# Secure Chat Application

A Java-based secure chat application featuring end-to-end encryption using RSA cryptography. This educational project demonstrates secure communication principles with user management, encrypted messaging, and course material viewing.

## Features

- **End-to-End Encryption**: RSA 2048-bit encryption for all messages
- **User Management**: Registration and authentication with SHA-256 password hashing
- **Secure Messaging**: Real-time chat between registered users
- **Course Materials**: Educational cybersecurity content viewer
- **Input Validation**: Protection against injection attacks
- **Modern GUI**: Clean Swing-based interface

## Prerequisites

- **Java JDK 8 or higher**
- Git (for cloning)

Verify Java installation:
```bash
javac -version
java -version
```

## Quick Start

### 1. Clone and Setup
```bash
git clone https://github.com/yourusername/secure-chat-application.git
cd secure-chat-application
```

### 2. Compile and Run

**Using Maven:**
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.securechat.controller.SecureChatApplication"
```

**Using Command Line:**
```bash
mkdir -p target/classes
javac -cp src/main/java -d target/classes src/main/java/com/securechat/*/*.java
java -cp target/classes com.securechat.controller.SecureChatApplication
```

### 3. Demo Users

Login with these pre-configured accounts:
```
admin / admin123
alice / password
bob / 123456
charlie / secure
```

### 4. Usage

1. **Login** with demo credentials or register a new user
2. **Chat**: Click "Open Secure Chat", select recipient, send encrypted messages
3. **Course Materials**: View cybersecurity educational content

## Testing

Run unit tests:
```bash
mvn test
```

## Project Structure

```
src/main/java/com/securechat/
├── controller/     # Application entry point
├── model/         # Data models (User, Message, etc.)
├── security/      # RSA encryption utilities
└── view/          # Swing GUI components
```

## Security Features

- **RSA 2048-bit encryption** for all messages
- **SHA-256 password hashing**
- **Input validation** against injection attacks
- **Secure key generation** for each user

## Development

### IDE Setup
- **IntelliJ IDEA**: Open project folder, run `SecureChatApplication.java`
- **Eclipse**: Import project, run as Java Application
- **VS Code**: Install Java Extension Pack, press F5

## Troubleshooting

**"Cannot find main class"**
```bash
# Ensure correct classpath
java -cp target/classes com.securechat.controller.SecureChatApplication
```

**Compilation issues**
- Make sure JDK (not just JRE) is installed
- Verify Java version: `javac -version`

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/new-feature`
3. Commit changes: `git commit -m 'Add new feature'`
4. Push to branch: `git push origin feature/new-feature`
5. Open a Pull Request

## License

MIT License - see [LICENSE](LICENSE) file for details.

## Educational Purpose

This project demonstrates secure communication concepts for educational purposes. For production use, additional security measures would be required.
