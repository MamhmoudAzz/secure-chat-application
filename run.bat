@echo off
echo Secure Chat Application - Quick Start
echo ====================================
echo.

echo Checking Java installation...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java JDK from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

javac -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java compiler (javac) not found
    echo Please install Java JDK (not just JRE)
    pause
    exit /b 1
)

echo Java found! Compiling application...
echo.

REM Create target directory
if not exist "target\classes" mkdir "target\classes"

REM Compile all Java files
echo Compiling source files...
for /r "src\main\java" %%f in (*.java) do (
    javac -cp "src\main\java" -d "target\classes" "%%f"
)

if %errorlevel% neq 0 (
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Starting Secure Chat Application...
echo.
echo Demo users you can try:
echo - admin / admin123
echo - alice / password  
echo - bob / 123456
echo - charlie / secure
echo.

REM Run the application
java -cp "target\classes" com.securechat.controller.SecureChatApplication

echo.
echo Application closed.
pause
