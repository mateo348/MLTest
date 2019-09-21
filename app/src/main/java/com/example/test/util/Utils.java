package com.example.test.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import com.example.test.view.BaseApplication;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Utils {



    public static final String BASE_URL = "https://api.mercadolibre.com/";

    /**
     * Verifica si el dispositivo está conectado a internet (WIFI/GPRS).
     * Para las versiones de API level >= 23, tambien se verifica que
     * si la red a la que esta conectado (si lo estuviese), tiene internet
     * @return Tiene internet o no
     */
    public static boolean isInternetAvailable() {

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

    static Map<String, String> currencyMap = null;
    static Map<String, String> conditionMap = null;

    public static String getCurrencySymbol(String currency){
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

    public static String getConditionText(String condition) {
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

    public static String formatPrice(int number){
        return String.format(Locale.ITALIAN,"%,d", number);
    }



}
