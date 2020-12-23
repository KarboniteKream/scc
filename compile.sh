#!/bin/bash

javac -cp src src/scc/Main.java && java -cp src scc.Main --phase=$1 --dump=$1 tests/$2.c

if [[ $1 == "build" ]]; then
	cat tests/$2.asm
else
	cat tests/$2.log
fi
