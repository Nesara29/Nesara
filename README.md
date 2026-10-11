# 🚀 EventMS — 3D Event Management System

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.1-6DB33F?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql" />
  <img src="https://img.shields.io/badge/Frontend-HTML%20%7C%20CSS%20%7C%20JS-E34F26?style=for-the-badge&logo=html5" />
</p>

<p align="center">
  <strong>A modern event management and booking platform with a futuristic, 3D-inspired interface.</strong>
</p>

---

## 🌌 Project Preview

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:12002F,50:5B21B6,100:00D9FF&height=200&section=header&text=EVENTMS&fontSize=60&fontColor=FFFFFF&animation=fadeIn&fontAlignY=38&desc=Create%20Events.%20Connect%20People.&descAlignY=60&descSize=18" width="100%" alt="EventMS futuristic banner" />
</p>

<p align="center">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=20&duration=3000&pause=1000&color=00D9FF&center=true&vCenter=true&width=700&lines=Discover+Amazing+Events;Manage+Bookings+Effortlessly;Powered+by+Spring+Boot+%26+MySQL" alt="Animated EventMS introduction" />
</p>

## ✨ Features

| Feature | Description |
|---|---|
| 🎫 Event Booking | Browse events and manage bookings |
| 👤 Client Portal | Client-facing pages and access |
| 🛡️ Admin Portal | Administrative event management |
| 🤝 Subadmin Portal | Separate subadmin access |
| 🔐 Authentication | Spring Security integration |
| 🗄️ Database | MySQL with Spring Data JPA |
| 🎨 User Interface | Thymeleaf templates with responsive frontend styling |
| 🌐 Web Application | Spring Boot MVC architecture |

## 🧰 Technology Stack

<p align="center">
  <img src="https://skillicons.dev/icons?i=java,spring,mysql,html,css,js,maven,git,github" alt="Technologies used" />
</p>

- **Backend:** Java 17, Spring Boot 3.1
- **Security:** Spring Security
- **Database:** MySQL
- **Persistence:** Spring Data JPA
- **Frontend:** HTML, CSS, JavaScript, Thymeleaf
- **Build Tool:** Maven

## 🏗️ Application Architecture

```text
                 ┌────────────────────────┐
                 │       Web Browser      │
                 └───────────┬────────────┘
                             │
                 ┌───────────▼────────────┐
                 │  HTML / CSS / JS       │
                 │  Thymeleaf Templates   │
                 └───────────┬────────────┘
                             │
                 ┌───────────▼────────────┐
                 │ Spring Boot MVC        │
                 │ Controllers & Security  │
                 └───────────┬────────────┘
                             │
                 ┌───────────▼────────────┐
                 │ Spring Data JPA        │
                 └───────────┬────────────┘
                             │
                 ┌───────────▼────────────┐
                 │       MySQL            │
                 └────────────────────────┘
```

## 📂 Project Structure

```text
EventMS/
├── src/
│   └── main/
│       ├── java/
│       │   └── .../
│       │       ├── controller/
│       │       ├── model/
│       │       ├── repository/
│       │       └── service/
│       └── resources/
│           ├── static/
│           │   ├── css/
│           │   ├── js/
│           │   └── images/
│           ├── templates/
│           └── application.properties
├── pom.xml
└── README.md
```

*Illustrative structure; retain the actual package and folder names in your repository.*

## ⚙️ Getting Started

**1. Clone the repository**

```bash
git clone https://github.com/YOUR-USERNAME/EventMS.git
cd EventMS
```

**2. Configure MySQL**

Create a database and update your `application.properties` with your local database name, username, and password.

```sql
CREATE DATABASE eventms;
```

**3. Run the application**

```bash
mvn spring-boot:run
```

Open `http://localhost:8080` in your browser.

## 🎨 Design Direction

<p align="center">
  <img src="https://img.shields.io/badge/Theme-Deep%20Purple-5B21B6?style=flat-square" />
  <img src="https://img.shields.io/badge/Accent-Neon%20Cyan-00D9FF?style=flat-square" />
  <img src="https://img.shields.io/badge/Style-Futuristic%203D-111827?style=flat-square" />
</p>

The 3D-inspired visual direction uses dark backgrounds, neon highlights, glowing elements, and futuristic interface concepts. A live Three.js experience requires a separate HTML page and JavaScript module; GitHub README rendering does not run that code.

## 🎯 Project Goals

- Simplify event discovery and booking.
- Provide separate client and administrative portals.
- Organize application data using MySQL and JPA.
- Deliver a clean, modern web interface.
- Build a foundation for future event-management features.

## 👨‍💻 Developer

**Your Name**

- GitHub: [@YOUR-USERNAME](https://github.com/YOUR-USERNAME)

---

<p align="center">
  <strong>✨ EventMS — Bringing Events and People Together ✨</strong>
</p>

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:00D9FF,50:5B21B6,100:12002F&height=100&section=footer" width="100%" alt="Decorative footer" />
</p>
