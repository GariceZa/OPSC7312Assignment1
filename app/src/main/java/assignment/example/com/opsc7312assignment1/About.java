package assignment.example.com.opsc7312assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Gareth on 25/07/2014.
 */
public class About extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loads the about xml file
        setContentView(R.layout.about);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // checks which menu item was clicked
        int id = item.getItemId();
        if (id == R.id.mnuAboutBack)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }
}
