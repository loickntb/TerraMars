package com.example.terramars.ui.overview
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.terramars.api.callAPI
import com.example.terramars.api.ApodData
import com.example.terramars.databinding.FragmentOverviewBinding
import com.squareup.picasso.Picasso

class OverviewFragment : Fragment() {

    private lateinit var imageViewOverview: ImageView
    private lateinit var txtExplanation: TextView
    private lateinit var txtTitle: TextView
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        txtTitle = binding.txtPicoftheday
        imageViewOverview = binding.imageViewOverview
        txtExplanation = binding.txtExplanation

        fetchApodData()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchApodData() {
        val repo = callAPI()
        repo.getApodData { apodResponse ->
            if (apodResponse != null) {
                updateUI(apodResponse)
            } else {
               println("Erreur")
            }
        }
    }

    private fun updateUI(apodResponse: ApodData.ApodResponse) {
        Picasso.get()
            .load(apodResponse.url)
            .into(imageViewOverview)

        txtExplanation.text = apodResponse.explanation
        txtTitle.text = apodResponse.title
    }
}