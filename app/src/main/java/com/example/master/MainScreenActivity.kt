package com.example.master

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main_screen.*
import android.support.design.widget.BottomNavigationView
import com.example.master.Fragments.AddPostFragment
import com.example.master.Fragments.UserFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.example.master.Fragments.ErrorFragment
import com.example.master.Fragments.TapeFragment
import com.example.master.MemesList.listMemes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setSupportActionBar(toolbar)

        progressBarMainScreen.setVisibility(ProgressBar.VISIBLE)

        sendMemesRequest()

        Handler().postDelayed({
            if (listMemes.isNotEmpty()) {
                loadFragment(TapeFragment.newInstance())
                progressBarMainScreen.setVisibility(ProgressBar.INVISIBLE)
            }
        }, 500)
    }

    private fun sendMemesRequest(){

        NetworkService.getInstance()
            .jsonApi
            .postDataMemes
            .enqueue(object : Callback<ArrayList<MemeInfo>> {
                override fun onResponse(call: Call<ArrayList<MemeInfo>>, response: Response<ArrayList<MemeInfo>>) {
                    if (response.isSuccessful()) {
                        val post = response.body()
                        if(post != null)
                            listMemes = post
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_screen_menu, menu)
        return true
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_tape -> {
                    getSupportActionBar()!!.setTitle("Популярные мемы")
                    sendMemesRequest()

                    if (listMemes.isNotEmpty()) {
                        loadFragment(TapeFragment.newInstance())
                    }
                    else {
                        loadFragment(ErrorFragment.newInstance())
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_add_post -> {
                    getSupportActionBar()!!.setTitle("Add post")
                    loadFragment(AddPostFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_user -> {
                    getSupportActionBar()!!.setTitle("User")
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
