# pocker-game

You are to build a command line program that takes, via STDIN, a "stream" of hands for a two player poker game. At the completion of the stream, your program should print to STDOUT the number of hands won by Player 1, and the number of hands won by Player 2.

# How to build
It is already built on the jar file. You can also open the project on Eclipse and build yourself, running through Eclipse.

Afterwards you should use piping to run the jar file passing the file poker-hands.txt through stdin, using the terminal or prompt.

On linux:
$ cat poker-hands.txt | java -jar pokerhandsort.jar
