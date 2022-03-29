#!/bin/bash

javac dbCreator.java
java -cp mysql-connector-java-8.0.28.jar:. dbCreator
