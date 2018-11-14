COMPILATION
javac -d tp0/bin tp0/src/*.java

//pour les depéndances
javac -cp tp0/bin -d tp1/bin tp1/src/*.java



EXECUTION
java -cp "tp0/bin:tp1/bin" BlackJackConsole
	ou
java -cp "tp0/bin;tp1/bin" BlackJackConsole
 