#!/bin/bash

# 1. Ask user for directory name
read -p "Enter the name of the directory you want to create: " dir_name

# 2. Try to create the directory
if mkdir "$dir_name" 2>/dev/null; then
    echo "Directory created successfully"
    
    # 3. Try to create txt.log file inside the directory
    if touch "$dir_name/txt.log" 2>/dev/null; then
        echo "File created successfully"
    else
        echo "Error: Could not create txt.log file"
        exit 1
    fi
else
    echo "Error: Could not create directory $dir_name"
    exit 1
fi 