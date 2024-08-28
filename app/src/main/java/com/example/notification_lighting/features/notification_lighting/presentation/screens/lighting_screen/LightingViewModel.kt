package com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetAnimationDurationUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetAnimationFrequencyUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetBorderThicknessUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetIconSizeUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetScreenCornerRadiusSizeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LightingViewModel @Inject constructor(
    private val getScreenCornerRadiusSizeUseCase: GetScreenCornerRadiusSizeUseCase,
    private val getAnimationFrequencyUseCase: GetAnimationFrequencyUseCase,
    private val getAnimationDurationUseCase: GetAnimationDurationUseCase,
    private val getBorderThicknessUseCase: GetBorderThicknessUseCase,
    private val getIconSizeUseCase: GetIconSizeUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(LightingState())
    val state: State<LightingState> = _state

    init {
        getScreenCornerRadiusSize()
        getAnimationFrequency()
        getAnimationDuration()
        getBorderThickness()
        getIconSize()
    }

    private fun getScreenCornerRadiusSize() {
        getScreenCornerRadiusSizeUseCase.invoke().onEach { size ->
            _state.value = state.value.copy(
                screenCornerRadiusSize = size
            )
        }.launchIn(viewModelScope)
    }

    private fun getAnimationFrequency() {
        getAnimationFrequencyUseCase.invoke().onEach { animationFrequency ->
            _state.value = state.value.copy(
                animationFrequency = animationFrequency
            )
        }.launchIn(viewModelScope)
    }

    private fun getAnimationDuration() {
        getAnimationDurationUseCase.invoke().onEach { duration ->
            _state.value = state.value.copy(
                animationDuration = duration
            )
        }.launchIn(viewModelScope)
    }

    private fun getBorderThickness() {
        getBorderThicknessUseCase.invoke().onEach { thickness ->
            _state.value = state.value.copy(
                borderThickness = thickness
            )
        }.launchIn(viewModelScope)
    }

    private fun getIconSize() {
        getIconSizeUseCase.invoke().onEach { size ->
            _state.value = state.value.copy(
                iconSize = size
            )
        }.launchIn(viewModelScope)
    }
}