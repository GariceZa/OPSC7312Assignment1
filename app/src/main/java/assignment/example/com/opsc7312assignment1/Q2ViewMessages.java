package assignment.example.com.opsc7312assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Gareth on 27/07/2014.
 */
public class Q2ViewMessages extends Activity {

    //Variables
    static final int READ_BLOCK_SIZE = 100;
    TextView tvSavedMessages;
    //---------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q2viewsavedmessages);
        tvSavedMessages = (TextView)findViewById(R.id.tvSavedMessages);
        // retrieving saved messaged
        loadFile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.q2viewmessages,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // checks which menu item was clicked and then performs tasks accordingly
        int id = item.getItemId();

        if(id == R.id.mnuViewMsgBack)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void loadFile() {
    //reads the safeMessages.txt file and loads the data into tvSavedMessages
        try
        {
            FileInputStream fIn = openFileInput("safeMessages.txt");
            InputStreamReader isr = new InputStreamReader(fIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String data = "";
            int charRead;

            try
            {
                while((charRead = isr.read(inputBuffer)) > 0)//while there is still data to be read
                {
                    String readString = String.copyValueOf(inputBuffer,0,charRead);//read in the data
                    data += readString;	//append the data
                    inputBuffer = new char [READ_BLOCK_SIZE];
                }
                tvSavedMessages.setText(data);//when finished reading set text view to data
                Toast.makeText(getBaseContext(),getString(R.string.fileloaded), Toast.LENGTH_LONG).show();//display success msg
            }
            catch (IOException e)//if there are errors reading the file, throw this error
            {
                Toast.makeText(getBaseContext(),getString(R.string.error)+ e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        catch (FileNotFoundException e) //if the file does not exist, throw this msg
        {
            Toast.makeText(getBaseContext(),getString(R.string.error)+getString(R.string.nomessages), Toast.LENGTH_LONG).show();
        }
    }
}
