package com.foodie.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.foodie.R;
import com.foodie.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FAkinola on 9/2/2016.
 */
public class HintDialog extends BaseDialogFragment {
    @BindView(R.id.hint_body)
    TextView hintText;

    private Bundle args;

    public static DialogFragment newInstance(Bundle args) {
        DialogFragment frag = new HintDialog();
        if (args != null) {
            frag.setArguments(args);
        }
        return frag;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_hint, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//todo call firebase to get message

        ButterKnife.bind(this, view);
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

}
