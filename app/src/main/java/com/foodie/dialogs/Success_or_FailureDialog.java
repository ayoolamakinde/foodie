package com.foodie.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodie.R;
import com.foodie.Utils.Constants;
import com.foodie.custom.GIFView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FAkinola on 13/12/2018.
 */
public class Success_or_FailureDialog extends BaseDialogFragment {
    @BindView(R.id.fireworks_start)
    ImageView fireworks1;
    @BindView(R.id.fireworks_mid)
    ImageView fireworks2;
    @BindView(R.id.fireworks_end)
    ImageView fireworks3;
    @BindView(R.id.next_button)
    Button button;
    @BindView(R.id.gif)
    GIFView gifView;
    @BindView(R.id.message)
    TextView message;

    private Bundle args;
    private int gameLevel;
    private boolean success;

    public static DialogFragment newInstance(Bundle args) {
        DialogFragment frag = new Success_or_FailureDialog();
        if (args != null) {
            frag.setArguments(args);
        }
        return frag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_success_or_failure, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            args = getArguments();
        } else {
            args = savedInstanceState.getBundle(Constants.ARGS);
        }

        if (args != null) {
            //get data from bundle
            gameLevel = args.getParcelable(Constants.GAME_LEVEL);
            success = args.getParcelable(Constants.SUCCESS);
        }
        ButterKnife.bind(this, view);
        //setup view
        if (success) {
            setupView();
        } else {
            //button.setBackground();
            gifView.setGIFResource(R.drawable.cry);
        }
    }

    private void setupView() {
        Animation ani = new TranslateAnimation(30, 105, 500, 32);
        ani.setDuration(700);
        ani.setFillAfter(false);
        ani.setRepeatCount(-1);
        ani.setRepeatMode(Animation.RELATIVE_TO_PARENT);
        fireworks1.setAnimation(ani);
        fireworks1.setVisibility(View.VISIBLE);

        Animation mAnimation = new TranslateAnimation(0, 500, 0, 500);
        mAnimation.setDuration(850);
        mAnimation.setFillAfter(true);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        fireworks2.setAnimation(mAnimation);
        fireworks2.setVisibility(View.VISIBLE);

        ani = new TranslateAnimation(30, 105, 500, 32);
        ani.setDuration(700);
        ani.setFillAfter(false);
        ani.setRepeatCount(-1);
        ani.setRepeatMode(Animation.ZORDER_BOTTOM);
        fireworks3.setAnimation(ani);
        fireworks3.setVisibility(View.VISIBLE);


    }

    private void next(View view) {
        //todo close dialog and load the next game
        if (success) {
            dismissAllDialogs(getActivity().getSupportFragmentManager());
        } else {
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle( getString(R.string.restart_question))
                    .setMessage(getString(R.string.restart_question_content))
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dismissAllDialogs(getActivity().getSupportFragmentManager());
                        }
                    })
                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //dismiss dialog
                            dialogInterface.dismiss();
                            //todo close the app
                            getActivity().onBackPressed();
                        }
                    })
                    .create();

            dialog.show();
        }
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
