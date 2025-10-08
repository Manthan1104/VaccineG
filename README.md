# 💉 Vaccine Guard — Vaccine Management System  
[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-green.svg)](https://spring.io/projects/spring-boot) [![MongoDB](https://img.shields.io/badge/Database-MongoDB-brightgreen.svg)](https://www.mongodb.com/) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

## 🩺 Overview
**Vaccine Guard** is a full-stack vaccination management web application that simplifies scheduling, tracking, and communication between parents and doctors. It provides **real-time notifications**, **role-based dashboards**, and **secure JWT + OAuth2 authentication**, ensuring an organized and efficient vaccine tracking experience.  

### ✨ Highlights
- 📱 **Responsive UI** – Tailwind-powered modern design for mobile and desktop.  
- 🔐 **Secure Authentication** – Email/password & Google OAuth2 login.  
- 👩‍⚕️ **Doctor Tools** – Add children, manage vaccines, schedule doses, and send reminders.  
- 👨‍👩‍👧 **Parent Dashboard** – View children’s vaccination progress and receive doctor notifications.  
- 💾 **Scalable Backend** – Built with Spring Boot + MongoDB, deployable on Docker or cloud.  

---

## ⚙️ Tech Stack
| Layer | Technologies |
|-------|---------------|
| **Backend** | Spring Boot 3.2.5, Spring Security, Spring Data MongoDB, JWT (JJWT), OAuth2 |
| **Frontend** | HTML5, Vanilla JavaScript, Tailwind CSS, Lucide Icons |
| **Database** | MongoDB (local or cloud instance) |
| **Build Tool** | Maven |
| **Testing** | JUnit 5, Spring Boot Test |
| **Deployment** | Embedded Tomcat, Docker-ready |

---

## 🚀 Features
### 👤 Authentication & Authorization
- Role-based access (Parent / Doctor)  
- JWT-secured API endpoints  
- Google OAuth2 login for easy onboarding  

### 👶 Child & Vaccine Management
- Doctors can add children and vaccines.  
- Generate personalized vaccination schedules.  
- Mark completed vaccines and track pending doses.  

### 💬 Notifications System
- Doctors send messages/alerts to parents.  
- Parents receive and track unread notifications.  

### 🧭 Responsive Dashboard
- **Parent View** → Child details, vaccination history, reminders.  
- **Doctor View** → Add child/vaccine, manage schedules, send alerts.  

### 🛡️ Security
- JWT authentication for stateless sessions.  
- CORS configuration for frontend-backend communication.  
- Input validation and exception handling for safe operations.  

---

## 🧩 Prerequisites
- ☕ **Java 21 JDK**  
- 🧰 **Maven 3.8+**  
- 🍃 **MongoDB 4.4+**  
- 💻 *(Optional)* Node.js for frontend development tools  

---

## 🧱 Setup & Installation
1. **Clone the Repository**  
   `git clone https://github.com/yourusername/VaccineGuard.git`  
   `cd VaccineGuard`

2. **Configure Application Properties**  
spring.data.mongodb.uri=mongodb://localhost:27017/vaccineguard
jwt.secret.key=your-very-long-secret-key-here-at-least-256-bits

makefile
Copy code
*(Optional: Google OAuth2 Integration)*  
spring.security.oauth2.client.registration.google.client-id=your-client-id
spring.security.oauth2.client.registration.google.client-secret=your-client-secret
spring.security.oauth2.client.registration.google.scope=email,profile

markdown
Copy code

3. **Build & Run**  
`mvn clean install`  
`mvn spring-boot:run`

4. **Access**  
- Application → [http://localhost:8080](http://localhost:8080)  
- Serves static `index.html` frontend automatically.  

---

## 🧭 Usage Guide
### 👩‍⚕️ For Doctors
1. Register/Login as **Doctor**.  
2. Add children using Parent’s User ID.  
3. Create schedules and mark vaccines as complete.  
4. Send reminders/notifications to parents.  

### 👨‍👩‍👧 For Parents
1. Register/Login as **Parent**.  
2. View vaccination schedules for each child.  
3. Track completed & pending doses.  
4. Receive alerts from doctors in real-time.  

---

## 🧪 Testing
Run all unit/integration tests:  
`mvn test`  
**Note:** If you encounter a configuration error, update the package name in test class:  
`package com.vaccine.management;`

---

## 🌐 API Reference
**Base URL:** `http://localhost:8080/api`

| Category | Endpoint | Description |
|-----------|-----------|-------------|
| **Auth** | `POST /api/auth/signup` | Register new user |
|  | `POST /api/auth/login` | Login and get JWT |
| **Children** | `GET /api/children` | Doctor: Get all managed children |
|  | `POST /api/children` | Doctor: Add child |
|  | `GET /api/children/my-children` | Parent: Get own children |
| **Vaccines** | `GET /api/vaccines` | Get all vaccines |
|  | `POST /api/vaccines` | Doctor: Add vaccine |
| **Schedules** | `POST /api/schedules` | Create new vaccination schedule |
|  | `PUT /api/schedules/{id}/complete` | Mark as complete |
| **Notifications** | `POST /api/notifications` | Doctor: Send custom message |
|  | `GET /api/notifications/my-notifications` | Parent: View notifications |

> All protected routes require an **Authorization: Bearer <JWT>** header.

---

## 🤝 Contributing
1. **Fork** the repository  
2. **Create a feature branch**  
`git checkout -b feature/AmazingFeature`  
3. **Commit changes**  
`git commit -m "Add some AmazingFeature"`  
4. **Push to branch**  
`git push origin feature/AmazingFeature`  
5. **Open a Pull Request**  

Please ensure your code follows **Spring Boot conventions** and includes basic tests.

---

## 🪪 License
This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author
**Manthan Garg**  
📧 [***REMOVED***@gmail.com](mailto:***REMOVED***@gmail.com)  
🌐 [GitHub](https://github.com/Manthan1104) | [LinkedIn](https://www.linkedin.com/in/manthan-garg-b96838317)  

> Built with ❤️ to promote better health management and vaccination awareness.
