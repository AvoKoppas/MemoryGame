package com.testdevlab.numbertapper.common

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.testdevlab.numbertapper.R

fun Fragment.openFragment(id: Int) = activity?.findNavController(R.id.navigation_host_fragment)?.navigate(id)