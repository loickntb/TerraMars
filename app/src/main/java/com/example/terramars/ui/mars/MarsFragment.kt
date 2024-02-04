package com.example.terramars.ui.mars

import MarsImageAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.terramars.api.callAPI
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
        var repo = callAPI()
        datePicker.visibility = View.GONE

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        btnShowImages.setOnClickListener {
            try {
                isDatePickerVisible = !isDatePickerVisible
                if (isDatePickerVisible) {
                    btnShowImages.text = "Afficher les images de mars !"
                    datePicker.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    datePicker.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }

                val selectedDate = "${datePicker.year}-${datePicker.month + 1}-${datePicker.dayOfMonth}"
                notificationsViewModel.updateSelectedDate(selectedDate)
                textView.text = "Date sélectionnée : $selectedDate"

                repo.getMarsPhotos(selectedDate) { imageUrls ->
                    if (imageUrls != null) {

                        val adapter = MarsImageAdapter(imageUrls)
                        recyclerView.adapter = adapter

                        adapter.notifyDataSetChanged()
                    } else {

                        Log.e("MarsFragment", "Échec du chargement des photos de Mars.")
                        Toast.makeText(context, "Erreur lors du chargement des photos de Mars", Toast.LENGTH_SHORT).show()
                    }
                }
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