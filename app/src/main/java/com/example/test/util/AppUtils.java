package com.example.test.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import com.example.test.view.BaseApplication;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AppUtils {


    private static AppUtils appUtils;
    public static AppUtils getInstance(){
        if (appUtils == null)
            appUtils = new AppUtils();
        return appUtils;
    }


    public static final String BASE_URL = "https://api.mercadolibre.com/";

    /**
     * Verifica si el dispositivo está conectado a internet (WIFI/GPRS).
     * Para las versiones de API level >= 23, tambien se verifica que
     * si la red a la que esta conectado (si lo estuviese), tiene internet
     * @return Tiene internet o no
     */
    public boolean isInternetAvailable() {

        boolean isOnline = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                isOnline = capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);

            } else {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                isOnline = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return isOnline;
        }

    }

    Map<String, String> currencyMap = null;
    Map<String, String> conditionMap = null;

    public String getCurrencySymbol(String currency){
        if(currencyMap == null){
            currencyMap =  new HashMap<String, String>();
            currencyMap.put("ARS","$");
            currencyMap.put("USD","U$S");
        }
        if(currencyMap.containsKey(currency))
            return currencyMap.get(currency);
        else
            return currency;
    }

    public String getConditionText(String condition) {
        if(conditionMap == null){
            conditionMap =  new HashMap<String, String>();
            conditionMap.put("used","Usado");
            conditionMap.put("new","Nuevo");
        }
        if(conditionMap.containsKey(condition))
            return conditionMap.get(condition);
        else
            return condition;
    }

    public String formatPrice(int number){
        return String.format(Locale.getDefault(),"%,d", number);
    }



}
