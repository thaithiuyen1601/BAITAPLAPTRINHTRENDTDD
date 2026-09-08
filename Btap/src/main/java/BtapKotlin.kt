package com.example.btap

fun main() {
    // 1. Thong tin sinh vien co san
    val name = "Thai Thi Uyen"
    val studentId = "2415053122247"

    // 2. Nhap cac cot diem tu ban phim
    print("Nhap diem Math: ")
    val math = readln().toDoubleOrNull() ?: 0.0

    print("Nhap diem Programming: ")
    val programming = readln().toDoubleOrNull() ?: 0.0

    print("Nhap diem Database: ")
    val database = readln().toDoubleOrNull() ?: 0.0

    // 3. Thuc hien tinh toan
    val totalScore = math + programming + database
    val averageScore = totalScore / 3
    val highestScore = maxOf(math, programming, database)
    val isPassed = averageScore >= 5.0

    // 4. In ra man hinh
    println("\n=================================")
    println("THONG TIN KET QUA CUA SINH VIEN")
    println("Sinh vien: $name - MSSV: $studentId")
    println("Diem thanh phan: Math($math), Programming($programming), Database($database)")
    println("---------------------------------")
    println("Tong diem: $totalScore")
    println("Diem trung binh (GPA): ${String.format("%.2f", averageScore)}")
    println("Diem cao nhat: $highestScore")
    println("Sinh vien co dat khong?: ${if (isPassed) "Co (Dat)" else "Khong (Rot)"}")
    println("=================================")
}