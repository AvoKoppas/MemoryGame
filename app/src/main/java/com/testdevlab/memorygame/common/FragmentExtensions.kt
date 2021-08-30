package com.testdevlab.memorygame.common

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.testdevlab.memorygame.R

fun Fragment.openFragment(id: Int) = activity?.findNavController(R.id.navigation_host_fragment)?.navigate(id)