package com.example.test.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.test.R;
import com.google.android.material.snackbar.Snackbar;

public class Utils {

   /* public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }*/

    public static boolean isInternetAvailable(Context context) {

        boolean isOnline = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

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

    public static void showInternetError(Activity context) {
        Snackbar.make(context.getCurrentFocus(), R.string.no_internet, Snackbar.LENGTH_LONG).show();
    }

    public static void showServerError(Activity context) {
        Snackbar.make(context.getCurrentFocus(), R.string.server_error, Snackbar.LENGTH_LONG).show();
    }


}
