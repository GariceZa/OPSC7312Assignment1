package assignment.example.com.opsc7312assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;


public class Question1 extends Activity  {

    //Variables
    AutoCompleteTextView ACTVRequiredContact,ACTVSendTo;
    List<String> storedContacts,storedNumbers;
    TextView send,receive;
    String sendNumber = "",contactToSend = "",receiveNumber = "",contactToReceive = "";
    Button btnSendSMS;
    //---------------------------------------------

    //Class instantiations
    Q1ContactsProvider CP = new Q1ContactsProvider();
    Q1SMSSender SMS = new Q1SMSSender();
    //--------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        setUp();

        /*
            creating an itemClickListener to gather contact information when a contact is selected in
            the RequiredContact auto complete text view
        */
        ACTVRequiredContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // retrieving the selected contacts name
                contactToSend = (String)parent.getItemAtPosition(position);
                /*
                    searching the storedContacts list to match the selected contact
                    with its stored number in the storedNumbers list
                 */
                for(int ID = 0; ID < storedContacts.size();ID++)
                {
                    if(storedContacts.get(ID).equals(contactToSend))
                    {
                        sendNumber = storedNumbers.get(ID);
                    }
                }

                // displaying the selected contacts number in the text view
                send.setText(getString(R.string.send) + sendNumber);

                // Validating contact selection is not the same for both fields
                if(validateSelection())
                {
                   btnSendSMS.setEnabled(true);
                }
                else
                {
                    btnSendSMS.setEnabled(false);
                }
            }
        });

        /*
            creating an itemClickListener to gather contact information when a contact is selected in
            the sendTo auto complete text view
        */
        ACTVSendTo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // retrieving the selected contacts name
                contactToReceive = (String)parent.getItemAtPosition(position);

                /*
                    searching the storedContacts list to match the selected contact
                    with its stored number in the storedNumbers list
                 */
                for(int ID = 0; ID < storedContacts.size();ID++)
                {
                    if(storedContacts.get(ID).equals(contactToReceive))
                    {
                        receiveNumber = storedNumbers.get(ID);
                    }
                }

                // displaying the selected contacts number in the text view
                receive.setText(getString(R.string.to) + receiveNumber);

                // Validating contact selection is not the same for both fields
                if(validateSelection())
                {
                    btnSendSMS.setEnabled(true);
                }
                else
                {
                    btnSendSMS.setEnabled(false);
                }
            }
        });

        /*
            creating a text watcher to perform validation on the send text view
            when the user makes changes to the selected contact
         */
        ACTVRequiredContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*
                    if the users has deleted the selected contact from the text view
                    update the send text view and disable the send sms button
                */
                if(s.length() == 0)
                {
                    send.setText(getString(R.string.send));
                    btnSendSMS.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            //do nothing

            }
        });

        /*
            creating a text watcher to perform validation on the receiver text view
            when the user makes changes to the selected contact
         */
        ACTVSendTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*
                    if the users has deleted the selected contact from the text view
                    update the send text view and disable the send sms button
                */
                if(s.length() == 0)
                {
                    receive.setText(getString(R.string.to));
                    btnSendSMS.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // checks which menu item was clicked
        int id = item.getItemId();
        if (id == R.id.q1Exit)
        {
            exitDialog();
        }
        if(id == R.id.q1Help)
        {
            startActivity(new Intent(this,Q1Help.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickExit(View view) {

        // Called when the user clicks the exit button
        exitDialog();

    }

    public void onClickSendMessage(View view) {
        //calls the SendSMS method in Q1SMSSender and passes the parameters below to send the message
        //displays a toast message of the sms to the user and clears all variables/views
        SMS.SendSMS(receiveNumber,getString(R.string.hi) + contactToReceive + getString(R.string.thenumberfor) + contactToSend + getString(R.string.is) + sendNumber);
        Toast.makeText(getApplicationContext(),getString(R.string.message) + getString(R.string.hi) + contactToReceive + getString(R.string.thenumberfor) + contactToSend + getString(R.string.is) +sendNumber,Toast.LENGTH_LONG).show();
        reset();
    }

    public void doPositiveClick() {
        /*
            if the user clicks ok in the exit dialog
            then finish the application
         */
        finish();
    }

    public void doNegativeClick() {
        //do nothing
    }

    private boolean validateSelection() {

        // Validating contact selection is not the same for both fields
        if(!sendNumber.equals("") && !receiveNumber.equals("")){
            if(!sendNumber.equals(receiveNumber)){
                return true;
            }
            else{
                return false;
            }
        }

        return false;
    }

    private void setUp() {
        /*
            Called in onCreate to initialize variables & views
         */

        //Initializing list arrays
        storedContacts  = CP.getContacts(this);
        storedNumbers   = CP.getNumbers(this);
        //------------------------

        //Linking text views
        send    = (TextView)findViewById(R.id.tvSend);
        receive = (TextView)findViewById(R.id.tvTo);
        //------------------------

        //Initializing the auto complete text views
        ACTVRequiredContact = (AutoCompleteTextView)findViewById(R.id.txtContactToSend);
        ACTVRequiredContact.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,storedContacts));
        ACTVRequiredContact.setThreshold(1); //how many characters need to be typed before suggestions are shown


        ACTVSendTo = (AutoCompleteTextView)findViewById(R.id.txtContactToReceive);
        ACTVSendTo.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,storedContacts));
        ACTVSendTo.setThreshold(1); //how many characters need to be typed before suggestions are shown
        //-----------------------

        //Linking activity buttons
        btnSendSMS = (Button)findViewById(R.id.btnSend);
        //-----------------------
    }

    private void reset() {

        /*
            Clearing all stored values
         */
        sendNumber = "";
        contactToSend = "";
        receiveNumber = "";
        contactToReceive = "";

        ACTVRequiredContact.setText("");
        ACTVSendTo.setText("");

        send.setText("Send: ");
        receive.setText("To: ");

        btnSendSMS.setEnabled(false);


    }

    private void exitDialog() {

        // launching an exit dialog
        Q1ExitDialogFragment EDF = Q1ExitDialogFragment.newInstance(getString(R.string.exit)+"?");
        EDF.show(getFragmentManager(),"dialog");
    }

}
