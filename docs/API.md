# API Documentation

## Overview

This document describes the internal API structure of the Secure Chat Application. While this is a desktop application without REST APIs, it has well-defined internal interfaces between components.

## Core Components

### Security Package

#### RSAUtil Class

**Purpose**: Provides RSA encryption and decryption utilities.

**Methods**:

```java
public static KeyPair generateKeyPair()
```
- **Description**: Generates a new RSA key pair with 2048-bit key size
- **Returns**: `KeyPair` object containing public and private keys
- **Throws**: `RuntimeException` if key generation fails

```java
public static String encrypt(String plainText, PublicKey publicKey)
```
- **Description**: Encrypts plain text using RSA public key
- **Parameters**:
  - `plainText`: The message to encrypt
  - `publicKey`: The recipient's public key
- **Returns**: Base64-encoded encrypted string
- **Throws**: `RuntimeException` if encryption fails

```java
public static String decrypt(String cipherText, PrivateKey privateKey)
```
- **Description**: Decrypts encrypted text using RSA private key
- **Parameters**:
  - `cipherText`: Base64-encoded encrypted message
  - `privateKey`: The recipient's private key
- **Returns**: Decrypted plain text string
- **Throws**: `RuntimeException` if decryption fails

#### MessageProxy Class

**Purpose**: Handles secure message operations between users.

**Constructor**:
```java
public MessageProxy(MessageListModel messageModel)
```

**Methods**:

```java
public void sendEncryptedMessage(User sender, User receiver, String messageText)
```
- **Description**: Sends an encrypted message from sender to receiver
- **Parameters**:
  - `sender`: The user sending the message
  - `receiver`: The user receiving the message
  - `messageText`: The plain text message
- **Throws**: `RuntimeException` if encryption fails

```java
public String decryptMessage(Message message, User receiver)
```
- **Description**: Decrypts a message for the specified receiver
- **Parameters**:
  - `message`: The encrypted message object
  - `receiver`: The user attempting to decrypt
- **Returns**: Decrypted message or error message

### Model Package

#### User Class

**Purpose**: Represents a user in the system.

**Constructor**:
```java
public User(String id, String pseudo, String password)
```

**Methods**:
- `String getId()`: Returns user ID
- `String getPseudoName()`: Returns user pseudonym
- `String getPassword()`: Returns hashed password
- `PublicKey getPublicKey()`: Returns user's public key
- `PrivateKey getPrivateKey()`: Returns user's private key

#### Message Class

**Purpose**: Represents a message in the system.

**Constructor**:
```java
public Message(String sender, String receiver, String originalContent, String encryptedContent)
```

**Methods**:
- `String getSender()`: Returns sender's pseudonym
- `String getReceiver()`: Returns receiver's pseudonym
- `String getContent()`: Returns encrypted content
- `String getOriginalContent()`: Returns original plain text

#### ParticipantListModel Class

**Purpose**: Manages user registration and authentication.

**Methods**:

```java
public void registerParticipant(String id, String pseudo, String rawPassword)
```
- **Description**: Registers a new user with hashed password
- **Parameters**:
  - `id`: Unique user identifier
  - `pseudo`: User's display name
  - `rawPassword`: Plain text password (will be hashed)

```java
public boolean login(String pseudo, String passwordHash)
```
- **Description**: Authenticates user credentials
- **Parameters**:
  - `pseudo`: User's pseudonym
  - `passwordHash`: SHA-256 hash of password
- **Returns**: `true` if authentication successful

```java
public User getUserByPseudo(String pseudo)
```
- **Description**: Retrieves user by pseudonym
- **Returns**: `User` object or `null` if not found

```java
public Vector<User> getParticipants()
```
- **Description**: Gets all registered users
- **Returns**: Vector containing all users

#### MessageListModel Class

**Purpose**: Manages message storage and notifications.

**Methods**:

```java
public void sendMessage(String sender, String receiver, String original, String encrypted)
```
- **Description**: Adds a new message to the list
- **Parameters**:
  - `sender`: Sender's pseudonym
  - `receiver`: Receiver's pseudonym
  - `original`: Original plain text
  - `encrypted`: Encrypted content

```java
public Vector<Message> getMessages()
```
- **Description**: Gets all messages
- **Returns**: Vector containing all messages

### View Package

#### LoginView Class

**Purpose**: Provides user authentication interface.

**Constructor**:
```java
public LoginView(ParticipantListModel participantModel, MessageListModel messageModel)
```

#### RegisterView Class

**Purpose**: Provides user registration interface.

**Constructor**:
```java
public RegisterView(ParticipantListModel participantModel, int h, int v)
```

#### ChatView Class

**Purpose**: Provides secure messaging interface.

**Constructor**:
```java
public ChatView(MessageListModel messageModel, User user, ParticipantListModel participantModel, int h, int v)
```

**Implements**: `Observer` interface for real-time message updates

#### MainAppView Class

**Purpose**: Main application dashboard after login.

**Constructor**:
```java
public MainAppView(User user, MessageListModel messageModel, ParticipantListModel participantModel)
```

## Observer Pattern Implementation

The application uses the Observer pattern for real-time updates:

### Observable Classes
- `ParticipantListModel`: Notifies when users are registered
- `MessageListModel`: Notifies when messages are sent
- `CourseModel`: Notifies when course data changes

### Observer Classes
- `RegisterView`: Updates when new users are registered
- `ChatView`: Updates when new messages arrive
- `CourseView`: Updates when course content changes

## Error Handling

### Exception Types
- `RuntimeException`: Used for encryption/decryption failures
- `IOException`: Used for file operations in course viewing
- Standard Java exceptions for UI operations

### Error Messages
- Encryption failures: "Encryption failed"
- Decryption failures: "[Unable to decrypt message]"
- File not found: "Course file not found: [path]"
- Invalid credentials: "Invalid credentials!"

## Security Considerations

### Input Validation
All user inputs are validated using regex patterns to prevent:
- SQL injection
- Cross-site scripting
- Command injection

### Password Handling
- Passwords are immediately hashed using SHA-256
- Plain text passwords are not stored
- Password fields are cleared after use

### Key Management
- RSA keys are generated per user
- Private keys never leave the user's session
- Public keys are used for encryption only

---

**Note**: This API documentation describes the internal structure for developers working on the application. For usage instructions, see the main README.md file.
