package com.example.master

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main_screen.*
import android.support.design.widget.BottomNavigationView
import com.example.master.fragment.AddMemeFragment
import com.example.master.fragment.UserFragment
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import com.example.master.fragment.ErrorFragment
import com.example.master.fragment.TapeFragment
import com.example.master.request.NetworkService
import com.example.master.StaticVariable.listMemes
import com.example.master.structure.MemeInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        sendMemesRequest()
    }

    fun onFavoriteButtonClick(view: View) {
        when (view.id) {
            R.id.imageButtonFavorite -> {
                val buttonFavorite = findViewById<ImageButton>(R.id.imageButtonFavorite)
                buttonFavorite.visibility = ProgressBar.INVISIBLE
            }
        }
    }

    private fun sendMemesRequest(){
        progressBarMainScreen.setVisibility(ProgressBar.VISIBLE)

        NetworkService.getInstance()
            .jsonApi
            .postDataMemes
            .enqueue(object : Callback<ArrayList<MemeInfo>> {
                override fun onResponse(call: Call<ArrayList<MemeInfo>>, response: Response<ArrayList<MemeInfo>>) {
                    if (response.isSuccessful()) {
                        val post = response.body()
                        if(post != null) {
                            listMemes = post
                            Handler().postDelayed({
                                if (listMemes.isNotEmpty()) {
                                    loadFragment(TapeFragment.newInstance())
                                    progressBarMainScreen.setVisibility(ProgressBar.INVISIBLE)
                                }
                            }, StaticVariable.delayTime)
                        }
                    }
                    else{
                        loadFragment(ErrorFragment.newInstance())
                    }
                }

                override fun onFailure(call: Call<ArrayList<MemeInfo>>, t: Throwable) {
                    loadFragment(ErrorFragment.newInstance())
                }
            })
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_tape -> {
                    sendMemesRequest()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_add_post -> {
                    loadFragment(AddMemeFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_user -> {
                    loadFragment(UserFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun loadFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_content, fragment)
        ft.commit()
    }
}
