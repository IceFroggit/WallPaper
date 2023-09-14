package icefroggit.app.presentation.viewModels

import androidx.lifecycle.ViewModel
import icefroggit.app.data.MainRepository

class RandomViewModel:ViewModel() {
    val repository = MainRepository()
}