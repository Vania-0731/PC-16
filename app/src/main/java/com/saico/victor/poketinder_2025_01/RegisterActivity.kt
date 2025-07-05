package com.saico.victor.poketinder_2025_01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.saico.victor.poketinder_2025_01.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBackClose.setOnClickListener {
            onBackPressed()
        }

        // Register button listener
        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtPassword2.text.toString()

            // Validations
            if (!isValidEmail(email)) {
                Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
            } else if (password.length < 8) {
                Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPreferencesRepository = SharedPreferencesRepository()
                sharedPreferencesRepository.setSharedPreference(this)
                sharedPreferencesRepository.saveUserEmail(email)
                sharedPreferencesRepository.saveUserPassword(password)

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                //ir al login
                finish()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
