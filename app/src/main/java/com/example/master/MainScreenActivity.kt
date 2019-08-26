package com.example.master

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main_screen.*
import android.support.design.widget.BottomNavigationView
import com.example.master.Fragments.AddPostFragment
import com.example.master.Fragments.TapeFragment
import com.example.master.Fragments.UserFragment
import android.support.v4.app.Fragment

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        loadFragment(TapeFragment.newInstance())

        setSupportActionBar(toolbar)
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
                    loadFragment(TapeFragment.newInstance())
                    getSupportActionBar()!!.setTitle("Популярные мемы")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_add_post -> {
                    loadFragment(AddPostFragment.newInstance())
                    getSupportActionBar()!!.setTitle("Add post")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_user -> {
                    loadFragment(UserFragment.newInstance())
                    getSupportActionBar()!!.setTitle("User")
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
