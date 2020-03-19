package com.example.vango.testownik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/*
class which supports all the test name from previous idea to do activity for each test

 */


public class Miernictwo extends AppCompatActivity {
Button odpA;
Button odpB;
Button odpC;
Button odpD;
Button sprawdz;
Button dalej;
TextView pytanie;
TextView pytanienr;
    ArrayList<ArrayList<String>> pytania = new ArrayList<ArrayList<String>>();
    ArrayList<Integer> question_count= new ArrayList<>();
    int questionNumber=0;
    int multiply=10;
    int wrong = 10;
    String urlAdress="";
    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                while(urlAdress.equals("")){

                }
                BufferedReader reader = new BufferedReader(new InputStreamReader((new URL(urlAdress)).openStream(),"UTF-8"));
                String line = reader.readLine();
                String odp="";
                String pytanie="";
                String a="";
                String b="";
                String c="";
                String d="";
                int temp=0;

                for( int k = 0; line!=null; k++)
                {

                   if(temp==0){
                       odp=line;
                   }
                   else if(temp==1){
                       pytanie=line;
                   }
                  else if(temp==2){
                       a=line;
                   }
                   else if(temp==3){
                       b=line;
                    }
                   else if(temp==4){
                        c=line;
                    }
                    else if(temp==5){
                        d=line;

                    }
                    temp++;
                    line=reader.readLine();
                    if(temp==6){
                     temp=0;
                     pytania.add(new ArrayList<String>(Arrays.asList(odp,pytanie,a,b,c,d)));
                        question_count.add(multiply);
                    }
                }
            }catch (Exception e){
                Log.i("bladasync",e+"");
            }
            return null;
        }
    }
    int ktore;//numer pytania
