# 🛒 AI-Powered POS & Inventory Management System

A modern, highly scalable, and intelligent Point of Sale (POS) and Inventory Management System built with **Java** and **MySQL**. This application follows the strict **MVC (Model-View-Controller)** architectural pattern and integrates **Local AI (Ollama)** for business intelligence, making it a truly next-generation retail solution.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![JasperReports](https://img.shields.io/badge/JasperReports-007396?style=for-the-badge&logo=java&logoColor=white)
![Ollama AI](https://img.shields.io/badge/Ollama_Local_AI-FFA500?style=for-the-badge&logo=ollama&logoColor=white)

---
<img width="984" height="587" alt="Screenshot 2026-03-23 222251" src="https://github.com/user-attachments/assets/58a0219e-1ec9-46e4-b742-b94c7c49ee05" />

<img width="982" height="587" alt="Screenshot 2026-03-23 222257" src="https://github.com/user-attachments/assets/729d7198-78ed-48e7-9733-4dfe42ca9859" />

<img width="980" height="586" alt="Screenshot 2026-03-23 222343" src="https://github.com/user-attachments/assets/588531de-0a03-408b-9b1f-1c5cc62c9d06" />


## ✨ Key Features

* **🔐 Secure Authentication:** * Role-based access control (Admin/Cashier) with database-driven validation.
* **🎛️ Interactive UI/UX:** * A full-screen, enterprise-grade Java Swing Dashboard with dynamic panel switching.
* **📦 Inventory Management:** * Complete CRUD operations for products with real-time stock monitoring.
* **🛒 Advanced POS & Billing:** * Dynamic shopping cart management.
  * Real-time total calculation and secure checkout.
  * Automatic inventory deduction and sales logging across multiple database tables.
* **📊 JasperReports Integration:** * Generates professional, print-ready "Daily Sales Reports" directly from the database using JasperReports 6.x.
* **🧠 AI Business Insights (Advanced Feature):** * Integrates with a local LLM via **Ollama (Llama 3)** to analyze database sales records and generate intelligent, actionable business insights without needing an internet connection.

---

## 🛠️ Technology Stack

* **Language:** Java (JDK 17 or higher)
* **GUI Framework:** Java Swing / AWT
* **Database:** MySQL
* **Architecture:** MVC (Model-View-Controller)
* **Reporting:** JasperReports 6.21.4
* **AI Integration:** Local HTTP Requests to Ollama API

---

## 📂 Project Architecture (MVC)

```text
src/
 ├── controller/    # Business logic (LoginController, POSController, AIInsightsController, etc.)
 ├── model/         # Data models and structures (Product, CartItem, etc.)
 ├── view/          # GUI components (DashboardView, POSView, AIInsightsView, etc.)
 ├── db/            # Database connection singleton class (DBConnection)
 ├── reports/       # JasperReports templates (.jrxml files)
 └── Main.java      # Application entry point
