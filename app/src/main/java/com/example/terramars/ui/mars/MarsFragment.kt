package com.example.terramars.ui.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.terramars.databinding.FragmentMarsBinding

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private lateinit var datePicker: DatePicker
    private lateinit var btnShowImages: Button
    private lateinit var viewModel: MarsViewModel

    private var isDatePickerVisible = false
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(MarsViewModel::class.java)

        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMars
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        datePicker = binding.datePickerMars
        btnShowImages = binding.btnShowImages

        datePicker.visibility = View.GONE

        btnShowImages.setOnClickListener {
            try {

                isDatePickerVisible = !isDatePickerVisible
                if (isDatePickerVisible) {
                    datePicker.visibility = View.VISIBLE
                } else {
                    datePicker.visibility = View.GONE
                }

                val selectedDate = "${datePicker.year}-${datePicker.month + 1}-${datePicker.dayOfMonth}"
                notificationsViewModel.updateSelectedDate(selectedDate)

                textView.text = "Date sélectionnée : $selectedDate"
                println(textView.text)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return root
    }

}