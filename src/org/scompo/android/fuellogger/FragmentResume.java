package org.scompo.android.fuellogger;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

/**
 * This Class implements a ListFragment that shows the saved fillings.
 *  
 * @author scompo
 * @version 1.0
 */
public class FragmentResume extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// We need to use a different list item layout 
		//for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= 
        		Build.VERSION_CODES.HONEYCOMB ?
				android.R.layout.simple_list_item_activated_1 : 
				android.R.layout.simple_list_item_1;
        // Create an array adapter for the list view, 
        // using the DataStuff headlines array
        //setListAdapter(new ArrayAdapter<String>(getActivity(), layout, DataStuff.indexes));
        
	}
	
}
