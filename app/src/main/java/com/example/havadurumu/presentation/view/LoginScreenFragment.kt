package com.example.havadurumu.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.havadurumu.R
import com.example.havadurumu.databinding.FragmentLoginScreenBinding

class LoginScreenFragment : Fragment() {
    private lateinit var binding: FragmentLoginScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginScreenBinding.inflate(inflater, container, false)

        binding.btnGiris.setOnClickListener {
            val weatherScreenFragment = WeatherScreenFragment()


            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, weatherScreenFragment) // Ensure fragment_container ID matches your container
                .addToBackStack(null) // Optional: Add to back stack to allow user to navigate back
                .commit()
        }

        return binding.root
    }
}
