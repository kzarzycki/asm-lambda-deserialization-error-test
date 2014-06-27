asm-lambda-deserialization-error-test
=====================================

This is a project showing an error that occurs while using asm in version 5, jdk8, lambda serialization, gradle shadow plugin and its relocation feature.

To run the test, first compile the project:

    ./gradlew shadowJar

Then run command (assuming you have java jdk8 in the PATH):

    java -cp build/distributions/asm-test-1.0-shadow.jar AsmTest


The exception I'm getting is:

    Exception in thread "main" java.io.IOException: unexpected exception type
        at java.io.ObjectStreamClass.throwMiscException(ObjectStreamClass.java:1538)
        at java.io.ObjectStreamClass.invokeReadResolve(ObjectStreamClass.java:1110)
        at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1810)
        at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1351)
        at java.io.ObjectInputStream.readObject(ObjectInputStream.java:371)
        at AsmTest.deserialize(AsmTest.java:36)
        at AsmTest.main(AsmTest.java:21)
    Caused by: java.lang.reflect.InvocationTargetException
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:483)
        at java.lang.invoke.SerializedLambda.readResolve(SerializedLambda.java:230)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:483)
        at java.io.ObjectStreamClass.invokeReadResolve(ObjectStreamClass.java:1104)
        ... 5 more
    Caused by: java.lang.IllegalArgumentException: Invalid lambda deserialization
        at AsmTest.$deserializeLambda$(AsmTest.java:11)
        ... 15 more


Please see the test source code at: [https://github.com/kzarzycki/asm-lambda-deserialization-error-test/blob/master/src/main/java/AsmTest.java]
