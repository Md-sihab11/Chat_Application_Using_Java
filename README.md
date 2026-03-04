# 💬 Real-Time Group Chat Application (Java)

[![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square)](https://www.java.com/)  
[![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)](LICENSE)  
[![Forks](https://img.shields.io/github/forks/YOUR_USERNAME/Chat_Application_Using_Java?style=flat-square)](https://github.com/YOUR_USERNAME/Chat_Application_Using_Java/fork)

A **real-time multi-user group chat application** built using **Java Swing** and **TCP Socket Programming**.  
This project demonstrates **client-server communication**, **multithreading**, and **event-driven GUI programming**.

---

##  📁 Project Structure

Chat_Application_Using_Java/  
│  
├── src/   
│   └── group/  
│       └── chatting/   
│           └── application/     
│                   ├── UserOne.java  
│                   ├── UserTwo.java   
│                   ├── UserThird.java  
│                   └── UserFour.java  
│                   └── Server.java  
│                   ├── DataBase.java  
│                   └── TestDB.java  
│  
├── icons/  
│   ├── back.png   
│   ├── video.png  
│   ├── phone.png   
│   ├── profile.png  
│   ├── plus.png  
│   ├── camera.png  
│   └── gallery.png  
│  
├── docs/  
│   └── Explanation.pdf  
├── .gitignore  
└── README.md  

---

## 🎯 Key Features

- 💬 Real-time messaging between multiple clients  
- 🖥 Modern GUI with draggable undecorated windows  
- ⏱ Messages show timestamps & sender names  
- ⚡ Multi-client support using multithreading  
- 📎 Prepared for multimedia integration (camera, gallery, files)  
- 🔄 Real-time updates without refreshing GUI  

---

## 🛠 Tech Stack

- **Java 17**  
- **Java Swing & AWT** → GUI components & layout  
- **TCP Socket Programming** → Client-server communication  
- **Multithreading** → Handle multiple users concurrently  
- **BufferedReader / BufferedWriter** → Input/output streams  
- **Calendar & SimpleDateFormat** → Timestamp messages  
- **IDE**: IntelliJ IDEA / Eclipse / VS Code  

---

## 🧑‍💻 How It Works

### Client Side
- Separate clients for **UserOne → UserFour**  
- GUI includes:
  - Header panel: profile, back button, video/phone icons  
  - Chat area with **bubble messages**  
  - Input field + Send button  
  - Extra icons panel: Plus, Camera, Gallery  

- Methods:
  - **actionPerformed()** → Send message to server  
  - **formatLabel()** → Style messages as chat bubbles  
  - **run()** → Receive real-time messages  

### Server Side
- `Server.java` listens on port **2003**  
- Accepts multiple clients using threads  
- Stores client writers in **Vector** for broadcasting  
- Broadcasts messages to all connected users  

---

## ⚡ Installation & Running

### 1️⃣ Start the Server
```bash
javac src/group/chatting/application/server/Server.java
java src/group/chatting/application/server/Server
 file.
```
🍀 How to Fork & Contribute
Click Fork button on GitHub to create your copy.
Clone your fork locally:  
git clone https://github.com/Md-sihab11/Chat_Application_Using_Java.git

cd Chat_Application_Using_Java
Create a new feature branch:
git checkout -b feature/YourFeature

Make your changes and commit:
git add .
git commit -m "Add your feature description"

Push branch to your fork:
git push origin feature/YourFeature
Open a Pull Request to the original repository.

Celebrate 🎉 your contribution!

🔮 Future Improvements  
🎥 Video & audio call integration  
📂 File sharing between users  
😃 Emoji & reaction support  
🔔 Desktop notifications for new messages  
🗃 Persistent chat history in database  
🎨 Theme customization (light/dark mode)  


🎯 Conclusion  

This project demonstrates a fully functional real-time group chat application using Java Swing and TCP Sockets.  
Highlights include client-server architecture, multithreaded communication, and event-driven GUI design, providing a solid foundation for network programming and distributed systems.  
