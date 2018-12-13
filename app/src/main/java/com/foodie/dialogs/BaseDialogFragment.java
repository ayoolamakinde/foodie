package com.foodie.dialogs;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;


/**
 * Created by FAkinola on 13/12/2018.
 */
public class BaseDialogFragment extends DialogFragment {
    /**
     * Dismiss all DialogFragments added to given FragmentManager and child fragments
     */
    public void dismissAllDialogs(FragmentManager manager) {
        List<Fragment> fragments = manager.getFragments();

        if (fragments == null)
            return;

        for (Fragment fragment : fragments) {
            if (fragment instanceof DialogFragment) {
                DialogFragment dialogFragment = (DialogFragment) fragment;
                dialogFragment.dismissAllowingStateLoss();
            }

            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            if (childFragmentManager != null)
                dismissAllDialogs(childFragmentManager);
        }
    }

}
