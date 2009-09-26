#!/bin/bash

read EMAIL PASSWORD <.passwd

echo "$PASSWORD" | appcfg.py -e "$EMAIL" --passin update .

echo Done.
read -p "Press ENTER to quit..."
