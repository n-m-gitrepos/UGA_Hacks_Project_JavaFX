#!/bin/bash -ex

mvn -q clean
mvn -q compile
mvn clean javafx:run
