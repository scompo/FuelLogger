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

import org.scompo.android.fuellogger.FragmentDetails.OnButtonClickedListener;
import org.scompo.android.fuellogger.FragmentMenu.OnMenuButtonClickedListener;
import org.scompo.android.fuellogger.FragmentResume.OnFillupSelectedListener;
import org.scompo.android.fuellogger.DB.DBHelper;
import org.scompo.android.fuellogger.DB.Fillup;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

/**
 * The main activity for the project.
 * It contains all the application logics.
 * 
 * @author scompo
 * @version 1.0
 */
public class MainActivity extends FragmentActivity implements OnButtonClickedListener, 
																OnFillupSelectedListener,
																OnMenuButtonClickedListener{
	
	DBHelper DB;
	public ArrayList<Fillup> listFillup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = DBHelper.getInstance(this);
        setContentView(R.layout.activity_main);
        // Check if the layout currently uses the Fragment container.
        // If so I should load the first frame.
        if(findViewById(R.id.main_fragment_container)!=null){
        	//now I verify that I'm not returning from a previous state.
        	if(savedInstanceState != null){
        		//If I'm already alive I should just return.
        		return;
        	}
        	// Let's load the first fragment!
        	FragmentMenu menu = new FragmentMenu();
        	menu.setArguments(getIntent().getExtras());
        	
        	getSupportFragmentManager().beginTransaction().add(
					R.id.main_fragment_container, menu).commit();        	
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onFillupSelected(int position) {
		Toast.makeText(this, listFillup.get(position).toString(), Toast.LENGTH_LONG).show();
    	FragmentDetails details = new FragmentDetails();
    	details.setContents(listFillup.get(position));
    	getSupportFragmentManager().beginTransaction().replace(
				R.id.main_fragment_container, details).commit();
	}

	public void onButtonClicked(int resourceId, Fillup fillup) {
		switch (resourceId) {
		case R.id.cmdSave:
			DB.addFillup(fillup);
			FragmentMenu menu= new FragmentMenu();
        	getSupportFragmentManager().beginTransaction().replace(
					R.id.main_fragment_container, menu).commit();    
			break;
			
		default:
			break;
		}
		
	}

	public void onMenuButtonClicked(int resourceID) {
		switch (resourceID) {
		case R.id.cmdAddNew:
        	FragmentDetails details = new FragmentDetails();
        	//details.setContents(new Fillup(100,100,100,"20/10/2012",100,true));
        	getSupportFragmentManager().beginTransaction().replace(
					R.id.main_fragment_container, details).commit();
			break;
			
		case R.id.cmdViewData:
			listFillup = (ArrayList<Fillup>) DB.getAllFillups();
			FragmentResume resume = new FragmentResume();
			resume.setList(listFillup);
        	getSupportFragmentManager().beginTransaction().replace(
					R.id.main_fragment_container, resume).commit();
			break;
			
		case R.id.cmdExit:
			this.finish();
			break;
			
		default:
			break;
		}
	}
}
