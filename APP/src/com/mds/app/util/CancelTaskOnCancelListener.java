/* File: CancelTaskOnCancelListener.java
 * 
 * Package: com.mds.app.util
 * 
 * Description: This is a util class to cancel the task
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
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