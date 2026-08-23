<div align="center">

<img src="./assets/hero-banner.svg" alt="Job Portal animated banner" width="100%"/>

<sub>🎬 Live animated banner — typewriter title reveal, radar pulses, scanning beam, and drifting particles, rendered with native SVG animation (no JS, no external CSS).</sub>

<br/><br/>

<p>
  <img src="https://img.shields.io/badge/Language-Java%2017-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Language: Java 17"/>
  <img src="https://img.shields.io/badge/Framework-Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Framework: Spring Boot"/>
  <img src="https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="Database: MySQL"/>
  <img src="https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Build: Maven"/>
  <img src="https://img.shields.io/badge/License-Educational%20Use-7F5AF0?style=for-the-badge" alt="License: Educational Use"/>
</p>

<p>
  <img src="https://img.shields.io/badge/Security-Spring%20Security-red?style=flat-square" alt="Spring Security"/>
  <img src="https://img.shields.io/badge/Roles-Employer%20%7C%20Job%20Seeker-blueviolet?style=flat-square" alt="Roles"/>
  <img src="https://img.shields.io/badge/UI-Thymeleaf%20%2B%20HTML5%2FCSS3-blue?style=flat-square" alt="UI Theme"/>
</p>

<h3>💼 A full-stack, role-based job portal connecting employers and job seekers — built with Java and Spring Boot</h3>

</div>

<img src="./assets/section-divider.svg" width="100%"/>

## 📖 Overview

**Job Portal** is a full-stack recruitment web application built on **Java, Spring Boot, Spring Security, Spring Data JPA (Hibernate), Thymeleaf, and MySQL**. It connects two sides of the hiring process from a single codebase — **employers** who post and manage job openings, and **job seekers** who search, apply, and track applications — all protected by Spring Security authentication.

Employers post roles, review applicants, and manage their listings end to end. Job seekers browse and search open positions, maintain a profile and resume, apply with one click, and follow their applications from a personal dashboard.

<table align="center">
<tr>
<td align="center">🏗️<br/><b>Architecture</b><br/>Spring MVC (Controller → Service/Repository → JPA)</td>
<td align="center">🔐<br/><b>Auth</b><br/>Spring Security, role-based sessions</td>
<td align="center">🗄️<br/><b>Data Layer</b><br/>Spring Data JPA + Hibernate</td>
<td align="center">🎨<br/><b>UI Layer</b><br/>Thymeleaf + HTML5/CSS3/JS</td>
</tr>
</table>

<img src="./assets/section-divider.svg" width="100%"/>

## ✨ Features

<table align="center" width="100%">
<tr>
<td width="50%" valign="top">

### 🏢 Employer Side
- Employer registration & login
- Post new job openings
- Edit / manage existing listings
- Review applicants per posting
- Employer dashboard

</td>
<td width="50%" valign="top">

### 👤 Job Seeker Side
- Job seeker registration & login
- Search & browse open jobs
- One-click apply flow
- Profile & resume management
- Application tracking dashboard

</td>
</tr>
</table>

> 🔑 Access is enforced by **Spring Security** — authenticated sessions gate posting, applying, and dashboard routes by user role.

<img src="./assets/section-divider.svg" width="100%"/>

## 🛠️ Technology Stack

<div align="center">

**Backend**

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring%20MVC-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=hibernate&logoColor=white"/>

**Frontend**

<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"/>
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"/>
<img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white"/>
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"/>

**Database & Build**

<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white"/>

</div>

<img src="./assets/section-divider.svg" width="100%"/>

## 🏗️ Architecture & Workflow

```mermaid
flowchart TD
    A["🌐 Browser<br/>Employer / Job Seeker"] -->|"HTTP Request"| B["🛡️ Spring Security Filter<br/>(session & role check)"]
    B --> C["🎮 Controller<br/>Spring MVC"]
    C --> D["⚙️ Service Layer"]
    C --> E["🗄️ Repository Layer<br/>Spring Data JPA"]
    D --> E
    E --> F[("🐬 MySQL")]
    F --> E
    E --> C
    C -->|"Model + View"| G["🎨 Thymeleaf Templates"]
    G -->|"Rendered HTML"| A

    style A fill:#0f0c29,stroke:#00d4ff,color:#ffffff
    style B fill:#1a1440,stroke:#ff4d6d,color:#ffffff
    style G fill:#0f0c29,stroke:#2cb67d,color:#ffffff
    style F fill:#1a1440,stroke:#7f5af0,color:#ffffff
```

**Request flow:** every request passes through the **Spring Security filter chain**, which checks the session against the requested route. Controllers delegate to the service/repository layer, JPA/Hibernate talks to MySQL, and Thymeleaf renders the appropriate employer or job-seeker template.

<img src="./assets/section-divider.svg" width="100%"/>

## 📸 Screenshots & Demo

> 🖼️ Add your actual screenshots to `docs/screenshots/` and update the filenames below. Placeholders are shown until real images are added.

