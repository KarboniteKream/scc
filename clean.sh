#!/bin/bash

find src/ -name "*.class" -type f -delete
find tests/ \( -name "*.log" -o -name "*.asm" \) -type f -delete
