package assignment.example.com.opsc7312assignment1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Gareth on 23/07/2014.
 */
public class Q1ExitDialogFragment extends DialogFragment {


    static Q1ExitDialogFragment newInstance(String title) {
    /*
       allows a new instance of the fragment to be created
       and accepts arguments to be displayed
    */
        Q1ExitDialogFragment EDF = new Q1ExitDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        EDF.setArguments(args);

        return EDF;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
            /*
                Creating an alert dialog with an OK and cancel button
                 OK and Exit buttons call the doPositiveClick()
                and doNegativeClick() methods in the Question1 class
            */
        String title = getArguments().getString("title");
        String message  = getString(R.string.exitmessage);
        String ok       = getString(R.string.ok);
        String cancel   = getString(R.string.cancel);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Question1)getActivity()).doPositiveClick();
                    }
                })
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Question1)getActivity()).doNegativeClick();
                    }
                }).create();
    }

}
