package com.example.android.politicalpreparedness.election.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.network.api.CivicsApi
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepositoryImpl
import com.example.android.politicalpreparedness.representative.view.RepresentativeViewModel
import com.example.android.politicalpreparedness.representative.view.RepresentativeViewModelFactory

class ElectionsFragment: Fragment() {

    private lateinit var binding: FragmentElectionBinding

    private val electionsViewModel by lazy {
        ViewModelProvider(
            this,
            ElectionsViewModelFactory(
                PoliticalPreparednessRepositoryImpl(CivicsApi.retrofitService)
            )
        )[ElectionsViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentElectionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }


}