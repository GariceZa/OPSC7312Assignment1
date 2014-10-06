package assignment.example.com.opsc7312assignment1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Gareth on 26/07/2014.
 */
public class Q2SaveDialogFragment extends DialogFragment {

    static Q2SaveDialogFragment newInstance(String title) {
    /*
       allows a new instance of the fragment to be created
       and accepts arguments to be displayed
    */
        Q2SaveDialogFragment SDF = new Q2SaveDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        SDF.setArguments(args);

        return SDF;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
            /*
                Creating an alert dialog with an OK and cancel button
                 OK and Exit buttons call the doPositiveClick()
                and doNegativeClick() methods in the Question2 class
            */
        String title = getArguments().getString("title");
        String message  = getString(R.string.nospam);
        String ok       = getString(R.string.ok);
        String cancel   = getString(R.string.cancel);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Question2)getActivity()).doSaveMessage();
                    }
                })
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Question2)getActivity()).doNotSaveMessage();
                    }
                }).create();
    }
}


