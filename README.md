# Secure Chat Application

A Java-based secure chat application featuring end-to-end encryption using RSA cryptography. This application demonstrates secure communication principles and includes user management, encrypted messaging, and course material viewing capabilities.

## Features

- **End-to-End Encryption**: Messages are encrypted using RSA 2048-bit keys
- **User Management**: Registration and authentication system with SHA-256 password hashing
- **Real-time Chat**: Secure messaging between registered users
- **Course Materials**: View educational content related to cybersecurity
- **Input Validation**: Protection against basic injection attacks
- **Modern GUI**: Clean Swing-based user interface

## Architecture

The application follows the Model-View-Controller (MVC) pattern:

```
src/main/java/com/securechat/
├── controller/          # Application controllers
│   └── SecureChatApplication.java
├── model/              # Data models
│   ├── User.java
│   ├── Message.java
│   ├── ParticipantListModel.java
│   ├── MessageListModel.java
│   └── CourseModel.java
├── security/           # Encryption utilities
│   ├── RSAUtil.java
│   └── MessageProxy.java
└── view/              # GUI components
    ├── LoginView.java
    ├── RegisterView.java
    ├── MainAppView.java
    ├── ChatView.java
    ├── CourseView.java
    └── ParticipantListView.java
```

## Prerequisites

- **Java Development Kit (JDK) 8 or higher** (not just JRE)
- Git for cloning the repository

### Installing Java on Different Operating Systems

**Windows:**
1. Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
2. Run the installer and follow the setup wizard
3. Add Java to your PATH environment variable
4. Verify installation:
```cmd
javac -version
java -version
```

**macOS:**
```bash
# Using Homebrew
brew install openjdk@11

# Or download from Oracle/OpenJDK websites
# Verify installation
javac -version
java -version
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-11-jdk

# Verify installation
javac -version
java -version
```

**Linux (CentOS/RHEL/Fedora):**
```bash
# CentOS/RHEL
sudo yum install java-11-openjdk-devel

# Fedora
sudo dnf install java-11-openjdk-devel

# Verify installation
javac -version
java -version
```

## Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/secure-chat-application.git
cd secure-chat-application
```

### 2. Compile the Application

**Option A: Using Maven (Recommended)**
```bash
# If you have Maven installed
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="com.securechat.controller.SecureChatApplication"
```

**Option B: Using Command Line (No Maven required)**
```bash
# Create target directory
mkdir -p target/classes

