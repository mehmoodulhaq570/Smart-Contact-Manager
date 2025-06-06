# Smart Contact Manager 2.0

**Smart Contact Manager (SCM) 2.0** is a full-fledged web application built with Java and Spring Boot, designed to help you manage your contacts efficiently. It offers a range of features from basic contact storage to advanced functionalities like social logins, cloud-based image storage, and direct emailing.

---

## 🚀 Features

- **👤 User Management:**
  - User Signup with email and password
  - Email verification via link
  - Signup/Login with Google and GitHub (OAuth)
  - View and Edit own profile details
- **📞 Contact Management:**
  - Add contacts with pictures
  - Contact pictures uploaded to cloud storage (AWS/Cloudinary)
  - View all contacts in a paginated list
  - View detailed contact information
  - Update existing contact details
  - Delete contacts
  - Search for specific contacts
  - Mark contacts as favorites ⭐
- **📧 Email Integration:**
  - Compose and send emails directly from the application
  - Emails can include text and attachments
- **📊 Data Management:**
  - Export contact data to Excel
- **🎨 User Interface:**
  - Dark and Light theme options 🌗
  - User-friendly interface built with TailwindCSS and Flowbite components
- **🗣️ Feedback:**
  - Option for users to provide feedback

---

## 🛠️ Technologies Used

This project leverages a modern technology stack to deliver a robust and scalable application:

- **Backend:**
  - ☕ Latest Spring Boot
  - 🧱 Spring Framework
  - 🌐 Spring MVC
  - 🗃️ Spring Data JPA (Hibernate for ORM)
  - 🔐 Spring Security (for authentication and authorization)
  - 📧 Java Email API
- **Frontend:**
  - 🍃 Thymeleaf (Server-side template engine)
  - 🎨 TailwindCSS
  - 🧩 Flowbite Components
  - <0xF0><0x9F><0xAA><0xB2> JavaScript
- **Database:**
  - 🐘 MySQL / PostgreSQL
- **Authentication:**
  - 🔑 OAuth 2.0 (for Google and GitHub login)
- **File Storage:**
  - ☁️ AWS SDK / Cloudinary SDK
- **Reporting:**
  - 📄 Apache POI or similar (for Excel export)
- **Other Tools:**
  - ✔️ Validation (Bean Validation)

---

## ⚙️ Setup and Installation

1.  **Prerequisites:**

    - Java JDK (version 17 or higher recommended)
    - Maven
    - MySQL or PostgreSQL database server
    - AWS S3 Bucket or Cloudinary account (for image storage)
    - Google Cloud Platform project and GitHub OAuth App credentials (for social login)
    - Email server credentials (e.g., Gmail SMTP)

2.  **Clone the repository:**

    ```bash
    git clone [https://github.com/your-username/smart-contact-manager.git](https://github.com/your-username/smart-contact-manager.git)
    cd smart-contact-manager
    ```

3.  **Configure the application:**

    - Open `src/main/resources/application.properties` (or `application.yml`).
    - Update the database connection details (URL, username, password).
    - Configure your cloud storage (AWS/Cloudinary) credentials.
    - Set up OAuth 2.0 client IDs and secrets for Google and GitHub.
    - Configure email sending properties.

4.  **Build the project:**

    ```bash
    mvn clean install
    ```

5.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    Alternatively, you can run the packaged JAR file from the `target` directory.

The application should now be accessible at `http://localhost:8080` (or the port configured in `application.properties`).

---

## 📖 Usage

1.  **Sign Up:** Create a new account using your email and password, or sign up with Google/GitHub.
2.  **Verify Email:** Click the verification link sent to your email.
3.  **Login:** Access your account.
4.  **Manage Contacts:**
    - Add new contacts with their details and a profile picture.
    - View your list of contacts.
    - Click on a contact to see more details, edit, or delete them.
    - Search for contacts using the search bar.
    - Mark your important contacts as favorites.
5.  **Send Emails:** Compose and send emails directly to your contacts from their detail page.
6.  **Export Data:** Export your contact list to an Excel file.
7.  **Profile:** Update your personal details and change your password.
8.  **Theme:** Switch between dark and light themes for comfortable viewing.
9.  **Feedback:** Share your thoughts and suggestions through the feedback form.

---

## 🤝 Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1.  Fork the repository.
2.  Create a new branch (`git checkout -b feature/your-feature-name`).
3.  Make your changes.
4.  Commit your changes (`git commit -m 'Add some feature'`).
5.  Push to the branch (`git push origin feature/your-feature-name`).
6.  Open a Pull Request.

Please make sure your code adheres to the project's coding standards.

---

## 📜 License

This project is licensed under the [MIT License](LICENSE) (or choose another license and create a LICENSE file).

---

**Happy Contact Managing!** 😊