public void wylosuj(){
    questionNumber++;
    int ile=0;

    Random random = new Random();

    do{
     ktore=random.nextInt(pytania.size());}
     while (question_count.get(ktore)==0);
    {
    Log.i("while","dziala");
    }
    Log.i("nr pyt",ktore+"");
pytanienr.setText("Pytanie nr: "+questionNumber);
pytanie.setText(pytania.get(ktore).get(1));
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    while (numbers.size() < 4) {

    int newint = random.nextInt(4)+2;
        if (!numbers.contains(newint)) {
            numbers.add(newint);
        }
    }

    odpA.setText(pytania.get(ktore).get(numbers.get(0)));
    odpB.setText(pytania.get(ktore).get(numbers.get(1)));
    odpC.setText(pytania.get(ktore).get(numbers.get(2)));
    odpD.setText(pytania.get(ktore).get(numbers.get(3)));
    Log.i("Pytanie",pytania.get(ktore).get(numbers.get(1)));




}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miernictwo);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B0CAFF"))); //kolor actionabar
        if (Build.VERSION.SDK_INT >= 21) {

            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.statusbar)); //kolor paska ze statusem
        }
        Intent intent = getIntent();
        urlAdress=intent.getExtras().getString("adres"); //pobierz adres z którego ma wczytać pytania

        odpA=(Button)findViewById(R.id.buttonA);
        odpB=(Button)findViewById(R.id.buttonB);
        odpC=(Button)findViewById(R.id.buttonC);
        odpD=(Button)findViewById(R.id.buttonD);
        sprawdz=(Button)findViewById(R.id.Check);
        dalej = (Button)findViewById(R.id.Nexty);
        pytanie=(TextView)findViewById(R.id.question);
        pytanienr=(TextView)findViewById(R.id.questionId);
        //daj buttony od razu w taki sam kolor i wyglad
        odpA.setBackgroundResource(android.R.drawable.btn_default);
        odpB.setBackgroundResource(android.R.drawable.btn_default);
        odpC.setBackgroundResource(android.R.drawable.btn_default);
        odpD.setBackgroundResource(android.R.drawable.btn_default);

        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
        String ss = spref.getString("Multiply", "4");//multiply get shared preference
        multiply=Integer.valueOf(ss);
        String s = spref.getString("Wrong", "2"); // wrong get shared preferences
        wrong=Integer.valueOf(s);

        DownloadTask task=new DownloadTask();
        try{
            task.execute().get(); //z get czeka na wykonanie
            wylosuj();
            Log.i("check list",pytania.get(1).get(1));


        }catch (Exception e){
Log.i("bladcreate",e+"");
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
            Intent intentt= new Intent(Miernictwo.this,Settings.class);
            Miernictwo.this.startActivity(intentt);
            return true;
        }
        if(item.getItemId()==R.id.Report){
            Intent intentt= new Intent(Miernictwo.this,Report_activity.class);
            Miernictwo.this.startActivity(intentt);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    boolean odpAclick=false;
    boolean odpBclick=false;
    boolean odpCclick=false;
    boolean odpDclick=false;


    public void PrzyciskOdp (View view){

        if(view.getId()==R.id.buttonA){
            if(!odpAclick) {
                odpA.setBackgroundColor(Color.YELLOW);
                odpAclick=true;
            }
            else{
                odpA.setBackgroundResource(android.R.drawable.btn_default);
                odpAclick=false;
            }
        }

        else if(view.getId()==R.id.buttonB){
            if(!odpBclick) {
                odpB.setBackgroundColor(Color.YELLOW);
                odpBclick=true;
            }
            else{
                odpB.setBackgroundResource(android.R.drawable.btn_default);
                odpBclick=false;
            }
        }

        else if(view.getId()==R.id.buttonC){
            if(!odpCclick) {
                odpC.setBackgroundColor(Color.YELLOW);
                odpCclick=true;
            }
            else{
                odpC.setBackgroundResource(android.R.drawable.btn_default);
                odpCclick=false;
            }
        }

        else if(view.getId()==R.id.buttonD){
            if(!odpDclick) {
                odpD.setBackgroundColor(Color.YELLOW);
                odpDclick=true;
            }
            else{
                odpD.setBackgroundResource(android.R.drawable.btn_default);
                odpDclick=false;
            }
        }
    }


    public void checkAnswer(View view){
        StringBuilder wynik=new StringBuilder("0000");

        int a=0;
        int aPlace;
        int b=0;
        int bPlace;
        int c=0;
        int cPlace;
        int d=0;
        int dPlace;
        aPlace=odpA.getText().charAt(0)-'a';
        if(odpAclick){
            a=1;
        }
        bPlace=odpB.getText().charAt(0)-'a';
        if(odpBclick){
            b=1;
        }
        cPlace=odpC.getText().charAt(0)-'a';
        if(odpCclick){
            c=1;
        }
        dPlace=odpD.getText().charAt(0)-'a';
        if(odpDclick){
            d=1;
            dPlace=odpD.getText().charAt(0)-'a';
        }
        wynik.setCharAt(aPlace,(char)(a+'0'));
        wynik.setCharAt(bPlace,(char)(b+'0'));
        wynik.setCharAt(cPlace,(char)(c+'0'));
        wynik.setCharAt(dPlace,(char)(d+'0'));
        if(wynik.toString().equals(pytania.get(ktore).get(0))){
            question_count.set(ktore,question_count.get(ktore)-1);
            Toast.makeText(this, "Poprawna odp, licznik"+question_count.get(ktore), Toast.LENGTH_SHORT).show();
        }
        else{
            question_count.set(ktore,question_count.get(ktore)+wrong);
            Toast.makeText(this, "Bledna odp, licznik"+question_count.get(ktore), Toast.LENGTH_SHORT).show();
        }
        int correct=pytania.get(ktore).get(0).indexOf("1");
        Log.i("tag",String.valueOf(correct));
        if(correct==0){
            if(odpA.getText().charAt(0)=='a'){
                odpA.setBackgroundColor(Color.GREEN);
            }
            else if(odpB.getText().charAt(0)=='a'){
                odpB.setBackgroundColor(Color.GREEN);
            }
            else if(odpC.getText().charAt(0)=='a'){
                odpC.setBackgroundColor(Color.GREEN);
            }
            else if(odpD.getText().charAt(0)=='a'){
                odpD.setBackgroundColor(Color.GREEN);
            }
        }
        else if(correct==1){
            if(odpA.getText().charAt(0)=='b'){
                odpA.setBackgroundColor(Color.GREEN);
            }
            else if(odpB.getText().charAt(0)=='b'){
                odpB.setBackgroundColor(Color.GREEN);
            }
            else if(odpC.getText().charAt(0)=='b'){
                odpC.setBackgroundColor(Color.GREEN);
            }
            else if(odpD.getText().charAt(0)=='b'){
                odpD.setBackgroundColor(Color.GREEN);
            }
        }
        else if(correct==2){
            if(odpA.getText().charAt(0)=='c'){
                odpA.setBackgroundColor(Color.GREEN);
            }
            else if(odpB.getText().charAt(0)=='c'){
                odpB.setBackgroundColor(Color.GREEN);
            }
            else if(odpC.getText().charAt(0)=='c'){
                odpC.setBackgroundColor(Color.GREEN);
            }
            else if(odpD.getText().charAt(0)=='c'){
                odpD.setBackgroundColor(Color.GREEN);
            }
        }
        else if(correct==3){
            if(odpA.getText().charAt(0)=='d'){
                odpA.setBackgroundColor(Color.GREEN);
            }
            else if(odpB.getText().charAt(0)=='d'){
                odpB.setBackgroundColor(Color.GREEN);
            }
            else if(odpC.getText().charAt(0)=='d'){
                odpC.setBackgroundColor(Color.GREEN);
            }
            else if(odpD.getText().charAt(0)=='d'){
                odpD.setBackgroundColor(Color.GREEN);
            }
        }
        odpAclick=false;
        odpBclick=false;
        odpCclick=false;
        odpDclick=false;


        ArrayList<Integer>  temp_sort=new ArrayList<>(); // sortowanie i sprawdzenie czy jest wartosc rozna od 0
        temp_sort.addAll(question_count);
        Collections.sort(temp_sort);
        Collections.reverse(temp_sort);
        Log.i("check",""+temp_sort.get(0));
        if(temp_sort.get(0)==0){
            Toast.makeText(this, "Gratulacje ukonczyles testownik srednia ilosc powtorzen:"+question_count.size()/questionNumber, Toast.LENGTH_SHORT).show();
            sprawdz.setVisibility(View.GONE);
            odpA.setVisibility(View.GONE);
            odpB.setVisibility(View.GONE);
            odpC.setVisibility(View.GONE);
            odpD.setVisibility(View.GONE);

            pytanie.setText("Gratulacje ukonczyles testownik srednia ilosc powtorzen:"+questionNumber/question_count.size());

        }
        else {
            sprawdz.setVisibility(View.GONE);
            dalej.setVisibility(View.VISIBLE);
        }
        Log.i("wynik",wynik.toString());
    }

    public void Dalejj(View view){
        sprawdz.setVisibility(View.VISIBLE);
        dalej.setVisibility(View.GONE);
        odpA.setBackgroundResource(android.R.drawable.btn_default);
        odpB.setBackgroundResource(android.R.drawable.btn_default);
        odpC.setBackgroundResource(android.R.drawable.btn_default);
        odpD.setBackgroundResource(android.R.drawable.btn_default);
        wylosuj();
    }
}
