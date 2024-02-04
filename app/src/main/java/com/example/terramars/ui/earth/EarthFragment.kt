package com.example.terramars.ui.earth

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.terramars.api.callAPI
import com.example.terramars.databinding.FragmentEarthBinding
import com.squareup.picasso.Picasso
import java.io.ByteArrayInputStream
import java.nio.ByteBuffer

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
        var repo = callAPI()
        fetchImageButton.setOnClickListener {

            val latitude = latitudeEditText.text.toString().toDoubleOrNull()
            val longitude = longitudeEditText.text.toString().toDoubleOrNull()
            val date = dateEditText.text.toString()

            if (latitude != null && longitude != null) {
                repo.getEarthImage(latitude, longitude, date) { imageBase64 ->
                    if (imageBase64 != null) {

                        val imageBytes = Base64.decode(imageBase64, Base64.DEFAULT)

                        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                        earthImageView.setImageBitmap(bitmap)

                        descriptionTextView.text = "Description de l'image (à récupérer de l'API Earth)"
                        earthImageView.visibility = View.VISIBLE
                    } else {
                        descriptionTextView.text = "Impossible de récupérer l'image de la Terre pour la date spécifiée."
                    }
                }

            } else {
                descriptionTextView.text = "Latitude et longitude doivent être des nombres valides."
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
