package hu.bme.aut.android.initiativetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.initiativetracker.R
import hu.bme.aut.android.initiativetracker.databinding.FragmentMainMenuBinding


class MainMenuFragment : Fragment() {
    private lateinit var binding : FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnList.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_listFragment)
        }
        binding.btnCounter.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_initiativeCounter)
        }
    }
}