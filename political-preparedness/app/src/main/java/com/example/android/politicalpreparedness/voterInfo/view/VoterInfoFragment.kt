package com.example.android.politicalpreparedness.voterInfo.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.databinding.FragmentVoterInfoBinding
import com.example.android.politicalpreparedness.network.api.CivicsApi
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepositoryImpl
import com.example.android.politicalpreparedness.representative.view.RepresentativeViewModel
import com.example.android.politicalpreparedness.representative.view.RepresentativeViewModelFactory

class VoterInfoFragment : Fragment() {

    private lateinit var binding: FragmentVoterInfoBinding

    private val voterInfoViewModel by lazy {
        ViewModelProvider(
            this,
            VoterInfoViewModelFactory(
                activity as Context
            )
        )[VoterInfoViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = FragmentVoterInfoBinding.inflate(inflater, container, false).apply {
            viewModel = voterInfoViewModel
            lifecycleOwner = viewLifecycleOwner
            this.followUnfollowButton.setOnClickListener {
                voterInfoViewModel.followUnfollowElection()
            }
        }

        val args = VoterInfoFragmentArgs.fromBundle(requireArguments())
        voterInfoViewModel.checkElection(args.argElection)

        voterInfoViewModel.getVoterInfo(args.argElection)

        voterInfoViewModel.url.observe(viewLifecycleOwner) {
            openWebUrl(it)
        }

        return binding.root
    }

    private fun openWebUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}