package com.example.notification_lighting.features.notification_lighting.presentation.screens.exclude_app_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetExcludedPackagesUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.SetExcludedPackagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExcludeAppViewModel @Inject constructor(
    private val getExcludedPackagesUseCase: GetExcludedPackagesUseCase,
    private val setExcludedPackagesUseCase: SetExcludedPackagesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ExcludeAppState())
    val state: State<ExcludeAppState> = _state

    init {
        getExcludedPackages()
    }

    private fun getExcludedPackages() {
        getExcludedPackagesUseCase.invoke().onEach { packages ->
            _state.value = state.value.copy(
                packages = packages
            )
        }.launchIn(viewModelScope)
    }

    fun setExcludedPackages(packages: String) {
        viewModelScope.launch {
            setExcludedPackagesUseCase.invoke(packages = packages)
        }
    }

}