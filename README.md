# Kostantsa-Bocheva-employees
Sirma task - identifies the pair of employees who have worked together on common projects for the **longest total overlapping time**.

## 📌 Requirements

1. Input File: CSV with format: EmpID, ProjectID, DateFrom, DateTo

2. Flexible date formats using Apache Commons (DateUtils.parseDate)

3. NULL in DateTo is treated as today.

4. Goal: Identify the pair of employees who worked together on the same project for the longest total period (in days).

---

## 🛠️ Setup

0. You need to have:
- Java 21 or higher
- Maven (for dependency management)
- Minimum recommended heap size: 1024 MB (1 GB)
- For medium workload: 2048 MB (2 GB)
- For large workload: 4 GB or more
 
1. Clone the repo:
   ```bash
   git clone https://github.com/koseto/Kostantsa-Bocheva-employees.git
   
2. Run the program
**Using an IDE:**
- Open the project in your IDE (IntelliJ IDEA, Eclipse, etc.)
- Run the `Main` class with the program arguments set to the path of your CSV file.
  (test files are added to src/test/resources/sample_from_task.csv)


TODO:
handle larger file - i.e. > 100 000 rows, 1 million rows, etc

   

