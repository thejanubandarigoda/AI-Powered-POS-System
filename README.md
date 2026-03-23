# 🛒 AI-Powered POS & Inventory Management System

A modern, enterprise-grade Point of Sale (POS) and Inventory Management System built with **Java** and **MySQL**. This application follows the **MVC (Model-View-Controller)** architecture and integrates **Local AI (Ollama)** and **Automated Data Backups**, making it a robust solution for retail businesses.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![JasperReports](https://img.shields.io/badge/JasperReports-007396?style=for-the-badge&logo=java&logoColor=white)
![Ollama AI](https://img.shields.io/badge/Ollama_Local_AI-FFA500?style=for-the-badge&logo=ollama&logoColor=white)

---

<img width="984" height="587" alt="Screenshot 2026-03-23 222251" src="https://github.com/user-attachments/assets/58a0219e-1ec9-46e4-b742-b94c7c49ee05" />

<img width="982" height="587" alt="Screenshot 2026-03-23 222257" src="https://github.com/user-attachments/assets/729d7198-78ed-48e7-9733-4dfe42ca9859" />

<img width="980" height="586" alt="Screenshot 2026-03-23 222343" src="https://github.com/user-attachments/assets/588531de-0a03-408b-9b1f-1c5cc62c9d06" />

## ✨ Key Features

* **🔐 Role-Based Authentication:** Secure login for Admins and Cashiers with password encryption logic.
* **📦 Inventory Management:** Real-time product tracking with full CRUD functionality and low-stock alerts.
* **🛒 Advanced Billing System:** * Interactive shopping cart.
  * Automatic inventory deduction upon sale.
  * Multi-table transaction handling in MySQL.
* **📊 Professional Reporting:** Generates print-ready **Daily Sales Reports** using JasperReports 6.x.
* **🧠 AI Business Insights:** Integrates with **Ollama (Llama 3)** to analyze sales trends and provide strategic business advice locally.
* **🛡️ Data Security & Backup:** One-click **Database Backup** feature that exports the entire system state into a `.sql` file for disaster recovery.

---

## 🛠️ Technology Stack

* **Language:** Java (JDK 17+)
* **GUI Framework:** Java Swing (Custom UI components)
* **Database:** MySQL 8.x
* **Architecture:** MVC Design Pattern
* **Reporting:** JasperReports Engine
* **AI Model:** Llama 3 (via Ollama API)

---

## 📂 Project Structure

```text
src/
 ├── controller/    # Logic (BackupController, AIInsightsController, etc.)
 ├── model/         # Data structures and Entities
 ├── view/          # UI components (Swing JPanels/JFrames)
 ├── db/            # Database connectivity (Singleton)
 ├── reports/       # Jasper (.jrxml) report templates
 └── Main.java      # Application bootstrap