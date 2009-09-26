#!/bin/bash

read EMAIL PASSWORD <.passwd

for app in www; do
    echo "Uploading $app..."
    echo "$PASSWORD" | appcfg.py -e "$EMAIL" --passin update "$app"
done

echo Done.
read -p "Press ENTER to quit..."
