package com.testdevlab.numbertapper.ui.game

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.testdevlab.numbertapper.R
import com.testdevlab.numbertapper.common.launchMain
import com.testdevlab.numbertapper.common.openFragment
import com.testdevlab.numbertapper.databinding.FragmentGameBinding
import com.testdevlab.numbertapper.ui.GameViewModel
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding

    private val viewModel by activityViewModels<GameViewModel>()

    private val adapter by lazy {
        GameAdapter { gamePiece ->
            Timber.d("Game piece clicked $gamePiece")
            viewModel.openPiece(gamePiece.value)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gameGrid.adapter = adapter
        binding.gameGrid.layoutManager = GridLayoutManager(requireContext(), 5)
        viewModel.startGame()

        launchMain {
            viewModel.gameTimer.collect { time ->
                binding.gameTimer.text = time
            }
        }

        launchMain {
            viewModel.gamePieces.collect { pieces ->
                adapter.gamePieces = pieces
            }
        }
        launchMain {
            viewModel.onGameOver.collect { time ->
                AlertDialog.Builder(context)
                    .setMessage(getString(R.string.game_over_template, time))
                    .setPositiveButton("Ok"){popup, _->
                        popup.dismiss()
                        openFragment(R.id.navigation_menu)
                    }
                    .setCancelable(false)
                    .show()
            }
        }

        launchMain {
            viewModel.nextButton.collect { nextButton ->
                binding.nextGamePiece.text = getString(R.string.next_button_template, nextButton)
            }
        }
    }
}
