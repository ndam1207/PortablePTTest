package com.example.hihihahahehe.portablept.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hihihahahehe.portablept.fragments.PackFragment;
import com.example.hihihahahehe.portablept.fragments.CalendarFragment;
import com.example.hihihahahehe.portablept.fragments.HomeFragment;
import com.example.hihihahahehe.portablept.fragments.NotificationFragment;
import com.example.hihihahahehe.portablept.fragments.PersonFragment;

/**
 * Created by hihihahahehe on 8/9/17.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private HomeFragment homeFragment;
    private PackFragment packFragment;
    private CalendarFragment calendarFragment;
    private NotificationFragment notificationFragment;
    private PersonFragment personFragment;
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        homeFragment = new HomeFragment();
        packFragment = new PackFragment();
        calendarFragment = new CalendarFragment();
        notificationFragment = new NotificationFragment();
        personFragment = new PersonFragment();

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = homeFragment;
                break;
            case 1:
                fragment = packFragment;
                break;
            case 2:
                fragment = calendarFragment;
                break;
            case 3:
                fragment = notificationFragment;
                break;
            case 4:
                fragment = personFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
