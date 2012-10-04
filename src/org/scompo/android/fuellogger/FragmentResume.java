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

import java.util.ArrayList;
import java.util.List;

import org.scompo.android.fuellogger.DB.Fillup;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

/**
 * This Class implements a ListFragment that shows the saved fillings.
 *  
 * @author scompo
 * @version 1.0
 */
public class FragmentResume extends ListFragment {
	
	/**
	 * Interface to implement to communicate between this fragment and the main activity.
	 * 
	 * @author scompo
	 * @version 1.0
	 */
	public interface OnFillupSelectedListener{
		
		/**
		 * Method called when a fillup in the list it's clicked.
		 *  
		 * @param position the position of the clicked item.
		 */
		public void onFillupSelected(int position);
	}
	
	List<Fillup> lista;
	
	OnFillupSelectedListener mCallBack;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallBack = (OnFillupSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFillupSelectedListener");
        }
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		FillupArrayAdapter adapter = new FillupArrayAdapter(getActivity(), android.R.id.list,lista);
        setListAdapter(adapter);
	}
	
	/**
	 * On create method.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * When I click on a fillup to see it!
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCallBack.onFillupSelected(position);
	}
	
	public void setList(ArrayList<Fillup> listFillup) {
		lista = listFillup;
	}
	
}
