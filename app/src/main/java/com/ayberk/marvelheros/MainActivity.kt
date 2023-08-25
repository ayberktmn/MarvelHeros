package com.ayberk.marvelheros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://gist.githubusercontent.com/skydoves/ae1f687d7e67865a3d217ff719e256f8/raw/592160d562604476acd2d4adfd9d383058c7c558/MarvelLists.json

    }
}