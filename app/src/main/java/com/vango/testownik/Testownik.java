package com.vango.testownik;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Testownik extends AppCompatActivity implements MainAdapter.OnNoteListener {

RecyclerView recyclerView;
ArrayList<MainModel> mainModels;
MainAdapter mainAdapter;
    List<String> namee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testownik);
        recyclerView=findViewById(R.id.recycler);
        Integer[] logo={R.drawable.miernictwo,R.drawable.air,R.drawable.telekomuna
                ,R.drawable.pps,R.drawable.pps,R.drawable.izs,R.drawable.po};
        namee=new ArrayList<>();
        String []names={"Miernictwo","Podstawy Air","Podstawy Telekomunikacji","PPS","PPS 2","IZS","Programowanie Obiektowe"};
        namee= Arrays.asList(names);
        mainModels=new ArrayList<>();
        for (int i = 0; i <logo.length; i++) {
            MainModel model = new MainModel(logo[i],names[i]);
            mainModels.add(model);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                Testownik.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainAdapter= new MainAdapter(Testownik.this,mainModels,this);
        recyclerView.setAdapter(mainAdapter);
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

    @Override
    public void onNoteClick(int position) {
        if(namee.get(position).equals("Miernictwo")){
            Intent intent = new Intent(Testownik.this,Miernictwo.class);
            intent.putExtra("adres","http://hackheroes.cba.pl/plik1.txt");
        startActivity(intent);
        }
        else if(namee.get(position).equals("Podstawy Air")){
            Intent intent = new Intent(Testownik.this,Miernictwo.class);
            intent.putExtra("adres","http://hackheroes.cba.pl/combined.txt");
            Testownik.this.startActivity(intent);
        }
        else if(namee.get(position).equals("Podstawy Air")){
            Intent intent = new Intent(Testownik.this,Miernictwo.class);
            intent.putExtra("adres","http://hackheroes.cba.pl/combined.txt");
            Testownik.this.startActivity(intent);
        }
        else if(namee.get(position).equals("Podstawy Telekomunikacji")){
            Intent intent = new Intent(Testownik.this,Miernictwo.class);
            intent.putExtra("adres","http://hackheroes.cba.pl/pt.txt");
            Testownik.this.startActivity(intent);
        }
        else if(namee.get(position).equals("PPS")){
            Intent intent = new Intent(Testownik.this,Miernictwo.class);
            intent.putExtra("adres","http://hackheroes.cba.pl/pps.txt");
            Testownik.this.startActivity(intent);
        }
        else if(namee.get(position).equals("PPS 2")){
            Intent intent = new Intent(Testownik.this,Miernictwo.class);
            intent.putExtra("adres","http://hackheroes.cba.pl/pps2.txt");
            Testownik.this.startActivity(intent);
        }
        else if(namee.get(position).equals("IZS")){
            Intent intent = new Intent(Testownik.this,Miernictwo.class);
            intent.putExtra("adres","http://hackheroes.cba.pl/izs.txt");
            Testownik.this.startActivity(intent);
        }
        else if(namee.get(position).equals("Programowanie Obiektowe")){
            Intent intent = new Intent(Testownik.this,Miernictwo.class);
            intent.putExtra("adres","http://hackheroes.cba.pl/po.txt");
            Testownik.this.startActivity(intent);
        }
    }
}
