package com.dewerro.watermyplants.presentation

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewerro.watermyplants.domain.use_case.PlantUseCases
import com.dewerro.watermyplants.presentation.utils.AlarmReceiver
import com.dewerro.watermyplants.presentation.utils.NotificationBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCases: PlantUseCases) : ViewModel() {
    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private var getPlantsJob: Job? = null

    init {
        getAllPlants()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.InsertPlant -> {
                viewModelScope.launch {
                    useCases.insertPlantUseCase(event.plant)
                }
            }
            is MainEvent.GetPlant -> {
                viewModelScope.launch {
                    useCases.getPlantUseCase(event.id)
                }
            }
            is MainEvent.DeletePlant -> {
                viewModelScope.launch {
                    useCases.deletePlantUseCase(event.plant)
                }
            }
        }
    }

    fun setAlarm(context: Context, time: String) {
        val notificationBuilder = NotificationBuilder()
        notificationBuilder.createNotificationChannel(context)

        val hours = time.substring(0, time.indexOf(':')).toLong()
        val minutes =
            time.substring(time.indexOf(':') + 1).toLong() + TimeUnit.HOURS.toMinutes(hours)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            RTC_WAKEUP,
            TimeUnit.MINUTES.toMillis(minutes),
            `24HOURS_IN_MILLIS`,
            getPendingIntent(context)
        )
    }

    private fun getAllPlants() {
        getPlantsJob?.cancel()
        getPlantsJob = useCases.getAllPlantsUseCase().onEach {
            _state.value = state.value.copy(plantList = it)
        }.launchIn(viewModelScope)
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }
    }

    companion object {
        const val `24HOURS_IN_MILLIS` = 86400000L
    }
}