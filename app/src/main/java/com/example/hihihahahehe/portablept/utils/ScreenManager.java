package com.example.hihihahahehe.portablept.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by hihihahahehe on 8/6/17.
 */

public class ScreenManager {
    private static final String TAG = ScreenManager.class.toString();

    public static void openFragment(FragmentManager fragmentManager, Fragment fragment, int layoutID, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int layoutID, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layoutID, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
