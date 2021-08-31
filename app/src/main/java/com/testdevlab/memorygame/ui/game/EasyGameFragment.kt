package com.testdevlab.memorygame.ui.game

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.HORIZONTAL
import android.widget.GridLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.TableInfo
import com.testdevlab.memorygame.databinding.FragmentGameBinding
import com.testdevlab.memorygame.ui.GameViewModel
import com.testdevlab.memorygame.R
import com.testdevlab.memorygame.common.launchMain
import com.testdevlab.memorygame.common.openFragment
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class EasyGameFragment : Fragment() {
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
        binding.gameGrid.layoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        viewModel.startGame(4)

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
                    .setPositiveButton("Ok") { popup, _ ->
                        popup.dismiss()
                        openFragment(R.id.navigation_menu)
                    }
                    .setCancelable(false)
                    .show()
            }
        }

        launchMain {
            viewModel.nextButton.collect { nextButton ->
                binding.score.text = getString(R.string.next_button_template, nextButton)
            }
        }
    }
}
