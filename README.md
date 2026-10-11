# R8: inlined `@Synchronized` method fails ART verification

R8 inlines `Foobar.doSomething()` (`@Synchronized`, with a branch) into a `try`/`catch (Exception)` in
`MainActivity.onCreate`. ART rejects the class on launch:

```
java.lang.VerifyError: Verifier rejected class com.example.r8repro.MainActivity:
void com.example.r8repro.MainActivity.onCreate(android.os.Bundle) failed to verify:
[0x10] expected to be within a catch-all for an instruction where a monitor is held
```

## Run

```bash
# Generate your own key and use "android" as the password
keytool -genkey -v -keystore repro.jks -keyalg RSA -keysize 2048 -validity 10000 -alias repro
./gradlew :app:assembleRelease [-Pr8.version=<version>]
adb install -r app/build/outputs/apk/release/app-release.apk
adb shell am start -n com.example.r8repro/.MainActivity
```

## Results (Pixel 9 Pro XL, minSdk 29)

| R8 | Result |
|---|---|
| 8.13.19, 8.13.25 | OK |
| 9.0.32, 9.0.54, 9.1.31, 9.1.56, 9.2.21, 9.2.38, 9.3.31, 9.4.12, 9.4.24, 9.4.28 | VerifyError |
| 9.5.23 | OK |

Crashes only with both the branch in `doSomething` and the `try`/`catch` in `MainActivity`.
