#!/bin/bash

javac dbDestroy.java
java -cp mysql-connector-java-8.0.28.jar:. dbDestroy