<div align="center">
<table>
<tr>
<td align="center" width="50%">
<img src="./docs/screenshots/home.png" alt="Public home page — PLACEHOLDER" width="100%"/>
<br/><b>Public Home Page</b>
</td>
<td align="center" width="50%">
<img src="./docs/screenshots/employer-dashboard.png" alt="Employer dashboard — PLACEHOLDER" width="100%"/>
<br/><b>Employer Dashboard</b>
</td>
</tr>
<tr>
<td align="center" width="50%">
<img src="./docs/screenshots/job-search.png" alt="Job search page — PLACEHOLDER" width="100%"/>
<br/><b>Job Search</b>
</td>
<td align="center" width="50%">
<img src="./docs/screenshots/seeker-dashboard.png" alt="Seeker dashboard — PLACEHOLDER" width="100%"/>
<br/><b>Job Seeker Dashboard</b>
</td>
</tr>
</table>
</div>

<img src="./assets/section-divider.svg" width="100%"/>

## 🚀 Installation & Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Nesara29/prajju.git
```

### 2️⃣ Navigate to the Project
```bash
cd prajju
```

### 3️⃣ Configure the Database
Create a MySQL database and update:
```
src/main/resources/application.properties
```

```properties
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/job_portal
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
```

### 4️⃣ Build the Project
```bash
mvn clean install
```

### 5️⃣ Run the Application
```bash
mvn spring-boot:run
```
or, using the bundled Maven wrapper:
```bash
./mvnw spring-boot:run
```

### 6️⃣ Open the Application
```
http://localhost:8080
```

<img src="./assets/section-divider.svg" width="100%"/>

## 📂 Project Structure

```
JobPortal/
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   │   │   ├── templates/
│   │   │   ├── static/
│   │   │   └── application.properties
│   └── test/
├── assets/                  → README banners (this file's graphics)
│   ├── hero-banner.svg
│   ├── section-divider.svg
│   └── footer-banner.svg
├── docs/screenshots/
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

<img src="./assets/section-divider.svg" width="100%"/>

## 🎯 Learning Outcomes

This project demonstrates practical, hands-on experience with:

- ☕ Java & Spring Boot
- 🎮 Spring MVC
- 🛡️ Spring Security
- 🗄️ Spring Data JPA (Hibernate)
- 🐬 MySQL relational database design
- 🎨 Thymeleaf templating
- ⚡ CRUD operations end to end
- 📦 Maven project management

<img src="./assets/section-divider.svg" width="100%"/>

## 🚀 Future Enhancements

<table align="center">
<tr>
<td>📎 Resume Upload (PDF/DOC)</td>
<td>✉️ Email Notifications</td>
<td>🗓️ Interview Scheduling</td>
</tr>
<tr>
<td>🏢 Company Profiles</td>
<td>🎯 Job Recommendations</td>
<td>📊 Admin Analytics Dashboard</td>
</tr>
<tr>
<td>🔗 REST API Integration</td>
<td>🔑 JWT Authentication</td>
<td>☁️ Cloud Deployment (AWS/Azure)</td>
</tr>
</table>

<img src="./assets/section-divider.svg" width="100%"/>

## 🤝 Contributing

Contributions are welcome!

1. 🍴 Fork the repository
2. 🌿 Create a feature branch
3. 💾 Commit your changes
4. 📤 Push your branch
5. 🔁 Submit a Pull Request

<img src="./assets/section-divider.svg" width="100%"/>

## 👨‍💻 Developer

<div align="center">

<img src="https://img.shields.io/badge/Developer-Nesara-7F5AF0?style=for-the-badge" alt="Developer"/>

| | |
|---|---|
| 🧑‍💻 **Name** | `YOUR_NAME` |
| 🎓 **Role** | `Full-Stack Developer` |
| 📧 **Email** | `YOUR_EMAIL@example.com` |
| 🐙 **GitHub** | https://github.com/Nesara29 |

</div>

## 🔗 Project Links

<div align="center">

<a href="https://github.com/Nesara29/prajju">
  <img src="https://img.shields.io/badge/Repository-JobPortal-181717?style=for-the-badge&logo=github&logoColor=white" alt="Repository"/>
</a>
<a href="https://github.com/Nesara29/prajju/issues">
  <img src="https://img.shields.io/badge/Report-Issue-red?style=for-the-badge&logo=github&logoColor=white" alt="Issues"/>
</a>
<a href="https://github.com/Nesara29/prajju/fork">
  <img src="https://img.shields.io/badge/Fork-Project-2CB67D?style=for-the-badge&logo=github&logoColor=white" alt="Fork"/>
</a>

</div>

<img src="./assets/section-divider.svg" width="100%"/>

## 📄 License

This project is intended for **educational and learning purposes**. You are free to use, modify, and extend it for academic or personal projects.

> 📝 Replace this section with a formal license (e.g. MIT, Apache 2.0) and add a `LICENSE` file if you plan to distribute this project publicly.

## ⭐ Support

If you found this project useful, please give it a ⭐ **Star** on GitHub — it encourages future improvements and helps others discover the project.

<div align="center">
<img src="./assets/footer-banner.svg" alt="Thank you footer" width="100%"/>
</div>
