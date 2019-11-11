package com.nsromapa.say.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nsromapa.say.bottomnav.fragments.Explore;
import com.nsromapa.say.bottomnav.fragments.Production;
import com.nsromapa.say.bottomnav.fragments.Stacks;
import com.nsromapa.say.bottomnav.fragments.User;
import com.nsromapa.say.bottomnav.view.CurvedBottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private CurvedBottomNavigationView bottom_navigation_view;
    private FloatingActionButton fab_news_feed;
    private boolean isNavUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isNavUp = true;
        bottom_navigation_view = findViewById(R.id.bottom_navigation_view);
        fab_news_feed = findViewById(R.id.fab_news_feed);
        setDash(bottom_navigation_view);
        showFragment(new Explore());
    }


    // Set up the bottom navigation bar.....
    private void setDash(CurvedBottomNavigationView view) {
        view.inflateMenu(R.menu.bnvm_dash);
        view.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        view.enableAnimation(false);
        view.setTextSize(10.0f);
        view.setIconsMarginTop(12);
        view.setIconSize(30.0f, 30.0f);

        view.getBottomNavigationItemView(2).setClickable(false);

        for (int i = 0; i < view.getItemCount(); i++) {
            view.getLargeLabelAt(i).setPadding(0, 0, 0, 0);
            view.getBottomNavigationItemView(i).setBackground(null);
        }

        //Setup the menu icon click listener
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nv_explorer:
                        showFragment(new Explore());
                        break;
                    case R.id.nv_profile:
                        showFragment(new User());
                        break;
                    case R.id.nv_production:
                        showFragment(new Production());
                        break;
                    case R.id.nv_stacks:
                        showFragment(new Stacks());
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void showFragment(Fragment fragment){
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }

    // When the center floating button is clicked
    //Animate the bottom nav
    public void onSlideNavClick(View view) {
        if (isNavUp) {
            slideDown(bottom_navigation_view);
            slideDownFloat(fab_news_feed);
            fab_news_feed.setImageResource(R.drawable.ic_menu_black_24dp);
        } else {
            slideUp(bottom_navigation_view);
            slideUpFloat(fab_news_feed);
            fab_news_feed.setImageResource(R.drawable.ic_close_black_24dp);
        }
        isNavUp = !isNavUp;
    }


    // slide the view from below itself to the current position
    public void slideUp(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself -
    // it own height making it to the bottom of the page
    public void slideDownFloat(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                (view.getHeight() - 52f)); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself +
    // it own height making it to the original position
    public void slideUpFloat(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                (view.getHeight() - 52f),                 // fromYDelta
                0); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }


}
