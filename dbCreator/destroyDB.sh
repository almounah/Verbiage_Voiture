#!/bin/bash

javac -cp ojdbc6.jar:. dbDestroy.java
java -cp ojdbc6.jar:. dbDestroy
