package assignment.example.com.opsc7312assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Gareth on 25/07/2014.
 */
public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loads the home screen
        setContentView(R.layout.home);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // checks which menu item was clicked
        int id = item.getItemId();
        if (id == R.id.mnuQuestion1)
        {
            startActivity(new Intent(this,Question1.class));
        }
        else if (id == R.id.mnuQuestion2)
        {
            startActivity(new Intent(this,Question2.class));
        }
        else if (id == R.id.mnuAbout)
        {
            startActivity(new Intent(this,About.class));
        }
        else if (id == R.id.mnuExit)
        {
            String exit = getString(R.string.exit);
            HomeExitDialog EDF = HomeExitDialog.newInstance(exit+"?");
            EDF.show(getFragmentManager(),"dialog");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public void onClickStartQ1(View view) {
        // starts question one activity
        startActivity(new Intent(this,Question1.class));
    }

    public void doPositiveClick() {
        //exits out of the application completely
        finish();
    }

    public void doNegativeClick() {
        //do nothing
    }
}
