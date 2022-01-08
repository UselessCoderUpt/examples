package com.examples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.examples.presentation.components.CustomerListScreen
import com.examples.presentation.components.HomeScreen
import com.examples.presentation.components.PawnOsListScreen
import com.examples.presentation.ui.BottomNavBar
import com.examples.presentation.ui.BottomNavDestination
import com.examples.presentation.ui.NavGraph
import com.examples.ui.theme.ExamplesTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setupWorkManagerJob()

        setContent {
            ExamplesTheme {
                NavGraph()
            }
        }
    }

    /**
     * Setup WorkManager background job to 'fetch' new network data daily.
     * REF: https://developer.android.com/codelabs/kotlin-coroutines#11
     * refer section 12 in above url
     */

    /**
     * WorkManager is part of Android Jetpack, and an Architecture Component for background work
    that needs a combination of opportunistic and guaranteed execution.
    Opportunistic execution means that WorkManager will do your background work as soon as it can.
    Guaranteed execution means that WorkManager will take care of the logic to start your work
    under a variety of situations, even if you navigate away from your app.
     */

/*
    private fun setupWorkManagerJob() {
        // initialize WorkManager with a Factory
        val workManagerConfiguration = Configuration.Builder()
            .setWorkerFactory(RefreshMainDataWork.Factory())
            .build()
        WorkManager.initialize(this, workManagerConfiguration)

        // Use constraints to require the work only run when the device is charging and the
        // network is unmetered
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(UNMETERED)
            .build()

        // Specify that the work should attempt to run every day
        val work = PeriodicWorkRequestBuilder<RefreshMainDataWork>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        // Enqueue it work WorkManager, keeping any previously scheduled jobs for the same
        // work.
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(RefreshMainDataWork::class.java.name, KEEP, work)
    }
*/

}
