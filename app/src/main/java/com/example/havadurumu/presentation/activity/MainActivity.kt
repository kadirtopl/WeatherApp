package com.example.havadurumu.presentation.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.havadurumu.R
import com.example.havadurumu.presentation.viewModel.WeatherViewModel
import com.example.havadurumu.databinding.ActivityMainBinding
import com.example.havadurumu.data.model.CurrentResponseApi
import com.example.havadurumu.presentation.view.LoginScreenFragment
import com.github.matteobattilana.weather.PrecipType
import eightbitlab.com.blurview.RenderScriptBlur
import retrofit2.Call
import retrofit2.Response
import java.util.Calendar

class MainActivity : AppCompatActivity() {
   lateinit var binding:ActivityMainBinding

   private val weatherViewModel: WeatherViewModel by viewModels ()
    private  val calender by lazy { Calendar.getInstance() }

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)

       if(savedInstanceState==null){
           supportFragmentManager.beginTransaction()
               .replace(R.id.fragmentContainerView,LoginScreenFragment())
               .commit()
       }



}}



