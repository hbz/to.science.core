#!/bin/bash

TARGET_DIR="./src/main/resources/resources"
REPO_URL="https://github.com/hbz/orca.resources.git"

if [ -d "$TARGET_DIR" ]; then
    echo "Directory already exists. Performing git pull..."
    cd "$TARGET_DIR"
    git pull
else
    echo "Directory does not exist. Performing git clone..."
    git clone "$REPO_URL" "$TARGET_DIR"
fi
