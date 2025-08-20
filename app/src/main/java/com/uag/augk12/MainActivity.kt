package com.uag.augk12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.uag.augk12.data.repository.BenefitRepository
import com.uag.augk12.navigation.NavGraph
import com.uag.augk12.ui.theme.AUGK12Theme
import com.uag.augk12.viewmodel.AuthViewModel
import com.uag.augk12.viewmodel.AuthViewModelFactory
import com.uag.augk12.viewmodel.BenefitViewModel
import com.uag.augk12.viewmodel.BenefitViewModelFactory

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(application)
    }

    private val benefitViewModel: BenefitViewModel by viewModels {
        BenefitViewModelFactory(BenefitRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AUGK12Theme {
                Surface(color = MaterialTheme.colorScheme.background){
                    NavGraph(authViewModel, benefitViewModel)
                }
            }
        }
    }
}