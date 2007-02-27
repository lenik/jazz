@echo off

call xbind -cp ../../src/java/bin -cp %JAVA_LIB%/net.sf.freejava.jar *.binding.xml
