package com.example.android.politicalpreparedness.election.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter
import com.example.android.politicalpreparedness.election.adapter.IElectionItemClickListener
import com.example.android.politicalpreparedness.models.Election
import com.example.android.politicalpreparedness.network.api.CivicsApi
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepositoryImpl
import com.example.android.politicalpreparedness.representative.adapter.RepresentativeListAdapter
import com.example.android.politicalpreparedness.representative.view.RepresentativeViewModel
import com.example.android.politicalpreparedness.representative.view.RepresentativeViewModelFactory

class ElectionsFragment: Fragment(), IElectionItemClickListener {

    private lateinit var binding: FragmentElectionBinding

    private val electionsViewModel by lazy {
        ViewModelProvider(
            this,
            ElectionsViewModelFactory(
                activity as Context
            )
        )[ElectionsViewModel::class.java]
    }

    var clickListener: IElectionItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupClickListener()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentElectionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        setupRecyclerViews()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        electionsViewModel.getUpcomingElections()
        electionsViewModel.getSavedElections()
    }

    private fun setupClickListener() {
        if (clickListener == null) {
            clickListener = this
        }
    }

    private fun setupRecyclerViews() {
        val upcomingElectionsAdapter = clickListener?.let {
            ElectionListAdapter(it)
        }
        val savedElectionsAdapter = clickListener?.let {
            ElectionListAdapter(it)
        }
        binding.upcomingElectionsRecyclerView.adapter = upcomingElectionsAdapter
        binding.savedElectionsRecyclerView.adapter = savedElectionsAdapter

        electionsViewModel.upcomingElectionsList.observe(viewLifecycleOwner) { upcomingElectionList ->
            upcomingElectionsAdapter?.let {
                it.submitList(upcomingElectionList)
            }
        }

        electionsViewModel.savedElectionsList.observe(viewLifecycleOwner) { savedElectionList ->
            savedElectionsAdapter?.let {
                it.submitList(savedElectionList)
            }
        }
    }

    override fun onClick(electionItem: Election) {
        findNavController().navigate(ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(electionItem))
    }


}