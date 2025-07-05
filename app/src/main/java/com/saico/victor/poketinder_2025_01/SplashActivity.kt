package com.saico.victor.poketinder_2025_01

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.saico.victor.poketinder_2025_01.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Espera 2 segundos antes de redirigir al LoginActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Si ya tienes datos en SharedPreferences (usuario registrado), redirige al Login
            val sharedPreferencesRepository = SharedPreferencesRepository()
            sharedPreferencesRepository.setSharedPreference(this)
            val userEmail = sharedPreferencesRepository.getUserEmail()

            if (userEmail.isEmpty()) {
                // Si el usuario no está registrado, redirige a RegisterActivity
                startActivity(Intent(this, RegisterActivity::class.java))
            } else {
                // Si ya está registrado, redirige a LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2000) // Splash visible por 2 segundos
    }
}
