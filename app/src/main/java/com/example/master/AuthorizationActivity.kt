package com.example.master

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_authorization.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        input_button.setOnClickListener {
            if (password_extended_edit_text.text.isEmpty())
                password_text_field_boxes.setError("Поле не может быть пустым", false)
            if (login_extended_edit_text.text.isEmpty())
                login_text_field_boxes.setError("Поле не может быть пустым", false)

            progressBar.setVisibility(ProgressBar.VISIBLE)
            input_button.setText("")

            val body = RegistrationBody()
            body.login = login_extended_edit_text.text.toString()
            body.password = password_extended_edit_text.text.toString()

            NetworkService.getInstance()
                .jsonApi
                .postData(body)
                .enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        val post = response.body()
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                    }
                })
        }
    }
}
