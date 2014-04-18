package com.mds.app.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

public class Persistence extends Activity {

	/*
	 * Pros parametros "fileName" dos metodos, passar ou:
	 * Persistence.getFileFavoritos() para gravar no arquivo dos favoritos, ou
	 * Persistence.getFileHistorico para gravar no arquivo do historico
	 */

	private static final String favoritesNameFile = "favoritos";
	private static final String historicNameFile = "historico";

	private Context context;
	
	private final Charset charset = Charset.forName("UTF-8");

	private File favoritesFile;
	private File historicFile;

	public Persistence(Context context) {
		this.context = context;
		favoritesFile = new File(this.context.getFilesDir(), favoritesNameFile);
		historicFile = new File(this.context.getFilesDir(), historicNameFile);
	}

	public void writeInFile(String fileName, String data) {
		final String TAG = "WRITE";
		FileOutputStream fileOutputStream = null;

		try {
			verifyFileName(fileName);
		} catch (IllegalArgumentException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
		}

		int mode = 0;
		if (!(new File(fileName)).exists()) {
			mode = Context.MODE_APPEND;
		}
		else {
			mode = Context.MODE_PRIVATE;
		}

		try {
			fileOutputStream = (new ContextWrapper(context)).openFileOutput(fileName, mode);
		} catch (FileNotFoundException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
		}
		try {
			fileOutputStream.write(data.getBytes(charset));
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
		}
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
		}

	}

	public void rewriteFile(String fileName, String newContent) {
		deleteAFile(fileName);
		writeInFile(fileName, newContent);
	}

	public String readFromFile(String fileName) {
		final String TAG = "READ";
		FileInputStream fileInputStream;

		try {
			verifyFileName(fileName);
		} catch (IllegalArgumentException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
			return "ARQUIVO NAO IDENTIFICADO";
		}

		String fileContent = "";

		try {
			fileInputStream = (new ContextWrapper(context)).openFileInput(fileName);
		} catch (FileNotFoundException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
			return "ARQUIVO NAO EXISTE";
		}

		int data = 0;
		try {
			while ((data = fileInputStream.read()) != -1) {
				char ch = (char) data;
				String str = String.valueOf(ch);
				fileContent += str;
			}
			Log.i(TAG, fileContent);
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
			return "ARQUIVO VAZIO";
		}

		try {
			fileInputStream.close();
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
		}

		return fileContent;
	}

	private boolean deleteAFile(String fileName) {
		final String pathString;
		if (fileName.equals(Persistence.getFavoritesNameFile())) {
			pathString = favoritesFile.getAbsolutePath();
		}
		else if (fileName.equals(Persistence.getHistoricNameFile())) {
			pathString = historicFile.getAbsolutePath();
		}
		else {
			throw new IllegalArgumentException("Deve ser passado o arquivo dos favoritos ou do historico!");
		}

		File file = new File(pathString);
		boolean deleted = file.delete();
		String deletedString = (deleted) ? "deleted" : "NOTdeleted";
		Log.i("DELETE", pathString + ": " + deletedString);
		return deleted;
	}

	private void verifyFileName(String fileName) {
		if (!fileName.equals(Persistence.getFavoritesNameFile())
				&& !fileName.equals(Persistence.getHistoricNameFile())) {
			throw new IllegalArgumentException("Deve ser passado o arquivo dos favoritos ou do historico!");
		}
	}

	// ////////////////////////////////

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public File getFavoritesFile() {
		return favoritesFile;
	}

	public void setFavoritesFile(File favoritesFile) {
		this.favoritesFile = favoritesFile;
	}

	public File getHistoricFile() {
		return historicFile;
	}

	public void setHistoricFile(File historicFile) {
		this.historicFile = historicFile;
	}

	public static String getFavoritesNameFile() {
		return favoritesNameFile;
	}

	public static String getHistoricNameFile() {
		return historicNameFile;
	}

}
