# 📸 Screenshot Guide for Secure Chat Report

## 🎯 Required Screenshots Checklist

### **Setup Instructions:**
1. Create screenshots directory:
   ```bash
   mkdir -p docs/screenshots
   ```

2. Run the application:
   ```bash
   cd /home/mahmoud/Downloads/Annane
   ./build.sh
   # Or: java -cp build com.securechat.controller.SecureChatApplication
   ```

## 📋 Screenshot Checklist

### **1. Login Interface** 
**File:** `docs/screenshots/login_view.png`
- [ ] Application starts with login window
- [ ] Modern blue design visible
- [ ] Demo credentials shown in footer
- [ ] 🔒 emoji and "Secure Chat" title visible
- [ ] Input fields and buttons clearly shown

**Steps:**
1. Launch application
2. Wait for login window to appear
3. Take screenshot of entire window
4. Save as `login_view.png`

---

### **2. Registration Interface**
**File:** `docs/screenshots/register_view.png`
- [ ] Click "Create New Account" button
- [ ] Registration form with 3 fields visible
- [ ] Modern card design shown
- [ ] 👤 emoji and "Create Account" title visible
- [ ] Input validation hints visible

**Steps:**
1. From login window, click "Create New Account"
2. Registration window opens
3. Take screenshot of registration form
4. Save as `register_view.png`

---

### **3. Main Dashboard**
**File:** `docs/screenshots/main_dashboard.png`
- [ ] Login with: admin / admin123
- [ ] Welcome message with user name
- [ ] Two feature cards visible (Chat & Course)
- [ ] Modern card-based layout
- [ ] User ID information shown
- [ ] Logout button visible

**Steps:**
1. Close registration window
2. Login with admin/admin123
3. Main dashboard appears
4. Take screenshot of entire dashboard
5. Save as `main_dashboard.png`

---

### **4. Chat Interface**
**File:** `docs/screenshots/chat_interface.png`
- [ ] Click "Open Secure Chat" from dashboard
- [ ] Chat window with modern header
- [ ] User dropdown on right side
- [ ] Message input area at bottom
- [ ] Send some test messages between users
- [ ] Message bubbles visible
- [ ] Encryption status shown

**Steps:**
1. From dashboard, click "Open Secure Chat"
2. Select a user from dropdown (alice, bob, or charlie)
3. Type and send a few messages
4. Take screenshot showing conversation
5. Save as `chat_interface.png`

---

### **5. Course Viewer**
**File:** `docs/screenshots/course_viewer.png`
- [ ] Click "View Course Materials" from dashboard
- [ ] Course content window opens
- [ ] 📚 emoji and title visible
- [ ] Course content text displayed
- [ ] Modern document viewer layout
- [ ] Refresh and Close buttons visible

**Steps:**
1. From dashboard, click "View Course Materials"
2. Course viewer window opens
3. Wait for content to load
4. Take screenshot of entire window
5. Save as `course_viewer.png`

---

### **6. User List Interface**
**File:** `docs/screenshots/user_list.png`
- [ ] Open participant list (if available in your version)
- [ ] List of registered users
- [ ] Modern formatting with emojis
- [ ] User statistics shown
- [ ] 👥 emoji and title visible

**Steps:**
1. Look for "View Users" or similar option
2. If not available, create a simple view
3. Take screenshot showing user list
4. Save as `user_list.png`

---

### **7. Architecture Diagram**
**File:** `docs/screenshots/architecture_diagram.png`
- [ ] Create diagram showing MVC pattern
- [ ] Include: Controller, Model, View, Security packages
- [ ] Show relationships between components
- [ ] Professional diagram layout

**Tools to Use:**
- **Online:** draw.io, Lucidchart, Creately
- **Desktop:** Microsoft Visio, LibreOffice Draw
- **Simple:** PowerPoint, Google Slides

**Diagram Content:**
```
┌─────────────────┐
│   Controller    │
│ SecureChatApp   │
└─────────┬───────┘
          │
    ┌─────┴─────┐
    │           │
┌───▼───┐   ┌───▼───┐
│ Model │   │ View  │
│       │   │       │
│ User  │   │ Login │
│Message│   │ Chat  │
│ etc.  │   │ etc.  │
└───┬───┘   └───────┘
    │
┌───▼───┐
│Security│
│ RSA   │
│Encrypt│
└───────┘
```

---

### **8. Class Diagram**
**File:** `docs/screenshots/class_diagram.png`
- [ ] UML-style class diagram
- [ ] Show main classes: User, Message, RSAUtil, Views
- [ ] Include relationships and dependencies
- [ ] Professional UML notation

**Classes to Include:**
- User (with attributes and methods)
- Message (with encryption fields)
- RSAUtil (static methods)
- LoginView, ChatView, etc.
- ParticipantListModel, MessageListModel

## 🛠️ Screenshot Tools

### **Linux:**
```bash
# Built-in tools
gnome-screenshot -w    # Window screenshot
gnome-screenshot -a    # Area screenshot

# Advanced tools
sudo apt install flameshot
flameshot gui
```

### **macOS:**
```bash
# Built-in shortcuts
Cmd + Shift + 4        # Area selection
Cmd + Shift + 3        # Full screen
Cmd + Shift + 4 + Space # Window selection
```

### **Windows:**
```bash
# Built-in tools
Windows + Shift + S    # Snipping tool
Alt + PrtScn          # Active window
```

## 📐 Screenshot Quality Guidelines

### **Resolution:**
- Minimum: 1024x768
- Recommended: 1920x1080 or higher
- Format: PNG (for crisp text and UI elements)

### **Composition:**
- Include entire window with title bar
- Avoid desktop background distractions
- Ensure text is readable
- Crop unnecessary white space

### **Naming Convention:**
- Use exact filenames as specified
- All lowercase with underscores
- PNG format only
- Place in `docs/screenshots/` folder

## ✅ Final Verification

### **Check Each Screenshot:**
- [ ] File exists in correct location
- [ ] Filename matches LaTeX document exactly
- [ ] Image is clear and readable
- [ ] Shows the intended interface/feature
- [ ] Proper resolution and format

### **Test LaTeX Compilation:**
```bash
cd docs/
pdflatex SecureChat_Report.tex
```

If images don't appear:
- Check file paths in LaTeX document
- Verify image files exist
- Ensure correct file extensions
- Check for typos in filenames

## 🎨 Enhancement Tips

### **For Better Screenshots:**
1. **Clean Desktop:** Hide unnecessary windows
2. **Consistent Sizing:** Use same window sizes
3. **Good Lighting:** Ensure screen brightness is adequate
4. **Focus:** Highlight important UI elements
5. **Annotations:** Add arrows or callouts if needed

### **Professional Touch:**
- Use consistent window positions
- Ensure all text is readable
- Avoid personal information in screenshots
- Show realistic but clean data
- Maintain consistent UI state across screenshots

## 🚀 Quick Workflow

1. **Prepare Environment:**
   - Clean desktop
   - Close unnecessary applications
   - Set consistent window sizes

2. **Take Screenshots Systematically:**
   - Follow the checklist order
   - Name files immediately
   - Verify each screenshot before moving on

3. **Create Diagrams:**
   - Use professional diagramming tools
   - Export as high-resolution PNG
   - Ensure readability at document size

4. **Verify Integration:**
   - Compile LaTeX document
   - Check all images appear correctly
   - Adjust sizing if needed

**Your comprehensive screenshot collection will make your report professional and visually appealing!** 📊
