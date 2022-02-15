#!/usr/bin/env bash
set -e

for day in $(seq 1 24)
do
  directory="./day${day}"
  file="${directory}/input.txt"
  if [[ ! -e "$file" ]]
  then
    if [[ ! -e "$directory" ]]
    then
      mkdir "$directory"
    fi
    wget -O "$file" --load-cookies=cookies.txt "https://adventofcode.com/2015/day/${day}/input"
  fi
done
