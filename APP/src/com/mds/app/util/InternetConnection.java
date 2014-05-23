/* File: InternetConnection.java
 * 
 * Package: com.mds.app.util
 * 
 * Description: This is an util class responsible for checking and creating an internet connection throught 2G/3G/4G or wifi.
 *
 */

package com.mds.app.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternetConnection {

	private Context context;

	public InternetConnection() {
	}

	public InternetConnection(Context context) {
		this.context = context;
	}

	public boolean checkInternetConnection() {
		boolean hasConnection = false;

		ConnectivityManager connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		android.net.NetworkInfo wifi = connection.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		android.net.NetworkInfo mobile = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (wifi.isConnected() || mobile.isConnected()) {
			System.out.println("TEM CONEXAO COM A INTERNET");
			hasConnection = true;
		}
		else {
			System.out.println("DESCONECTADO DA INTERNET");
			hasConnection = false;
		}

		return hasConnection;
	}

	public Context getContext() {
		return context;
	}

}
