package assignment.example.com.opsc7312assignment1;

/**
 * Created by Gareth on 26/07/2014.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;



public class Q2SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //end broadcast
        this.abortBroadcast();

        //get sms
        Bundle SMSbundle = intent.getExtras();
        SmsMessage[] SMSMsg = null;
        String SMSStr = "New SMS From ";

        if(SMSbundle != null)
        {
            //retrieve sms message
            Object [] pdus = (Object[]) SMSbundle.get("pdus");
            SMSMsg = new SmsMessage[pdus.length];

            for(int cnt = 0;cnt < SMSMsg.length;cnt++) {
                SMSMsg[cnt] = SmsMessage.createFromPdu((byte[]) pdus[cnt]);

                if (cnt == 0) {
                    //obtaining sender number
                    SMSStr += SMSMsg[cnt].getOriginatingAddress();
                    SMSStr += ": ";
                }
                // SMSStr set to sms message
                SMSStr += SMSMsg[cnt].getMessageBody().toString();
            }

        }

        //intent to update the activity with the sms
        Intent broadCastIntent = new Intent();
        broadCastIntent.setAction("SMS_RECEIVED_ACTION");
        broadCastIntent.putExtra("sms",SMSStr);
        context.sendBroadcast(broadCastIntent);
    }
}
