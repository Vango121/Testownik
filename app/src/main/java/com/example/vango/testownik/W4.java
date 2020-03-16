package com.example.vango.testownik;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class W4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w4);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B0CAFF")));
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.statusbar));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        if(item.getItemId()==R.id.Ustawienia){
            Intent intentt= new Intent(W4.this,Settings.class);
            W4.this.startActivity(intentt);
            return true;
        }
        if(item.getItemId()==R.id.Report){
            Intent intentt= new Intent(W4.this,Report_activity.class);
            W4.this.startActivity(intentt);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void pytania(View view){
        Intent intent = new Intent(W4.this,Miernictwo.class);
        W4.this.startActivity(intent);
    }
    public void air(View view){
        Intent intent = new Intent(W4.this,Podstawy_Air.class);
        W4.this.startActivity(intent);
    }
}
