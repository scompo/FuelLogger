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

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

/**
 * The main activity for the project.
 * It contains all the application logics.
 * 
 * @author scompo
 * @version 1.0
 */
public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        	
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
