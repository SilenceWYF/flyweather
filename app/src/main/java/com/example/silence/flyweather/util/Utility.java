package com.example.silence.flyweather.util;

import android.text.TextUtils;

import com.example.silence.flyweather.db.FlyWeatherDB;
import com.example.silence.flyweather.model.City;
import com.example.silence.flyweather.model.Province;

/**
 * Created by Silence on 2016/3/29.
 */
public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvinceResponse(FlyWeatherDB flyWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0){
                for (String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    flyWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(FlyWeatherDB flyWeatherDB, String response, int provinceID){
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0){
                for (String c : allCities){
                    String [] array =c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceID);
                    //将解析出来的数据储存到City表
                    flyWeatherDB.savedCity(city);
                }
                return true;
            }
        }
        return false;
    }


}
