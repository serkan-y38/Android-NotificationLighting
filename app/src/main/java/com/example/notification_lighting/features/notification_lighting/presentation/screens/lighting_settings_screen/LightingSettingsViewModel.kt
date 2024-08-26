package com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetAnimationDurationUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetAnimationFrequencyUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetBorderThicknessUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetIconSizeUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.GetScreenCornerRadiusSizeUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.SetAnimationDurationUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.SetAnimationFrequencyUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.SetBorderThicknessUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.SetIconSizeUseCase
import com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store.SetScreenCornerRadiusSizeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LightingSettingsViewModel @Inject constructor(
    private val getScreenCornerRadiusSizeUseCase: GetScreenCornerRadiusSizeUseCase,
    private val setScreenCornerRadiusSizeUseCase: SetScreenCornerRadiusSizeUseCase,
    private val getAnimationFrequencyUseCase: GetAnimationFrequencyUseCase,
    private val setAnimationFrequencyUseCase: SetAnimationFrequencyUseCase,
    private val getAnimationDurationUseCase: GetAnimationDurationUseCase,
    private val setAnimationDurationUseCase: SetAnimationDurationUseCase,
    private val getBorderThicknessUseCase: GetBorderThicknessUseCase,
    private val setBorderThicknessUseCase: SetBorderThicknessUseCase,
    private val getIconSizeUseCase: GetIconSizeUseCase,
    private val setIconSizeUseCase: SetIconSizeUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SelectedOptionsState())
    val state: State<SelectedOptionsState> = _state

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

    fun setScreenCornerRadiusSize(size: Int) {
        viewModelScope.launch {
            setScreenCornerRadiusSizeUseCase.invoke(size = size)
        }
    }

    private fun getAnimationFrequency() {
        getAnimationFrequencyUseCase.invoke().onEach { animationFrequency ->
            _state.value = state.value.copy(
                animationFrequency = animationFrequency
            )
        }.launchIn(viewModelScope)
    }

    fun setAnimationFrequency(repetitionNum: Int) {
        viewModelScope.launch {
            setAnimationFrequencyUseCase.invoke(repetitionNum = repetitionNum)
        }
    }

    private fun getAnimationDuration() {
        getAnimationDurationUseCase.invoke().onEach { duration ->
            _state.value = state.value.copy(
                animationDuration = duration
            )
        }.launchIn(viewModelScope)
    }

    fun setAnimationDuration(duration: Int) {
        viewModelScope.launch {
            setAnimationDurationUseCase.invoke(duration = duration)
        }
    }

    private fun getBorderThickness() {
        getBorderThicknessUseCase.invoke().onEach { thickness ->
            _state.value = state.value.copy(
                borderThickness = thickness
            )
        }.launchIn(viewModelScope)
    }

    fun setBorderThickness(thickness: Int) {
        viewModelScope.launch {
            setBorderThicknessUseCase.invoke(thickness = thickness)
        }
    }

    private fun getIconSize() {
        getIconSizeUseCase.invoke().onEach { size ->
            _state.value = state.value.copy(
                iconSize = size
            )
        }.launchIn(viewModelScope)
    }

    fun setIconSize(size: Int) {
        viewModelScope.launch {
            setIconSizeUseCase.invoke(size = size)
        }
    }

}