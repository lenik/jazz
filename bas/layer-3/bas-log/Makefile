SHELL = /bin/bash

default: before-mvn
	mvn install -fn -DskipTests

before-mvn: clean

clean:
	rm -fr target/maven-status/maven-compiler-plugin/compile/log4j-plugin-processor/
