#!/usr/bin/env bash

# Print the directories, which don't contain forbidden subdirectory or .ignore file

HIGHLIGHT='\033[0;31m'
NORMAL='\033[0m'

echo -e "Scanning $@"
find "$@" \( -execdir test -e {}/.ignore \; -prune \) -o \( -execdir test -d {}/forbidden \; -prune \) -o -type d \
    -exec bash -c '
      for x do
            echo -e "'${HIGHLIGHT}'Found directory: $x'${NORMAL}'"
            echo "PID: $$"
      done
      echo "Done"
    ' find-sh {} +


#https://unix.stackexchange.com/questions/93324/how-does-this-find-command-using-find-exec-sh-c-sh-work
