asm-lambda-deserialization-error-test
=====================================

This is a project showing an error that occurs while using asm in version 5, jdk8, lambda serialization, gradle shadow plugin and its relocation feature.

To run the test, first compile the project:

    ./gradlew shadowJar

Then run command (assuming you have java jdk8 in the PATH):

    java -cp build/distributions/asm-test-1.0-shadow.jar AsmTest
