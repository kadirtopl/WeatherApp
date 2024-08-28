package com.example.havadurumu.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.fragment.app.Fragment
import com.example.havadurumu.R
import com.example.havadurumu.databinding.FragmentWeatherScreenBinding
import com.example.havadurumu.data.model.CurrentResponseApi
import com.example.havadurumu.presentation.viewModel.WeatherViewModel
import com.github.matteobattilana.weather.PrecipType
import eightbitlab.com.blurview.RenderScriptBlur
import retrofit2.Call
import retrofit2.Response
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.project1712.Adapter.ForecastAdapter
import com.example.project1712.model.ForecastResponseApi
import retrofit2.Callback
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.Calendar

class WeatherScreenFragment : Fragment() {

    private lateinit var binding: FragmentWeatherScreenBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val calender by lazy { Calendar.getInstance() }
    private val forecastAdapter by lazy { ForecastAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lat = 51.50
        val lon = 0.12
        val name = "London"

        binding.cityTxt.text = name
        binding.progressBar.visibility = View.VISIBLE

        // Setup RecyclerView
        binding.forecastView.layoutManager = LinearLayoutManager(requireContext())
        binding.forecastView.adapter = forecastAdapter

        weatherViewModel.loadCurrentWeather(lat, lon, "metric").enqueue(object :
            retrofit2.Callback<CurrentResponseApi> {
            override fun onResponse(
                call: Call<CurrentResponseApi>,
                response: Response<CurrentResponseApi>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    binding.progressBar.visibility = View.GONE
                    binding.detailLayout.visibility = View.VISIBLE
                    data?.let {
                        binding.statusTxt.text = it.weather?.get(0)?.main ?: "-"
                        binding.humidityTxt.text = "${it.main?.humidity}%"
                        binding.windTxt.text = "${it.wind?.speed?.let { Math.round(it) }}Km"
                        binding.currentTempTxt.text = "${it.main?.temp?.let { Math.round(it) }}°"
                        binding.maxTempTxt.text = "${it.main?.tempMax?.let { Math.round(it) }}°"
                        binding.minTempTxt.text = "${it.main?.tempMin?.let { Math.round(it) }}°"

                        val drawable = if (isNightNow()) R.drawable.night_bg
                        else {
                            setDynamicallWallpaper(it.weather?.get(0)?.icon ?: "°")
                        }
                        binding.bgImage.setImageResource(drawable)
                        setEffectRainSnow(it.weather?.get(0)?.icon ?: "°")
                    }
                }
            }

            override fun onFailure(call: Call<CurrentResponseApi>, t: Throwable) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        val rootView = binding.root
        val windowBackGround = requireActivity().window.decorView.background
        val blurView = binding.blurView

        blurView.setupWith(rootView, RenderScriptBlur(requireContext()))
            .setFrameClearDrawable(windowBackGround)
            .setBlurRadius(10f)
        blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        blurView.clipToOutline = true

        weatherViewModel.loadForecastWetaher(lat, lon, "metric").enqueue(object :
            retrofit2.Callback<ForecastResponseApi> {
            override fun onResponse(
                call: Call<ForecastResponseApi>,
                response: Response<ForecastResponseApi>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    blurView.visibility = View.VISIBLE
                    data?.let {
                        forecastAdapter.differ.submitList(it.list)
                        binding.forecastView.apply{
                            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                         adapter=forecastAdapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ForecastResponseApi>, t: Throwable) {
                // Handle the error
                Toast.makeText(requireContext(), "Error fetching forecast data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun isNightNow(): Boolean {
        return calender.get(Calendar.HOUR_OF_DAY) >= 2
    }

    private fun setDynamicallWallpaper(icon: String): Int {
        return when (icon.dropLast(1)) {
            "01" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.snow_bg
            }
            "04", "02", "03" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.cloudy_bg
            }
            "09", "10", "11" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.rainy_bg
            }
            "13" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.snow_bg
            }
            "50" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.haze_bg
            }
            else -> 0
        }
    }

    private fun setEffectRainSnow(icon: String) {
        when (icon.dropLast(1)) {
            "01", "02", "03", "04", "09", "10", "11", "13", "50" -> {
                initWeatherView(PrecipType.CLEAR)
            }
        }
    }

    private fun initWeatherView(type: PrecipType) {
        binding.weatherView.apply {
            setWeatherData(type)
            angle = 20
            emissionRate = 100.0f
        }
    }
}
