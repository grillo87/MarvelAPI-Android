package com.josegrillo.marvelapi.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<ViewBindingType : ViewBinding> : Fragment() {

    private var _binding: ViewBindingType? = null
    protected val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater, container)
        return requireNotNull(_binding).root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewElements()
        setObservers()
    }

    abstract fun configureViewElements()
    abstract fun setObservers()
    abstract fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBindingType
}