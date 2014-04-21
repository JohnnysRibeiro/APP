package com.mds.app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ReceiveHTTP {

	public ReceiveHTTP() {
	}

	public String receive(String url) {

		BufferedReader inputStream = null;
		String data = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = null;
		HttpResponse answer = null;
		URI website = null;
		StringBuffer dataStringBuffer = null;
		String append = null;

		try {
			website = new URI(url);
		} catch (URISyntaxException urise) {
			urise.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}

		request = new HttpGet();
		request.setURI(website);

		try {
			answer = client.execute(request);
			inputStream = new BufferedReader(new InputStreamReader(answer.getEntity().getContent()));

			dataStringBuffer = new StringBuffer("");
			append = "";
			while ((append = inputStream.readLine()) != null) {
				dataStringBuffer.append(append);
			}

			inputStream.close();
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		data = dataStringBuffer.toString();

		return data;
	}
}
