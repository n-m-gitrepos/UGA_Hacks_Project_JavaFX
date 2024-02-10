#!/bin/bash -ex

mvn -q clean
mvn -q compile
mvn -q exec:java -Dexec.mainClass=com.example.uga_hacks_project.Application
# mvn clean javafx:run
