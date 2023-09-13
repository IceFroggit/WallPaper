package icefroggit.app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import icefroggit.app.R
import icefroggit.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //todo чекнуть так ли безопасно биндинг делается
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}