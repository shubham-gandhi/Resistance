package com.example.android.resistance;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String PREF_FILE_NAME= "testpref";
    public static final String KEY_USER_LEARNED_dRAWER= "user_learned_drawer";


    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUderLearenedDrawer;
    private boolean mFromSavedInstantState;

    private View containerview;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUderLearenedDrawer= Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_dRAWER,"false"));

        if(savedInstanceState!= null){
            mFromSavedInstantState=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    public void setUp(int fragmentID,DrawerLayout drawerLayout, final android.support.v7.widget.Toolbar toolbar) {

        containerview= getActivity().findViewById(fragmentID);

        mDrawerLayout=drawerLayout;
        mDrawerToggle=
                new ActionBarDrawerToggle(getActivity() ,drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);

                        if(!mUderLearenedDrawer){
                            mUderLearenedDrawer=true;
                            saveToPreferences(getActivity(), KEY_USER_LEARNED_dRAWER, mUderLearenedDrawer + "");
                        }

                        getActivity().invalidateOptionsMenu();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);

                        getActivity().invalidateOptionsMenu();

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        super.onDrawerSlide(drawerView, slideOffset);
                        if(slideOffset<0.6){
                            toolbar.setAlpha(1-slideOffset);
                        }

                    }
                };

        if(!mUderLearenedDrawer && !mFromSavedInstantState)
        {
            mDrawerLayout.openDrawer(containerview);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();

            }
        });



    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){

        SharedPreferences sharedPreferences= context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){

        SharedPreferences sharedPreferences= context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(preferenceName, defaultValue);
    }
}