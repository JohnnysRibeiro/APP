/* File: Persistence.java
 * 
 * Package: com.mds.app.persistence
 * 
 * Description: This is a persistence class responsible for managing the persistence between the application and the database.
 *
 */

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
	 * For "fileName" parameters from methods we need to pass
	 * Persistence.getFavoritesFile() to save the favorite into the file
	 * or Persistence.getHistoryFile() to save into the history file
	 */

	private static final String favoritesNameFile = "favoritos";
	private static final String historyNameFile = "historico";

	private Context context;
	
	private final Charset charset = Charset.forName("UTF-8");

	private File favoritesFile;
	private File historyFile;

	public Persistence(Context context) {
		this.context = context;
		favoritesFile = new File(this.context.getFilesDir(), favoritesNameFile);
		historyFile = new File(this.context.getFilesDir(), historyNameFile);
	}

	/*
	 * This method is responsible for writing into a file. It verifies if the file
	 * already exists, then it enters in APPEND mode otherwise the flux goes to the PRIVATE
	 * MODE, then it writes on a file.
	 */
	
	public void writeInFile(String fileName, String data) {
		final String TAG = "WRITE";
		FileOutputStream fileOutputStream = null;

		/*
		 * Calls a method that verifies if the filename passed as parameter
		 * exists on the array of favorites and history projects. If it fails
		 * then the code calls an exception.
		 */
		
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

		/*
		 * Assign the file to the fileOutputStream variable/class
		 */
		
		try {
			fileOutputStream = (new ContextWrapper(context)).openFileOutput(fileName, mode);
		} catch (FileNotFoundException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
		}
		
		/*
		 * Writes the data 
		 */
		
		try {
			fileOutputStream.write(data.getBytes(charset));
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
		}
		
		/*
		 * Closes the fileOutputStrem
		 */
		
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
			e.printStackTrace();
		}

	}

	/*
	 * Delete the old file and creates a new one, using the same fileName, with
	 * new content.
	 */
	
	public void rewriteFile(String fileName, String newContent) {
		removeAFile(fileName);
		writeInFile(fileName, newContent);
	}

	/*
	 * Verify if the fileName exists, concatenates its content to the
	 * fileContent variable and return this variable. If the file
	 * doesnt not exist so the method return a string saying it.
	 */
	
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

	/*
	 * Get the real path to the fileName and delete it. Returns true if the file
	 * was deleted and false it it wasnt. 
	 */
	
	private boolean removeAFile(String fileName) {
		final String pathString;
		if (fileName.equals(Persistence.getFavoritesNameFile())) {
			pathString = favoritesFile.getAbsolutePath();
		}
		else if (fileName.equals(Persistence.getHistoryNameFile())) {
			pathString = historyFile.getAbsolutePath();
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

	/*
	 * Verify if the filename corresponds to the same name inside the Favorites and/or History
	 * Array and if its not the method throws an Exception.
	 */
	
	private void verifyFileName(String fileName) {
		if (!fileName.equals(Persistence.getFavoritesNameFile())
				&& (!fileName.equals(Persistence.getHistoryNameFile()))) {
			throw new IllegalArgumentException("Deve ser passado o arquivo dos favoritos ou do historico!");
		}
	}

	/*
	 * Getters and setters. 
	 */
	
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

	public File getHistoryFile() {
		return historyFile;
	}

	public void setHistoryFile(File historyFile) {
		this.historyFile = historyFile;
	}

	public static String getFavoritesNameFile() {
		return favoritesNameFile;
	}

	public static String getHistoryNameFile() {
		return historyNameFile;
	}

}
