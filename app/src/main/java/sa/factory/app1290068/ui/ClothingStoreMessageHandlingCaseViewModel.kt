package sa.factory.app1290068.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290068.data.ClothingStoreMessageHandlingCaseRepository
import sa.factory.app1290068.model.ClothingStoreMessageHandlingCase
import sa.factory.app1290068.model.FacebookInboxLeadTask
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClothingStoreMessageHandlingCaseViewModel(
    private val repository: ClothingStoreMessageHandlingCaseRepository
) : ViewModel() {
    val catalog: StateFlow<List<FacebookInboxLeadTask>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<ClothingStoreMessageHandlingCase?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: ClothingStoreMessageHandlingCase) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
