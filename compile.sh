 #!bin/bash

 echo "Compilation"

 javac -d logger/bin logger/src/logger/*.java
 javac -cp logger/bin -d restaurant/bin restaurant/src/restaurant/*.java
// javac -cp restaurant/bin:logger/bin -d testframework/bin testframework/src/testframework*.java

echo "Fin Compilation"