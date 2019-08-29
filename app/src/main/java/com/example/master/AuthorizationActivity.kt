package com.example.master

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_authorization.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.support.design.widget.Snackbar
import android.view.View
import com.example.master.request.NetworkService
import com.example.master.request.PostAuth
import com.example.master.structure.RegistrationBody
import com.example.master.structure.SharedPreferencesParams


class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        input_button.setOnClickListener {
            if (login_extended_edit_text.text.isEmpty())
                login_text_field_boxes.setError("Поле не может быть пустым", false)
            else {
                if (password_extended_edit_text.text.isEmpty())
                    password_text_field_boxes.setError("Поле не может быть пустым", false)
                else {
                    progressBar.setVisibility(ProgressBar.VISIBLE)
                    input_button.setText("")

                    val body = RegistrationBody()
                    body.login = login_extended_edit_text.text.toString()
                    body.password = password_extended_edit_text.text.toString()

                    StaticVariable.sharedPref = getSharedPreferences(SharedPreferencesParams.PREF_NAME, MODE_PRIVATE)

                    sendAuthorizationRequest(body, it)
                }
            }
        }
    }

    private fun sendAuthorizationRequest(body: RegistrationBody, view : View){
        NetworkService.getInstance()
            .jsonApiAuth
            .postData(body)
            .enqueue(object : Callback<PostAuth> {
                override fun onResponse(call: Call<PostAuth>, response: Response<PostAuth>) {
                    val post = response.body()

                    if (post != null)
                        saveUser(post)

                    Handler().postDelayed({
                        val intent = Intent(this@AuthorizationActivity,
                            MainScreenActivity::class.java)
                        startActivity(intent)
                    }, StaticVariable.delayTime)
                }

                override fun onFailure(call: Call<PostAuth>, t: Throwable) {
                    val snackbar = Snackbar.make(view,R.string.errorIncorrectData,Snackbar.LENGTH_LONG)
                    val snackbarView = snackbar.view
                    snackbarView.setBackgroundColor(Color.parseColor("#FF575D"))
                    snackbar.setActionTextColor(Color.WHITE)

                    snackbar.show()
                }
            })
    }

    private fun saveUser(post: PostAuth) {
        val prefEditor = StaticVariable.sharedPref.edit()
        prefEditor.putString(SharedPreferencesParams.token, post.accessToken)
        prefEditor.putInt(SharedPreferencesParams.id, post.userInfo.id)
        prefEditor.putString(SharedPreferencesParams.username, post.userInfo.username)
        prefEditor.putString(SharedPreferencesParams.firstName, post.userInfo.firstName)
        prefEditor.putString(SharedPreferencesParams.lastName, post.userInfo.lastName)
        prefEditor.putString(SharedPreferencesParams.userDescription, post.userInfo.userDescription)
        prefEditor.apply()
    }

    override fun onStop() {
        super.onStop()
        progressBar.setVisibility(ProgressBar.INVISIBLE)
        input_button.setText(R.string.stringEnter)
    }
}
