package assignment.example.com.opsc7312assignment1;

import android.app.Activity;
import android.telephony.SmsManager;

/**
 * Created by Gareth on 25/07/2014.
 */
public class Q1SMSSender extends Activity {


    protected void SendSMS(String number,String msg) {
        //sends an sms using the parameter passed into the method
        SmsManager smsMan = SmsManager.getDefault();
        smsMan.sendTextMessage(number,null,msg,null,null);
    }
}
