package com.uag.augk12.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.uag.augk12.data.models.ChildModel
import com.uag.augk12.data.models.UserModel
import com.uag.augk12.data.store.UserPreferences
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel(application: Application): AndroidViewModel(application) {
    private val context = application.applicationContext

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    private val _userAuth = MutableStateFlow<UserModel?>(null)
    val userAuth: StateFlow<UserModel?> = _userAuth.asStateFlow()

    private val _selectedChild = MutableStateFlow<ChildModel?>(null)
    val selectedChild: StateFlow<ChildModel?> = _selectedChild.asStateFlow()

    private val _children = MutableStateFlow(
        listOf(
            ChildModel(1, "Juan Pérez", ""),
            ChildModel(2, "María López", "")
        )
    )
    val children: StateFlow<List<ChildModel>> = _children.asStateFlow()

    init {
        viewModelScope.launch {
            UserPreferences.isAuthenticatedFlow(context).collect { auth ->
                _isAuthenticated.value = auth
            }
        }
        viewModelScope.launch {
            UserPreferences.selectedChildIdFlow(context).collect { childId ->
                val child = _children.value.find { it.id == childId }
                _selectedChild.value = child
            }
        }
        viewModelScope.launch {
            UserPreferences.userAuthFlow(context).collect { user ->
                _userAuth.value = user
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            kotlinx.coroutines.delay(1000)
            _isAuthenticated.value = true
            UserPreferences.saveIsAuthenticated(context, true)
            UserPreferences.saveUserAuthenticated(context, UserModel(username, password))
        }
    }

    fun selectChild(child: ChildModel) {
        _selectedChild.value = child
        viewModelScope.launch {
            UserPreferences.saveSelectedChildId(context, child.id)
        }
    }

    fun logout() {
        viewModelScope.launch {
            _isAuthenticated.value = false
            _selectedChild.value = null
            UserPreferences.saveIsAuthenticated(context, false)
            UserPreferences.saveSelectedChildId(context, null)
            UserPreferences.saveUserAuthenticated(context, null)
        }
    }
}