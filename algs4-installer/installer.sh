#!/bin/sh

NAME='algs4'
DEST='/usr/local'

CURR=`pwd`

cd ${DEST}
mkdir ${NAME}
chmod 755 ${NAME}
cd ${NAME}

wget http://algs4.cs.princeton.edu/code/algs4.jar
wget http://algs4.cs.princeton.edu/linux/javac-algs4
wget http://algs4.cs.princeton.edu/linux/java-algs4

chmod 755 javac-algs4 java-algs4
mv javac-algs4 ${DEST}/bin
mv java-algs4 ${DEST}/bin

cd ${CURR}
