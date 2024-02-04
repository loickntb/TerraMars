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

        /*  val imageUrls = listOf("https://mars.nasa.gov/msl-raw-images/msss/01082/mcam/1082MR0047611000600326E01_DXXX.jpg","https://mars.nasa.gov/msl-raw-images/msss/02739/mcam/2739MR0143580001200802C00_DXXX.jpg", "https://mars.nasa.gov/msl-raw-images/msss/01082/mcam/1082MR0047610990600325E01_DXXX.jpg", "https://mars.nasa.gov/msl-raw-images/msss/01082/mcam/1082MR0047611310600357E01_DXXX.jpg")
          val adapter = MarsImageAdapter(imageUrls)
          println(adapter.itemCount)
          recyclerView.adapter = adapter
          recyclerView.layoutManager = LinearLayoutManager(context)
          recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)*/
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
                        // Créez l'adaptateur
                        val adapter = MarsImageAdapter(imageUrls)
                        // Définissez l'adaptateur
                        recyclerView.adapter = adapter

                        // Notifiez à l'adaptateur que l'ensemble de données a changé
                        adapter.notifyDataSetChanged()
                    } else {
                        // Gérez le cas où imageUrls est null (échec de l'appel API)
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