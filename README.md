# Student Management System

A Java-based console application mini-project built with **Maven** to track, organize, and manage student records efficiently. 

---

## 🚀 Features

The system offers an interactive command-line interface to execute the following administrative operations:

*   **Add student** – Register new students with distinct details.
*   **View all students** – Display a complete roster of existing records.
*   **Search capabilities** – Instantly look up entries by specific **ID** or **Name**.
*   **Modify records** – Dynamically **Update** or **Delete** student profiles.
*   **Data Sorting** – Organize lists by **Marks (high to low)** or **Name (A-Z)**.
*   **Analytics Dashboard** – View basic **Statistics** including class average and the top performer.

---

## 💻 System Preview

When you execute the application, you will interact with the following console menu dashboard:

```text
===== STUDENT MANAGEMENT SYSTEM =====
 1. Add student
 2. View all students
 3. Search by ID
 4. Search by name
 5. Update student
 6. Delete student
 7. Sort by marks (high to low)
 8. Sort by name (A-Z)
 9. Statistics (topper & average)
10. Exit
Enter choice:
```

---

## 🛠️ Setup & Execution

### Prerequisites
*   **Java JDK 17** or higher
*   **Apache Maven** (or use the included Maven wrapper)

### Method 1: Running via IntelliJ IDEA (Recommended)
1. Clone the repository to your local environment:
   ```bash
   git clone https://github.com
   ```
2. Open IntelliJ IDEA.
3. Go to **File > Open** and select the cloned `studentMgmt` folder.
4. IntelliJ will automatically detect the `pom.xml` file and load it as a Maven project. Let the dependencies sync.
5. Locate your main class file containing the `public static void main(String[] args)` method, right-click it, and select **Run**.

### Method 2: Running via Terminal / Command Line
Navigate directly into the project root directory and execute the project using Maven:

*   **Compile the project:**
    ```bash
    mvn clean compile
    ```
*   **Execute the application:**
    ```bash
    mvn exec:java "-Dexec.mainClass=com.example.Main"
    ```
    *(Note: Replace `com.example.Main` with your actual main class path package).*
