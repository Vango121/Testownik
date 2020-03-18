package com.example.vango.testownik;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import junit.framework.Test;

public class Testownik extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testownik);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B0CAFF")));
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.statusbar));
        }

    }
    public void Click(View view){
        Intent intent = new Intent(Testownik.this,W4.class);
        Testownik.this.startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

public boolean onOptionsItemSelected(MenuItem item){
switch (item.getItemId()){
    case R.id.Ustawienia:
        Intent intentt= new Intent(Testownik.this,Settings.class);
        Testownik.this.startActivity(intentt);
        return true;
    case R.id.Report:
        Intent intenttt= new Intent(Testownik.this,Report_activity.class);
        Testownik.this.startActivity(intenttt);
        return true;

    default: return super.onOptionsItemSelected(item);
}
}
}
