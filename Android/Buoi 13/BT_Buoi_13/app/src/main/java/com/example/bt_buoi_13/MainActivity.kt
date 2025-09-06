package com.example.bt_buoi_13

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bt_buoi_13.databinding.ActivityMainBinding
import com.example.bt_buoi_13.fragment.FragmentAdd
import com.example.bt_buoi_13.fragment.FragmentAll
import com.example.bt_buoi_13.fragment.FragmentCompleted

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            replaceFragment(FragmentAll())
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_all -> {
                    replaceFragment(FragmentAll(), true)
                    true
                }
                R.id.nav_completed -> {
                    replaceFragment(FragmentCompleted(), true)
                    true
                }
                else -> false
            }
        }

        binding.btnAdd.setOnClickListener { view ->
            replaceFragmentAll(FragmentAdd(), true)
            hideBottomNav()
        }
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }


    fun replaceFragmentAll(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }


    fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }
}