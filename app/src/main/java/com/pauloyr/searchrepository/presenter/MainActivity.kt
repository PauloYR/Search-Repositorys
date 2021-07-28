package com.pauloyr.searchrepository.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pauloyr.searchrepository.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val searchRepositoryViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchRepositoryViewModel.setSearch("Android")
        searchRepositoryViewModel.setPage(1)
        searchRepositoryViewModel.getRepositories()
        searchRepositoryViewModel.repositories.observe(this,{ repositors->
            repositors?.let {
                Log.i("OPA","ALO")
            }
        })
    }
}