package assignment.example.com.opsc7312assignment1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Gareth on 26/07/2014.
 */
public class Question2 extends Activity {

    //Variables
    TextView tvMessage ;
    IntentFilter intentFilter;
    String message;
    //--------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        setUp();

        tvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForSpam();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //registering the receiver
        registerReceiver(intentReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregistering the receiver
        unregisterReceiver(intentReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // checks which menu item was clicked and then performs tasks accordingly
        int id = item.getItemId();
        if (id == R.id.mnuSpamChk)
        {
            checkForSpam();
        }
        else if(id == R.id.mnuViewMsgs)
        {
            startActivity(new Intent(this,Q2ViewMessages.class));
        }
        else if(id == R.id.mnuHelp)
        {
            startActivity(new Intent(this,Q2Help.class));
        }

        else if(id == R.id.mnuExit)
        {
            Q2ExitDialogFragment EDF = Q2ExitDialogFragment.newInstance(getString(R.string.exit)+"?");
            EDF.show(getFragmentManager(),"dialog");
        }

        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //retrieving the sms from the intent and displaying it to the user
            tvMessage.setText(intent.getExtras().getString("sms"));
            message = (intent.getExtras().getString("sms"));
        }
    };

    public void doPositiveClick() {
        // exits the activity if the user clicks ok
        finish();
    }

    public void doNegativeClick() {
        //do nothing
    }

    public void doSaveMessage() {
        //if the user selects to save the message
        SaveMessageToFile();
    }

    public void doNotSaveMessage() {
        // do nothing
    }

    public void SaveMessageToFile() {
        //saving the sms
        try
        {
            FileOutputStream fOut = openFileOutput("safeMessages.txt",MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(message);
            osw.append("\n");
            osw.close();
            Toast.makeText(getApplicationContext(),getString(R.string.smssaved), Toast.LENGTH_LONG).show();
        }
        catch(IOException ioe)
        {
            Toast.makeText(getApplicationContext(), getString(R.string.errorsaving)+ ioe.toString(), Toast.LENGTH_LONG).show();
        }

    }

    private void setUp() {
        //initializing the text view
        tvMessage = (TextView) findViewById(R.id.tvNewMessage);

        //creating intent to filter for SMS's
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
    }

    private void checkForSpam() {
    /*
        checks if the text view contains a new message
        validates if it is spam and allows the user to
        save the message if it is not spam
     */
        if(tvMessage.getText().toString().equals("No New Messages")){
            Toast.makeText(getApplicationContext(),getString(R.string.nomessagestocheck), Toast.LENGTH_LONG).show();
        }
        else{
            if(message.contains("COMPETITION") ||message.contains("Competition") || message.contains("competition")
                    || message.contains("WIN")|| message.contains("Win") || message.contains("win")){
                Toast.makeText(getApplicationContext(),getString(R.string.spamwarning),Toast.LENGTH_LONG).show();
            }
            else{
                Q2SaveDialogFragment SDF = Q2SaveDialogFragment.newInstance(getString(R.string.Save)+"?");
                SDF.show(getFragmentManager(), "dialog");
            }
        }
    }
}

