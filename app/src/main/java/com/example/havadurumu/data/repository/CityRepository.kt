package com.example.havadurumu.data.repository

import androidx.viewpager2.widget.ViewPager2.OffscreenPageLimit
import com.example.havadurumu.data.service.ApiServices

class CityRepository(val api: ApiServices)
{
    fun getCities(q:String,limit:Int)=
        api.getCitiesList(q,limit,"8fa77dfd74dbb4133764600c182738bc")
}