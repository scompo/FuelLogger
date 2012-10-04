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

import java.util.Hashtable;
import org.scompo.android.fuellogger.DB.Fillup;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * This Class implements a Fragment that shows the details of a Fillup.
 *  
 * @author scompo
 * @version 1.0
 */
public class FragmentDetails extends Fragment{
	
	private Fillup _fillup;
	private Hashtable<Integer, View> viewsContainer;
	
	OnButtonClickedListener mCallBack;
	
	/**
	 * To implement to listen to a button of FragmentDetails.
	 * 
	 * @author scompo
	 * @version 1.0
	 */
	public interface OnButtonClickedListener{
		
		/**
		 * Method to interface the activity with the click of the button.
		 */
		public void onButtonClicked(int resourceId, Fillup fillup);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallBack = (OnButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonClickedListener");
        }
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_fillup = Fillup.EMPTY_FILLUP;
		viewsContainer = new Hashtable<Integer, View>();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_details, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		fillViewsContainer();
		updateFillupData();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Button save=(Button) getActivity().findViewById(R.id.cmdSave);
		save.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				onClickSave(v);
			}
		});
	}
	
	
	/**
	 * fillup the viewsContainer with the actual views.
	 */
	private void fillViewsContainer() {
		viewsContainer.put(Integer.valueOf(R.id.txtDate), getActivity().findViewById(R.id.txtDate));
		viewsContainer.put(Integer.valueOf(R.id.txtDistance), getActivity().findViewById(R.id.txtDistance));
		viewsContainer.put(Integer.valueOf(R.id.txtQuantity), getActivity().findViewById(R.id.txtQuantity));
		viewsContainer.put(Integer.valueOf(R.id.txtPrice), getActivity().findViewById(R.id.txtPrice));
		viewsContainer.put(Integer.valueOf(R.id.chkPartial), getActivity().findViewById(R.id.chkPartial));
	}

	/**
	 * Sets the content of the fragment with the fillup.
	 * 
	 * @param fillup The Fillup containing the data to load.
	 */
	public void setContents(Fillup fillup){
		_fillup = fillup;
		//updateFillupData();
	}
	
	/**
	 * Method that sets the fields to the _fillup values.
	 */
	private void updateFillupData(){
		((EditText)viewsContainer.get(R.id.txtDate)).setText(_fillup.getDate());
		//TODO this is dirty I know but I can't be bothered right now to add the conversion right..
		((EditText)viewsContainer.get(R.id.txtDistance)).setText(""+_fillup.getDistance());
		((EditText)viewsContainer.get(R.id.txtQuantity)).setText(""+_fillup.getQt());
		((EditText)viewsContainer.get(R.id.txtPrice)).setText(""+_fillup.getPrice());
		((CheckBox)viewsContainer.get(R.id.chkPartial)).setChecked(_fillup.isPartial());
	}
	
	/**
	 * On button save clicked.
	 * 
	 * @param view
	 */
	public void onClickSave(View view){
		Fillup fillTemp = getFillupFromViewsContainer();
		mCallBack.onButtonClicked(view.getId(),fillTemp);
	}
	
	/**
	 * Method that returns a Fillup from the content of the view.
	 * 
	 * @return a new Fillup with the content of the view.
	 */
	private Fillup getFillupFromViewsContainer() {
		Fillup temp=new Fillup();
		if(_fillup.getId()!=Fillup.EMPTY_FILLUP.getId()){
			temp.setId(_fillup.getId());
		}
		temp.setDate(((EditText)viewsContainer.get(R.id.txtDate)).getText().toString());
		temp.setDistance(Long.parseLong(((EditText)viewsContainer.get(R.id.txtDistance)).getText().toString()));
		temp.setQt(Float.parseFloat(((EditText)viewsContainer.get(R.id.txtQuantity)).getText().toString()));
		temp.setPrice(Float.parseFloat(((EditText)viewsContainer.get(R.id.txtPrice)).getText().toString()));
		temp.setPartial(((CheckBox)viewsContainer.get(R.id.chkPartial)).isChecked());
		return temp;
	}
	
}