# Compile all Java files
javac -cp src/main/java -d target/classes src/main/java/com/securechat/controller/*.java src/main/java/com/securechat/model/*.java src/main/java/com/securechat/security/*.java src/main/java/com/securechat/view/*.java

# Run the application
java -cp target/classes com.securechat.controller.SecureChatApplication
```

**Option C: One-line compilation (Linux/macOS)**
```bash
find src/main/java -name "*.java" | xargs javac -d target/classes -cp src/main/java && java -cp target/classes com.securechat.controller.SecureChatApplication
```

**Option D: Windows PowerShell**
```powershell
# Create target directory
New-Item -ItemType Directory -Force -Path "target\classes"

# Compile all Java files
Get-ChildItem -Path "src\main\java" -Recurse -Filter "*.java" | ForEach-Object { javac -cp "src\main\java" -d "target\classes" $_.FullName }

# Run the application
java -cp "target\classes" com.securechat.controller.SecureChatApplication
```

### 3. Using the Application

1. **Launch the Application**
   - The login window will appear automatically

2. **Login with Pre-configured Demo Users**
   ```
   Username: admin    | Password: admin123
   Username: alice    | Password: password
   Username: bob      | Password: 123456
   Username: charlie  | Password: secure
   ```

3. **Register New Users**
   - Click "New User" on the login screen
   - Fill in User ID, Username, and Password
   - Click "Register"

4. **Start Secure Chatting**
   - After login, click "Open Secure Chat"
   - Select a recipient from the dropdown
   - Type your message and click "Send"
   - All messages are automatically encrypted with RSA

5. **View Course Materials**
   - Click "View Course Materials" from the main menu
   - Browse cybersecurity educational content

## Testing

### Running Unit Tests

The project includes unit tests for the RSA encryption functionality:

```bash
# If using Maven
mvn test

# If using command line (requires JUnit 5 JAR files)
javac -cp "junit-platform-console-standalone-1.9.2.jar:target/classes" -d target/test-classes src/test/java/com/securechat/security/RSAUtilTest.java
java -cp "junit-platform-console-standalone-1.9.2.jar:target/classes:target/test-classes" org.junit.platform.console.ConsoleLauncher --class-path target/test-classes --select-class com.securechat.security.RSAUtilTest
```

### Manual Testing

1. **Test User Registration**
   ```
   1. Launch the application
   2. Click "New User"
   3. Enter: ID="test1", Username="testuser", Password="testpass"
   4. Click "Register"
   5. Verify success message
   ```

2. **Test Login**
   ```
   1. Use credentials: Username="testuser", Password="testpass"
   2. Click "Login"
   3. Verify main application window opens
   ```

3. **Test Encrypted Messaging**
   ```
   1. Login with two different users (use demo accounts)
   2. Open "Secure Chat" from both accounts
   3. Send messages between users
   4. Verify messages are received and decrypted correctly
   ```

## Configuration

### Custom Course Content

To add your own course materials:

1. Place text files in the `resources/` directory
2. Update the course path in `MainAppView.java`:
   ```java
   String coursePath = "resources/your-course-file.txt";
   ```

### Security Settings

The application uses:
- **RSA 2048-bit encryption** for message security
- **SHA-256 hashing** for password storage
- **Input validation** to prevent injection attacks

## Development

### Project Structure

```
secure-chat-application/
├── src/
│   ├── main/java/com/securechat/    # Source code
│   │   ├── controller/              # Application controllers
│   │   ├── model/                   # Data models
│   │   ├── security/                # Encryption utilities
│   │   └── view/                    # GUI components
│   └── test/java/com/securechat/    # Unit tests
├── target/                          # Compiled classes
├── resources/                       # Course materials and assets
├── docs/                           # Documentation
├── pom.xml                         # Maven configuration
└── README.md                       # This file
```

### Building with IDE

**IntelliJ IDEA:**
1. Open the project folder
2. Wait for indexing to complete
3. Right-click on `SecureChatApplication.java`
4. Select "Run 'SecureChatApplication.main()'"

**Eclipse:**
1. Import as existing project
2. Right-click on `SecureChatApplication.java`
3. Select "Run As" > "Java Application"

**VS Code:**
1. Install Java Extension Pack
2. Open the project folder
3. Press F5 or use "Run and Debug"

## Security Features

- **RSA Encryption**: Each user gets a unique 2048-bit RSA key pair
- **Message Encryption**: All messages are encrypted with recipient's public key
- **Password Security**: Passwords are hashed using SHA-256
- **Input Validation**: Protection against SQL injection and XSS attacks
- **Secure Key Generation**: Cryptographically secure random key generation

## Troubleshooting

### Common Issues and Solutions

**1. "javac: command not found" or "java: command not found"**
```bash
# Check if Java is installed
which java
which javac

# If not found, install JDK (see Prerequisites section)
# Make sure JAVA_HOME is set correctly
echo $JAVA_HOME
```

**2. Compilation errors about "String.repeat()"**
- This has been fixed in the current version
- The application now uses Java 8-compatible string repetition

**3. "Cannot find or load main class"**
```bash
# Ensure you're in the project root directory
pwd

# Check if classes were compiled correctly
ls -la target/classes/com/securechat/controller/

# Use the correct classpath
java -cp target/classes com.securechat.controller.SecureChatApplication
```

**4. Deprecation warnings about Observable/Observer**
- These are warnings, not errors - the code will compile and run correctly
- The warnings appear because Observable/Observer were deprecated in Java 9+
- For production use, consider migrating to modern event systems

**5. GUI doesn't appear (Linux)**
```bash
# Check if display is available
echo $DISPLAY

# For headless systems, use X11 forwarding
ssh -X username@hostname

# Or install a virtual display
sudo apt install xvfb
export DISPLAY=:99
Xvfb :99 -screen 0 1024x768x24 &
```

**6. Permission denied errors**
```bash
# Make sure you have write permissions
chmod +w target/
chmod +w target/classes/
```

## Contributing

1. Fork the repository
2. Create a feature branch:
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. Make your changes and test them
4. Commit your changes:
   ```bash
   git commit -m 'Add amazing feature'
   ```
5. Push to the branch:
   ```bash
   git push origin feature/amazing-feature
   ```
6. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

If you encounter any issues or have questions:

1. Check the [Issues](../../issues) page for existing solutions
2. Create a new issue with:
   - Detailed description of the problem
   - Steps to reproduce
   - Your operating system and Java version
   - Error messages or logs

## Educational Purpose

This application is designed for educational purposes to demonstrate secure communication concepts. For production use, additional security measures and thorough security auditing would be required.

## Acknowledgments

- Built as an educational project for cybersecurity learning
- Demonstrates practical application of RSA encryption
- Inspired by modern secure messaging applications
