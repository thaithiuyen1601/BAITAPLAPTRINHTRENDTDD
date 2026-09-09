package com.example.btap

// 1. Định nghĩa Data Class cho Sinh viên
data class Student(
    val studentId: String,
    val fullName: String,
    val age: Int,
    val major: String,
    val GPA: Double
)

fun main() {
    // 2. Khởi tạo danh sách sinh viên với 5 sinh viên mẫu riêng biệt
    val studentList = mutableListOf(
        Student("2415051222137", "Le Duy Khanh", 20, "Mechatronics Engineering", 8.6),
        Student("2415053245343", "Hoang Ngoc Huy", 20, "Data Science", 7.4),
        Student("2415053124918", "Thai Khanh Chi", 19, "Business Administration", 4.8),
        Student("2315053444409", "Phan Dan Le", 21, "Software Engineering", 9.1),
        Student("2415052155530", "Le Thi Thuy Tien", 20, "Graphic Design", 6.5)
    )

    var choice: Int
    do {
        // 3. Hiển thị Menu cơ bản theo đề bài
        println("\n========== STUDENT MANAGEMENT ==========")
        println("1. Add student")
        println("2. Display all students")
        println("3. Search student (Tim kiem nang cao & Thong ke)")
        println("4. Calculate average GPA")
        println("5. Find student with highest GPA")
        println("6. Remove student")
        println("0. Exit")
        println("========================================")
        print("Choose: ")

        choice = readln().toIntOrNull() ?: -1

        when (choice) {
            1 -> {
                println("\n--- 1. ADD STUDENT ---")
                print("Enter Student ID: ")
                val id = readln()
                print("Enter Full Name: ")
                val name = readln()
                print("Enter Age: ")
                val age = readln().toIntOrNull() ?: 0
                print("Enter Major: ")
                val major = readln()
                print("Enter GPA: ")
                val gpa = readln().toDoubleOrNull() ?: 0.0

                studentList.add(Student(id, name, age, major, gpa))
                println("-> Add student successfully!")
            }
            2 -> {
                println("\n--- 2. DISPLAY ALL STUDENTS ---")
                if (studentList.isEmpty()) {
                    println("List is empty!")
                } else {
                    displayStudents(studentList)
                }
            }
            3 -> {
                // Tích hợp các yêu cầu tìm kiếm, đếm, sắp xếp nâng cao
                subSearchAndStatisticsMenu(studentList)
            }
            4 -> {
                println("\n--- 4. CALCULATE AVERAGE GPA ---")
                if (studentList.isNotEmpty()) {
                    val avgAll = studentList.map { it.GPA }.average()
                    println("Overall Average GPA: ${String.format("%.2f", avgAll)}")
                } else {
                    println("No students available.")
                }
            }
            5 -> {
                println("\n--- 5. FIND STUDENT WITH HIGHEST GPA ---")
                val highest = studentList.maxByOrNull { it.GPA }
                if (highest != null) {
                    println("Student with highest GPA:")
                    println(highest)
                } else {
                    println("No students available.")
                }
            }
            6 -> {
                println("\n--- 6. REMOVE STUDENT ---")
                print("Enter Student ID to remove: ")
                val idToRemove = readln()
                val removed = studentList.removeIf { it.studentId == idToRemove }
                if (removed) {
                    println("-> Removed student with ID: $idToRemove")
                } else {
                    println("-> Student ID not found!")
                }
            }
            0 -> println("Exiting program. Goodbye!")
            else -> println("Invalid choice! Please choose from 0 to 6.")
        }
    } while (choice != 0)
}

// Hàm hỗ trợ hiển thị danh sách
fun displayStudents(list: List<Student>) {
    println(String.format("%-10s | %-22s | %-4s | %-24s | %-4s", "ID", "Full Name", "Age", "Major", "GPA"))
    println("-".repeat(75))
    for (s in list) {
        println(String.format("%-10s | %-22s | %-4d | %-24s | %-4.1f", s.studentId, s.fullName, s.age, s.major, s.GPA))
    }
}

// Menu con phục vụ các yêu cầu thống kê, tìm kiếm và sắp xếp mở rộng
fun subSearchAndStatisticsMenu(list: MutableList<Student>) {
    println("\n--- 3. SEARCH & ADVANCED FEATURES ---")
    println("1. Count students with GPA >= 8.0 & GPA < 5.0")
    println("2. Calculate average GPA by specific major")
    println("3. Find oldest student")
    println("4. Find students with GPA between 7.0 and 8.5")
    println("5. Find students by major")
    println("6. Find students by partial name")
    println("7. Sort students by GPA descending & Show top 3 highest GPA")
    println("8. Sort students by age & Sort by name")
    print("Choose sub-option: ")

    when (readln().toIntOrNull()) {
        1 -> {
            val countGood = list.count { it.GPA >= 8.0 }
            val countWeak = list.count { it.GPA < 5.0 }
            println("-> Number of students with GPA >= 8.0: $countGood")
            println("-> Number of students with GPA < 5.0: $countWeak")
        }
        2 -> {
            print("Enter major name to calculate average GPA: ")
            val targetMajor = readln()
            val filtered = list.filter { it.major.equals(targetMajor, ignoreCase = true) }
            if (filtered.isNotEmpty()) {
                val avgMajor = filtered.map { it.GPA }.average()
                println("-> Average GPA for major '$targetMajor': ${String.format("%.2f", avgMajor)}")
            } else {
                println("-> No students found in this major.")
            }
        }
        3 -> {
            val oldest = list.maxByOrNull { it.age }
            if (oldest != null) {
                println("-> Oldest student: $oldest")
            }
        }
        4 -> {
            val rangeList = list.filter { it.GPA in 7.0..8.5 }
            println("-> Students with GPA from 7.0 to 8.5:")
            displayStudents(rangeList)
        }
        5 -> {
            print("Enter major to search: ")
            val m = readln()
            val majorList = list.filter { it.major.equals(m, ignoreCase = true) }
            displayStudents(majorList)
        }
        6 -> {
            print("Enter partial name to search: ")
            val keyword = readln()
            val nameList = list.filter { it.fullName.contains(keyword, ignoreCase = true) }
            displayStudents(nameList)
        }
        7 -> {
            val sortedGpa = list.sortedByDescending { it.GPA }
            println("\n-> Students sorted by GPA descending:")
            displayStudents(sortedGpa)

            println("\n-> Top 3 students with highest GPA:")
            displayStudents(sortedGpa.take(3))
        }
        8 -> {
            println("\n-> Sorted by Age (Ascending):")
            displayStudents(list.sortedBy { it.age })

            println("\n-> Sorted by Name (Alphabet):")
            displayStudents(list.sortedBy { it.fullName })
        }
        else -> println("Invalid sub-option!")
    }
}