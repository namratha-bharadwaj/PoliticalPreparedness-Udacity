package com.example.android.politicalpreparedness.representative.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.databinding.FragmentRepresentativeBinding
import com.example.android.politicalpreparedness.network.api.CivicsApi
import com.example.android.politicalpreparedness.models.Address
import com.example.android.politicalpreparedness.representative.adapter.RepresentativeListAdapter
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepositoryImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.lang.Exception
import java.util.Locale

class RepresentativeFragment : Fragment() {

    companion object {
        //Done: Add Constant for Location request
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val representativeViewModel by lazy {
        ViewModelProvider(
            this,
            RepresentativeViewModelFactory(
                PoliticalPreparednessRepositoryImpl(CivicsApi.retrofitService)
            )
        )[RepresentativeViewModel::class.java]
    }
    private lateinit var binding: FragmentRepresentativeBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //Complete: Establish bindings
        binding = FragmentRepresentativeBinding.inflate(inflater, container, false)
        binding.viewModel = representativeViewModel
        binding.lifecycleOwner = this

        setupRecyclerView()

        //TODO: Establish button listeners for field and location search
        binding.searchButton.setOnClickListener {
            val address1 = binding.addressLine1EditText.text
            val address2 = binding.addressLine2EditText.text
            val state =
                binding.stateSpinner.getItemAtPosition(binding.stateSpinner.selectedItemPosition)
            val city = binding.cityEditText.text
            val zip = binding.zipcodeEditText.text
            if (address1 != null && state != null && city != null && zip != null) {
                val address = Address(
                    line1 = address1.toString(),
                    line2 = address2?.toString(),
                    state = state.toString(),
                    city = city.toString(),
                    zip = zip.toString()
                )
                representativeViewModel.searchRepresentatives(address.toFormattedString())
            } else {
                Toast.makeText(context, "Please check your info and try again.", Toast.LENGTH_SHORT).show()
            }

        }

        binding.useLocationButton.setOnClickListener {
            if (checkLocationPermissions()) {
                getLocation()
            }
        }

        return binding.root
    }

    private fun checkLocationPermissions(): Boolean {
        return if (isPermissionGranted()) {
            true
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
            false
        }
    }

    private fun isPermissionGranted() : Boolean {
        //Done: Check if permission is already granted and return (true = granted, false = denied/other)
        return (ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun setupRecyclerView() {
        //TODO: Define and assign Representative adapter
        val adapter = RepresentativeListAdapter()
        binding.myRepresentativesRecyclerView.adapter = adapter

        //TODO: Populate Representative adapter
        representativeViewModel.representativesList.observe(viewLifecycleOwner) { repList ->
            adapter.submitList(repList)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //Done: Handle location permission result to get location on permission granted
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLocation()
                }
                return
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        //Done: Get location from LocationServices
        //Done: The geoCodeLocation method is a helper function to change the lat/long location to a human readable street address
        //Done: Get location from LocationServices
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity as Activity)

        //Done: The geoCodeLocation method is a helper function to change the lat/long location to a human readable street address
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            try {
                val address = geoCodeLocation(location)
                representativeViewModel.setAddress(address)
            } catch (e: Exception) {
                Log.e("Representative", e.localizedMessage)
            }
        }
    }

    private fun geoCodeLocation(location: Location): Address {
        val geocoder = Geocoder(context, Locale.getDefault())
        return geocoder.getFromLocation(location.latitude, location.longitude, 1)
                .map { address ->
                    Address(address.thoroughfare, address.subThoroughfare, address.locality, address.adminArea, address.postalCode)
                }
                .first()
    }

//    private fun hideKeyboard() {
//        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
//    }

}