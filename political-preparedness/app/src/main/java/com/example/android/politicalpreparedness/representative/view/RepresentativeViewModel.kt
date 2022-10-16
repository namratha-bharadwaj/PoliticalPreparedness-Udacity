package com.example.android.politicalpreparedness.representative.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.models.Address
import com.example.android.politicalpreparedness.models.Representative
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepository
import kotlinx.coroutines.launch

class RepresentativeViewModel(val repository: PoliticalPreparednessRepository): ViewModel() {

    //TODO: Establish live data for representatives and address
    private var _representativesList = MutableLiveData<List<Representative>>()
    val representativesList: LiveData<List<Representative>>
        get() = _representativesList

    private var _address = MutableLiveData<Address>()
    val address: LiveData<Address>
        get() = _address

    /**
     *  The following code will prove helpful in constructing a representative from the API. This code combines the two nodes of the RepresentativeResponse into a single official :

    val (offices, officials) = getRepresentativesDeferred.await()
    _representatives.value = offices.flatMap { office -> office.getRepresentatives(officials) }

    Note: getRepresentatives in the above code represents the method used to fetch data from the API
    Note: _representatives in the above code represents the established mutable live data housing representatives

     */
    fun searchRepresentatives(address: String) {
        viewModelScope.launch {
            val representatives = repository.getRepresentatives(address)
            representatives?.let { repsList ->
                _representativesList.postValue(repsList)
            }
        }

    }

    fun setAddress(address: Address) {
        _address.value = address
    }

}
