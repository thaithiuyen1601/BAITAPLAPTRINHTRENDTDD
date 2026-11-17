
package com.example.studentmvvm.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.studentmvvm.databinding.DialogAddStudentBinding
import com.example.studentmvvm.model.Student
import com.example.studentmvvm.viewmodel.StudentViewModel

class AddStudentDialogFragment : DialogFragment() {

    private val viewModel: StudentViewModel by activityViewModels {
        (requireActivity() as MainActivity).studentViewModelFactory
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding = DialogAddStudentBinding.inflate(
            layoutInflater
        )

        return AlertDialog.Builder(requireContext())
            .setTitle("Thêm sinh viên mới")
            .setView(binding.root)
            .setPositiveButton("Lưu") { _, _ ->

                val name = binding.etName.text.toString().trim()
                val code = binding.etCode.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()
                val gpa = binding.etGpa.text.toString()
                    .trim().toDoubleOrNull()

                if (name.isEmpty() ||
                    code.isEmpty() ||
                    email.isEmpty() ||
                    gpa == null
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Vui lòng nhập đầy đủ thông tin",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (
                    !android.util.Patterns.EMAIL_ADDRESS
                        .matcher(email).matches() ||
                    gpa !in 0.0..4.0
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Email hoặc GPA không hợp lệ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val student = Student(
                        fullName = name,
                        studentCode = code,
                        email = email,
                        gpa = gpa
                    )

                    viewModel.insert(student)

                    Toast.makeText(
                        requireContext(),
                        "Đã thêm sinh viên",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .setNegativeButton("Hủy", null)
            .create()
    }
}
