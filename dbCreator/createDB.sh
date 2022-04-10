#!/bin/bash

javac -cp ojdbc6.jar:. dbCreator.java
java -cp ojdbc6.jar:. dbCreator
