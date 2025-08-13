package com.uag.augk12.data.store

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.uag.augk12.data.models.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

val Context.dataStore by preferencesDataStore(name = "user_prefs")
object UserPreferences {
    private val IS_AUTHENTICATED = booleanPreferencesKey("is_authenticated")
    private val SELECTED_CHILD_ID = intPreferencesKey("selected_child_id")
    private val USER_AUTH = stringPreferencesKey("user_data")

    fun isAuthenticatedFlow(context: Context): Flow<Boolean> =
        context.dataStore.data.map { prefs -> prefs[IS_AUTHENTICATED] ?: false }

    fun selectedChildIdFlow(context: Context): Flow<Int?> =
        context.dataStore.data.map { prefs -> prefs[SELECTED_CHILD_ID] }

    fun userAuthFlow(context: Context): Flow<UserModel?> =
        context.dataStore.data.map { prefs ->
            prefs[USER_AUTH]?.let { Json.decodeFromString<UserModel>(it) }
        }

    suspend fun saveIsAuthenticated(context: Context, isAuthenticated: Boolean) {
        context.dataStore.edit { prefs -> prefs[IS_AUTHENTICATED] = isAuthenticated }
    }

    suspend fun saveUserAuthenticated(context: Context, user:UserModel?) {
        context.dataStore.edit { prefs ->
            if(user != null) {
                val jsonString = Json.encodeToString(user)
                prefs[USER_AUTH] = jsonString
            } else {
                prefs.remove(USER_AUTH)
            }
        }
    }

    suspend fun saveSelectedChildId(context: Context, childId: Int?) {
        context.dataStore.edit { prefs ->
            if (childId != null) {
                prefs[SELECTED_CHILD_ID] = childId
            } else {
                prefs.remove(SELECTED_CHILD_ID)
            }
        }
    }
}