package com.testdevlab.numbertapper.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.testdevlab.numbertapper.R
import com.testdevlab.numbertapper.common.openFragment
import com.testdevlab.numbertapper.databinding.FragmentMenuBinding
import com.testdevlab.numbertapper.ui.GameViewModel

class MenuFragment : Fragment () {
    private lateinit var binding: FragmentMenuBinding

    private val viewModel by activityViewModels<GameViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startNewGame.setOnClickListener {
            openFragment(R.id.navigation_game)
        }
        binding.showHighScores.setOnClickListener {
            openFragment(R.id.navigation_high_scores)
        }
    }
}