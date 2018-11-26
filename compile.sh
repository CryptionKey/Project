#/bin/bash

echo "Compilation"

javac -d logger/bin logger/src/logger/*.java
javac -cp logger/bin -d restaurant/bin restaurant/src/restaurant/*.java

echo "Fin Compilation"