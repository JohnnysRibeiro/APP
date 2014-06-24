/* File: StableArrayAdapter.java
 * 
 * Package: com.mds.app.util
 * 
 * Description: This is an util class responsible for stabling/adapting an Array for a view at the application.
 *
 */

package com.mds.app.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.widget.ArrayAdapter;

public class StableArrayAdapter extends ArrayAdapter<String> {

	HashMap<String, Integer> mIdMap;

	public StableArrayAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
		super(context, textViewResourceId, objects);
		mIdMap = new HashMap<String, Integer>();
		for (int i = 0; i < objects.size(); ++i) {
			mIdMap.put(objects.get(i), i);
		}
	}

	@Override
	public long getItemId(int position) {
		String item = getItem(position);
		return mIdMap.get(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

}