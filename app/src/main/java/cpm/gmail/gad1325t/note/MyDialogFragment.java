package cpm.gmail.gad1325t.note;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //生成
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        Bundle args= Objects.requireNonNull(getArguments());
        final String targetString=args.getString("targetString");
        final String txtread=args.getString("txtread");
        //設定
        return builder.setTitle("Attention!")
                .setMessage("Are you really want to delete '"+targetString+"'?")
                .setIcon(R.drawable.coution)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity callingActivity=(MainActivity)getActivity();
                        callingActivity.Delfile(targetString,txtread);
                        dismiss();
                    }
                })
                .setNegativeButton("Cancel",null)

                .create();



    }
}
