package com.testdevlab.memorygame.ui.highscores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.testdevlab.memorygame.R
import com.testdevlab.memorygame.databinding.FragmentHighscoresBinding
import com.testdevlab.memorygame.ui.GameViewModel
import com.testdevlab.memorygame.common.launchMain
import com.testdevlab.memorygame.common.openFragment
import kotlinx.coroutines.flow.collect

class HighScoresFragment : Fragment() {
    private lateinit var binding: FragmentHighscoresBinding

    private val viewModel by activityViewModels<GameViewModel>()
    private val adapter by lazy { HighScoreAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHighscoresBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.highScoreList.adapter = adapter

        binding.closeHighScores.setOnClickListener{
            openFragment(R.id.navigation_menu)
        }

        launchMain {
            viewModel.highScores.collect { highscores ->
                binding.emptyHighScore.visibility = if (highscores.isEmpty()) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
                adapter.highScores = highscores
            }
        }
    }
}