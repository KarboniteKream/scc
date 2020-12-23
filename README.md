# scc

A C programming language compiler for the SIC/XE computer.

Assembler and simulator are available at [sic-tools](https://github.com/KarboniteKream/sic-tools).

### Compiling
```sh
$ javac -cp src src/scc/Main.java
$ java -cp src scc.Main --phase=PHASE --dump=DUMP FILE.c
```
or
```sh
$ ./compile.sh PHASE FILE.c
```

### Running
```sh
$ ./run.sh PHASE/FILENAME
```

### Options
```
PHASE/DUMP = lexan|synan|ast|seman|frames|imcode|lincode|asmcode|tmpan|regalloc|build
```
