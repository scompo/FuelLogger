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
