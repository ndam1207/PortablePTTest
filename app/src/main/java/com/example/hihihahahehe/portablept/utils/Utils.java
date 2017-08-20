package com.example.hihihahahehe.portablept.utils;

import android.support.design.widget.TabLayout;

/**
 * Created by hihihahahehe on 8/9/17.
 */

public class Utils {

    public static void setIconBottomBar(TabLayout tabLayout, int[] listIcon){
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        for (int i = 0; i < listIcon.length; i++) {
            tabLayout.getTabAt(i).setIcon(listIcon[i]);
        }
        for (int i = 1; i < listIcon.length; i++) {
            tabLayout.getTabAt(i).getIcon().setAlpha(100);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static void addTab(TabLayout tabLayout, String title) {
        tabLayout.addTab(tabLayout.newTab().setText(title));
    }


}
