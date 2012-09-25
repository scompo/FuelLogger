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
import org.scompo.android.fuellogger.DB.Fillup;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Implements an adapter for the list to use with a list of Fillups.
 * 
 * @author scompo
 * @version 1.0
 */
public class FillupArrayAdapter extends ArrayAdapter<Fillup> {
	
	private Context mContext;
	private List <Fillup> values;
	
	/**
	 * Constructor.
	 * 
	 * @param context The context.
	 * @param viewResourceId The name of the resource to fill.
	 * @param fill the list of fillup to add.
	 */
	public FillupArrayAdapter(Context context, int viewResourceId, List<Fillup> fill){
		// Be careful to get the right constructor.
		super(context, viewResourceId, fill);
		mContext=context;
		values = fill;
	}
	
	/**
	 * Really it's not needed.. btw.. it's still here.
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}
	
	/**
	 * To implement when the adapter fills the wiew.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView;
		LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
		row = inflater.inflate(R.layout.row_layout, parent,false);
		TextView txtQt = (TextView) row.findViewById(R.id.txt_qt);
		TextView txtData = (TextView) row.findViewById(R.id.txt_data);
		txtQt.setText(String.valueOf(values.get(position).getQt()));
		txtData.setText(values.get(position).getDate()+" - "+
						String.valueOf(values.get(position).getDistance()));
		return row;
	}
	
}
