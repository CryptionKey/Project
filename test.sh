#!/bin/bash

 echo "Compilation tests"
javac -cp restaurant/bin:logger/bin -d testframework/bin testframework/src/testframework*.java
 echo "Fin Compilation tests"


java -cp restaurant/bin:logger/bin:testframework/bin testframework.RunTest