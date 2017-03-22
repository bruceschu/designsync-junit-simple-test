DesignSync JUnit Simple Test
designsync-junit-simple-test
============================

This example demonstrates:

* Unit tests written with [JUnit 4](http://junit.org/)
* Integration tests written with [JUnit 4](http://junit.org/)
* A Maven build that puts it all together

Running the tests
-----------------

* To run the unit tests, call `mvn test`

Conventions
-----------

This example follows the following basic conventions:

 | unit test | integration test
--- | --- | ---
__resides in:__ | `src/test/java/*Test.java` | `src/test/java/*IT.java`
__executes in Maven phase:__ | test | verify
__handled by Maven plugin:__ | [surefire](http://maven.apache.org/surefire/maven-surefire-plugin/) | [failsafe](http://maven.apache.org/surefire/maven-failsafe-plugin/)
