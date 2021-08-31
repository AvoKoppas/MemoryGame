package com.testdevlab.memorygame.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.testdevlab.memorygame.R
import com.testdevlab.memorygame.databinding.FragmentMenuBinding
import com.testdevlab.memorygame.ui.GameViewModel
import com.testdevlab.memorygame.common.openFragment

class MenuFragment : Fragment () {
    private lateinit var binding: FragmentMenuBinding

    private val viewModel by activityViewModels<GameViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.easy.setOnClickListener {
            openFragment(R.id.navigation_easy)
        }
        binding.medium.setOnClickListener {
            openFragment(R.id.navigation_medium)
        }
        binding.hard.setOnClickListener {
            openFragment(R.id.navigation_hard)
        }
        binding.showHighScores.setOnClickListener {
            openFragment(R.id.navigation_high_scores)
        }
    }
}