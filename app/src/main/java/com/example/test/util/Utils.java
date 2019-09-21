package com.example.test.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import com.example.test.view.BaseApplication;

public class Utils {

  /*  public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }*/

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



}
