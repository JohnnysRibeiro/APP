/* File: CancelTaskOnCancelListener.java
 * 
 * Package: com.mds.app.util
 * 
 * Description: This is an util class responsible for canceling/killing a task requested by the user or the application. Usually an asyncrounous task.
 * 
 */

package com.mds.app.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;

public class CancelTaskOnCancelListener implements OnCancelListener {

	private AsyncTask<?, ?, ?> task;

	public CancelTaskOnCancelListener() {

	}

	public CancelTaskOnCancelListener(AsyncTask<?, ?, ?> task) {
		this.task = task;
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		if (task != null) {
			task.cancel(true);
		}
	}

	public AsyncTask<?, ?, ?> getTask() {
		return this.task;
	}

}