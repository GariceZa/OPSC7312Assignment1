package assignment.example.com.opsc7312assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Gareth on 26/07/2014.
 */
public class Q2Help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loads the question 2 help activity
        setContentView(R.layout.q2help);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // checks which menu item was clicked
        int id = item.getItemId();
        if (id == R.id.mnuQ2HelpBack)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.q2help, menu);
        return true;
    }
}
