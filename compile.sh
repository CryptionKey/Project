
//COMPILATION
/javac -d tp0/bin tp0/src/*.java

//pour les depéndances
//javac -cp tp0/bin -d tp1/bin tp1/src/*.java


 javac -d logger/bin $(find logger/src/ -name *.java)
 javac -classpath logger/bin -d restaurant/bin $(find restaurant/src/ -name *.java)
// javac -classpath restaurant/bin:logger/bin -d testframework/bin $(find testframework/src -name *.java)

 echo "Compilation"