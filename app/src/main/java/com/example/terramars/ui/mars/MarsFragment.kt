package com.example.terramars.ui.mars

import MarsImageAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.terramars.databinding.FragmentMarsBinding

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private lateinit var datePicker: DatePicker
    private lateinit var btnShowImages: Button
    private lateinit var viewModel: MarsViewModel
    private lateinit var recyclerView: RecyclerView

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
        recyclerView = binding.imageRecyclerView

        datePicker.visibility = View.GONE

        val imageUrls = listOf("https://mars.nasa.gov/msl-raw-images/msss/01082/mcam/1082MR0047611000600326E01_DXXX.jpg","https://mars.nasa.gov/msl-raw-images/msss/02739/mcam/2739MR0143580001200802C00_DXXX.jpg", "https://mars.nasa.gov/msl-raw-images/msss/01082/mcam/1082MR0047610990600325E01_DXXX.jpg", "https://mars.nasa.gov/msl-raw-images/msss/01082/mcam/1082MR0047611310600357E01_DXXX.jpg")
        val adapter = MarsImageAdapter(imageUrls)
        println(adapter.itemCount)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        btnShowImages.setOnClickListener {
            try {

                isDatePickerVisible = !isDatePickerVisible
                if (isDatePickerVisible) {
                    btnShowImages.text = "Afficher les image de mars !"
                    datePicker.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    datePicker.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE

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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}