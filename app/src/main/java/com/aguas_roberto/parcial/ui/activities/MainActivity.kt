package com.aguas_roberto.parcial.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aguas_roberto.parcial.data.local.repository.ListUsers
import com.aguas_roberto.parcial.databinding.ActivityMainBinding
import com.aguas_roberto.parcial.logic.userCases.LoginUserCase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {

            val loginUserCase = LoginUserCase(ListUsers())

            val result = loginUserCase(
                binding.etxtUser.text.toString(),
                binding.etxtPassword.text.toString()
            )

            result.onSuccess { user ->
                val intentToConstraintAct = Intent(
                    this,
                    ConstrainActivity::class.java
                ).apply {
                    putExtra("idUser", user.id)
                }
                startActivity(intentToConstraintAct)
            }

            result.onFailure {
                Toast.makeText(
                    this,
                    it.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}