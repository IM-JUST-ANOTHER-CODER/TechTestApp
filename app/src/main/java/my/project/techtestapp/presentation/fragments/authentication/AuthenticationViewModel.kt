package my.project.techtestapp.presentation.fragments.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import my.project.techtestapp.R
import my.project.techtestapp.app.Application
import my.project.techtestapp.data.repository.AuthenticationRepository
import my.project.techtestapp.utils.LoginState
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val context: Application,
) : ViewModel() {

    private var _phoneMask = MutableLiveData<String>()
    val phoneMask: LiveData<String?> = _phoneMask

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(phone: String, password: String) = viewModelScope.launch {
        val response = authenticationRepository.loadLoginStateFromApi(phone, password)
        try {
            if (response == true) {
                _loginState.value = LoginState.Success
            }
        } catch (e: Exception) {
            _loginState.value = LoginState.Error(context.getString(R.string.error_answer))
            Log.e("Login Exception", "$e")
        }
    }
    
    fun loadMask() {
        viewModelScope.launch {
            try {
                val result = authenticationRepository.loadMask()
                _phoneMask.postValue(result)
            } catch (e: Exception) {
                _phoneMask.postValue("")
                Log.e("Login Exception", "$e")
            }
        }
    }
}