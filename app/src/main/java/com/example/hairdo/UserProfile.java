package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class UserProfile  extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

//    ImageView search;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile2);
        //
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().hide();
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }


//        search = findViewById(R.id.tool_search);




//        FloatingActionButton fab = findViewById(R.id. fab ) ;
//        fab.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick (View view) {
//                Snackbar. make (view , "Replace with your own action" ,
//                        Snackbar. LENGTH_LONG )
//                        .setAction( "Action" , null ).show() ;
//            }
//        }) ;
        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer ,toolbar, R.string. navigation_drawer_open ,
                R.string. navigation_drawer_close ) ;
        drawer.addDrawerListener(toggle) ;
        toggle.syncState() ;
        NavigationView navigationView = findViewById(R.id. nav_view_user ) ;
        navigationView.setNavigationItemSelectedListener( this ) ;
    }

    public  void onSearchClick(View v){
        Intent inte = new Intent(this,SearchSalon.class);
        startActivity(inte);
    }

    @Override
    public void onBackPressed () {
        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        if (drawer.isDrawerOpen(GravityCompat. START )) {
            drawer.closeDrawer(GravityCompat. START ) ;
        } else {
            super .onBackPressed() ;
        }
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer , menu) ;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId() ;
        if (id == R.id. action_settings ) {
            return true;
        }
        return super .onOptionsItemSelected(item) ;
    }
    @SuppressWarnings ( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected ( @NonNull MenuItem item) {

        int id = item.getItemId() ;
        if (id == R.id.nav_home ) {
            UserHome fragment = new UserHome();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment_user,fragment);
            ft.commit();
        } else if (id == R.id.nav_profile ) {

            UserProfileEdit fragment = new UserProfileEdit();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment_user,fragment);
            ft.commit();

        } else if (id == R.id. nav_upcoming_appointments ) {

//            Intent intent = new Intent(this,.class);
//            startActivity(intent);
        }
        else if (id == R.id. nav_summary ) {

//            Intent intent = new Intent(this,ContactUs.class);
//            startActivity(intent);
        }
        else if (id == R.id. nav_notification ) {

//            Intent intent = new Intent(this,ContactUs.class);
//            startActivity(intent);
        }
        else if (id == R.id. nav_payments ) {

            Intent intent = new Intent(this,PaymentSummary.class);
            startActivity(intent);
        }
        else if (id == R.id. nav_ContactUs ) {

            Intent intent = new Intent(this,ContactUs.class);
            startActivity(intent);
        }

//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent searchInt = new Intent(v.getContext(),SearchSalon.class);
//                startActivity(searchInt);
//            }
//        });

        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        drawer.closeDrawer(GravityCompat. START ) ;
        return true;
    }
}