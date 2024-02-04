package com.example.terramars.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.terramars.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var imageViewOverview: ImageView
    private lateinit var txtExplanation: TextView
    private lateinit var txtDate: TextView
    private var _binding: FragmentOverviewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(OverviewViewModel::class.java)

        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textView
      //  dashboardViewModel.text.observe(viewLifecycleOwner) {
     //       textView.text = it
     //   }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}