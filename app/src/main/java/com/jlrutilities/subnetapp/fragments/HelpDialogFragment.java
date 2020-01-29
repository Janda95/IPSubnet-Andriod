package com.jlrutilities.subnetapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.jlrutilities.subnetapp.R;

public class HelpDialogFragment extends DialogFragment {

  public HelpDialogFragment(){}

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

    LayoutInflater inflater = requireActivity().getLayoutInflater();

    //Custom Dialog fragment for persistence
    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
    alertDialog.setView(inflater.inflate(R.layout.dialog_help, null));
    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
        (dialog, which) -> dialog.dismiss());
    alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
      @Override
      public void onShow(DialogInterface arg0) {
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(18);
      }
    });

    return alertDialog;
  }

}
