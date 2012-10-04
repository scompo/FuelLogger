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

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This Class implements a Fragment that shows the menu.
 *  
 * @author scompo
 * @version 1.0
 */
public class FragmentMenu extends Fragment {
	
	OnMenuButtonClickedListener mCallBack;
	
	/**
	 * Interface to implement to listen to click events on the menu.
	 * 
	 * @author scompo
	 * @version 1.0
	 */
	public interface OnMenuButtonClickedListener{
		
		/**
		 * Method to implement to listen to clicks on the menu.
		 * 
		 * @param resourceID The id of the button clicked.
		 */
		public void onMenuButtonClicked(int resourceID);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Button a = (Button) getActivity().findViewById(R.id.cmdAddNew);
		a.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				mCallBack.onMenuButtonClicked(v.getId());				
			}
		});
		Button b = (Button) getActivity().findViewById(R.id.cmdViewData);
		b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				mCallBack.onMenuButtonClicked(v.getId());
				
			}
		});
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_menu, container, false);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallBack = (OnMenuButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMenuButtonClickedListener");
        }
	}
	
	//TODO: CHANGE THIS SHIT. USE THE ADD ACTIONLISTENER ON THE SINGLE BUTTONS.
	/**
	 * Called when cmdAddNew is clicked.
	 * 
	 * @param view The view.
	 */
	public void onClickAddNew(View view){
		mCallBack.onMenuButtonClicked(view.getId());
	}
	
	
	

	/**
	 * Called when cmdViewData is clicked.
	 * 
	 * @param view The view.
	 */
	public void onClickViewData(View view){
		mCallBack.onMenuButtonClicked(view.getId());
	}
	
	/**
	 * Called when cmdExit is clicked.
	 * 
	 * @param view The view.
	 */
	public void onClickExit(View view){
		mCallBack.onMenuButtonClicked(view.getId());
	}
	
}
