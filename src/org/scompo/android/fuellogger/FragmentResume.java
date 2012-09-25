/*  Android fuel logger keeps track of the user fuel consumption.
 *  
 *  Copyright (C) 2012  Mauro Scomparin <http://scompoprojects.wordpress.com>
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.scompo.android.fuellogger;

import java.util.List;

import org.scompo.android.fuellogger.DB.DBHelper;
import org.scompo.android.fuellogger.DB.Fillup;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

/**
 * This Class implements a ListFragment that shows the saved fillings.
 *  
 * @author scompo
 * @version 1.0
 */
public class FragmentResume extends ListFragment {
	
	DBHelper mDB;
	
	/**
	 * On create method.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// We need to use a different list item layout 
		//for devices older than Honeycomb
        /*int layout = Build.VERSION.SDK_INT >= 
        		Build.VERSION_CODES.HONEYCOMB ?
				android.R.layout.simple_list_item_activated_1 : 
				android.R.layout.simple_list_item_1;*/
        mDB = DBHelper.getInstance(getActivity());
        List <Fillup> data = mDB.getAllFillups();
        FillupArrayAdapter adapter = new FillupArrayAdapter(this.getActivity(), R.id.list,data);
        setListAdapter(adapter);
	}
	
}
