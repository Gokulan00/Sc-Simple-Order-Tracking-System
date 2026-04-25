@echo off
cd /d D:\GOKUL\MOVIES\ordertracking\ordertracking\backend

javac -cp "src/main/java;target/dependency/*" src/main/java/*.java
java -cp "src/main/java;target/dependency/*" SimpleServer

pause 