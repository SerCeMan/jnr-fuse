# Releasing

1. Ensure the correct JDK is installed.
2. Verify GPG is configured and the keys are registered, see https://docs.gradle.org/current/userguide/signing_plugin.html. 
3. Configure `~/.gradle/gradle.properties` with the GPG key information.
4. Open https://oss.sonatype.org/#profile;User%20Token and generate a new token.
5. Run `SONATYPE_LOGIN=$LOGIN_KEY SONATYPE_PASSWORD=$PASSWORD_KEY ./gradlew  clean release`
6. Update the version in the README.md file.