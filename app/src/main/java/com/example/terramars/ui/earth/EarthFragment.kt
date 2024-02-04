package com.example.terramars.ui.earth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.terramars.databinding.FragmentEarthBinding

class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private lateinit var latitudeEditText: EditText
    private lateinit var longitudeEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var fetchImageButton: Button
    private lateinit var descriptionTextView: TextView
    private lateinit var earthImageView: ImageView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(EarthViewModel::class.java)

        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        val root: View = binding.root
        latitudeEditText = binding.editTextLatitude
        longitudeEditText = binding.editTextLongitude
        dateEditText = binding.editTextDate
        fetchImageButton = binding.buttonFetchEarthImage
        descriptionTextView = binding.textViewDescription
        earthImageView = binding.imageViewEarth

        val textView: TextView = binding.textEarth
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        fetchImageButton.setOnClickListener {

            val latitude = latitudeEditText.text.toString()
            val longitude = longitudeEditText.text.toString()
            val date = dateEditText.text.toString()

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}