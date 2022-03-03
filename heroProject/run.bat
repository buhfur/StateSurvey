@echo off

echo Compiling source files....
javac -d . src/Hero.java
javac -d . src/Party.java
javac -d . src/PartyTest.java

echo Running Application...
echo. 
java src/PartyTest 
