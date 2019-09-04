package com.example.onthivatly;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.onthivatly.Pdf.PdfViewerFragment;
import com.example.onthivatly.cauhoitracnghiem.DethiFragment;
import com.example.onthivatly.cauhoitracnghiem.DienxoaychieuFragment;
import com.example.onthivatly.cauhoitracnghiem.HatnhanFragment;
import com.example.onthivatly.cauhoitracnghiem.HomeFragment;
import com.example.onthivatly.cauhoitracnghiem.SongcoFragment;
import com.example.onthivatly.cauhoitracnghiem.ThaukinhFragment;
import com.example.onthivatly.question.DBHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DBHelper db = new DBHelper(this);
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
        } else if (id == R.id.cauhoisongco) {
            SongcoFragment songcoFragment = new SongcoFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, songcoFragment, songcoFragment.getTag()).commit();

        } else if (id == R.id.cauhoidienxoaychieu) {
            DienxoaychieuFragment dienxoaychieuFragment = new DienxoaychieuFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, dienxoaychieuFragment, dienxoaychieuFragment.getTag()).commit();

        } else if (id == R.id.cauhoithaukinh) {
            ThaukinhFragment thaukinhFragment = new ThaukinhFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, thaukinhFragment, thaukinhFragment.getTag()).commit();

        } else if (id == R.id.cauhoihatnhan) {
            HatnhanFragment hatnhanFragment = new HatnhanFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, hatnhanFragment, hatnhanFragment.getTag()).commit();


        } else if (id == R.id.dethidaihoc) {
            DethiFragment dethiFragment = new DethiFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, dethiFragment, dethiFragment.getTag()).commit();


        }
        else if (id == R.id.pdfviewer) {
            PdfViewerFragment pdfViewerFragment = new PdfViewerFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, pdfViewerFragment, pdfViewerFragment.getTag()).commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
