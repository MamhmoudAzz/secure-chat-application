#!/bin/bash

echo "Secure Chat Application - Quick Start"
echo "===================================="
echo

echo "Checking Java installation..."
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install Java JDK from: https://openjdk.org/"
    exit 1
fi

if ! command -v javac &> /dev/null; then
    echo "ERROR: Java compiler (javac) not found"
    echo "Please install Java JDK (not just JRE)"
    exit 1
fi

echo "Java found! Compiling application..."
echo

# Create target directory
mkdir -p target/classes

# Compile all Java files
echo "Compiling source files..."
find src/main/java -name "*.java" | xargs javac -d target/classes -cp src/main/java

if [ $? -ne 0 ]; then
    echo "ERROR: Compilation failed!"
    exit 1
fi

echo "Compilation successful!"
echo
echo "Starting Secure Chat Application..."
echo
echo "Demo users you can try:"
echo "- admin / admin123"
echo "- alice / password"
echo "- bob / 123456"
echo "- charlie / secure"
echo
echo "IMPORTANT: All windows are now properly sized!"
echo "No need to resize manually - everything should be visible."
echo

# Run the application
java -cp target/classes com.securechat.controller.SecureChatApplication

echo
echo "Application closed."
