package com.vango.testownik;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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

import com.daasuu.ahp.AnimateHorizontalProgressBar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import es.dmoral.toasty.Toasty;

/*
class which supports all the tests name from previous idea to do activity for each test

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

boolean autosave; // settings check if on or off
boolean saved; // check if is saved
boolean game_ended=false;
AnimateHorizontalProgressBar progressBar; // score progress
    ArrayList<ArrayList<String>> pytania = new ArrayList<ArrayList<String>>();
    ArrayList<Integer> question_count= new ArrayList<>();
    int questionNumber=0;
    int goodAnswers=0;
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
                     if(!saved){
                        question_count.add(multiply);
                     }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
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
    }

pytanienr.setText("Pozostałe powtórzenia : "+question_count.get(ktore));

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





}
public void zapis(){
    SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
    autosave=spref.getBoolean("Autosave",false);
    if(!autosave||game_ended){
        if(urlAdress.equals("http://hackheroes.cba.pl/plik1.txt")){
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            pref.edit().remove("MiernictwoState").apply();
            pref.edit().remove("GoodAnswersMiernictwo").apply();
            pref.edit().remove("CountMiernictwo").apply();
        }
        else if(urlAdress.equals("http://hackheroes.cba.pl/combined.txt")){
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            pref.edit().remove("AirState").apply();
            pref.edit().remove("GoodAnswersAir").apply();
            pref.edit().remove("CountAir").apply();
        }
        else if(urlAdress.equals("http://hackheroes.cba.pl/pt.txt")){
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            pref.edit().remove("TelekomunaState").apply();
            pref.edit().remove("GoodAnswersTelekomuna").apply();
            pref.edit().remove("CountTelekomuna").apply();
        }
        else if(urlAdress.equals("http://hackheroes.cba.pl/pps.txt")){
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            pref.edit().remove("PpsState").apply();
            pref.edit().remove("GoodAnswersPPS").apply();
            pref.edit().remove("CountPPS").apply();
        }
        else if(urlAdress.equals("http://hackheroes.cba.pl/pps2.txt")){
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            pref.edit().remove("Pps2State").apply();
            pref.edit().remove("GoodAnswersPps2").apply();
            pref.edit().remove("CountPps2").apply();
        }
        else if(urlAdress.equals("http://hackheroes.cba.pl/izs.txt")){
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            pref.edit().remove("IzsState").apply();
            pref.edit().remove("GoodAnswersIzs").apply();
            pref.edit().remove("CountIzs").apply();
        }
    }
    if(autosave&&!game_ended) {
        if (urlAdress.equals("http://hackheroes.cba.pl/plik1.txt")) {
            saved = spref.contains("MiernictwoState");
            if (saved) {
                goodAnswers= spref.getInt("GoodAnswersMiernictwo",0);
                questionNumber =spref.getInt("CountMiernictwo",0);
                String get = spref.getString("MiernictwoState", "0s0s");
                question_count = ListForSharedPreferences.ListToString(get);

            }
        } else if (urlAdress.equals("http://hackheroes.cba.pl/combined.txt")) {
            saved = spref.contains("AirState");
            if (saved) {
                goodAnswers= spref.getInt("GoodAnswersAir",0);
                questionNumber =spref.getInt("CountAir",0);
                String get = spref.getString("AirState", "0s0s");
                question_count = ListForSharedPreferences.ListToString(get);

            }
        } else if (urlAdress.equals("http://hackheroes.cba.pl/pt.txt")) {
            saved = spref.contains("TelekomunaState");
            if (saved) {
                goodAnswers= spref.getInt("GoodAnswersTelekomuna",0);
                questionNumber =spref.getInt("CountTelekomuna",0);
                String get = spref.getString("TelekomunaState", "0s0s");
                question_count = ListForSharedPreferences.ListToString(get);
            }
        } else if (urlAdress.equals("http://hackheroes.cba.pl/pps.txt")) {
            saved = spref.contains("PpsState");
            if (saved) {
                goodAnswers= spref.getInt("GoodAnswersPps",0);
                questionNumber =spref.getInt("CountPps",0);
                String get = spref.getString("PpsState", "0s0s");
                question_count = ListForSharedPreferences.ListToString(get);

            }
        } else if (urlAdress.equals("http://hackheroes.cba.pl/pps2.txt")) {
            saved = spref.contains("Pps2State");
            if (saved) {
                goodAnswers= spref.getInt("GoodAnswersPps2",0);
                questionNumber =spref.getInt("CountPps",0);
                String get = spref.getString("Pps2State", "0s0s");
                question_count = ListForSharedPreferences.ListToString(get);
            }
        } else if (urlAdress.equals("http://hackheroes.cba.pl/izs.txt")) {
            saved = spref.contains("IzsState");
            if (saved) {
                goodAnswers= spref.getInt("GoodAnswersIzs",0);
                questionNumber =spref.getInt("CountIzs",0);
                String get = spref.getString("IzsState", "0s0s");
                question_count = ListForSharedPreferences.ListToString(get);
            }
        }
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miernictwo);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5C92FD"))); //kolor actionabar
        if (Build.VERSION.SDK_INT >= 21) {

            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.test)); //kolor paska ze statusem
        }
        Intent intent = getIntent();
        urlAdress=intent.getExtras().getString("adres"); //pobierz adres z którego ma wczytać pytania

        odpA=findViewById(R.id.buttonA);
        odpB=findViewById(R.id.buttonB);
        odpC=findViewById(R.id.buttonC);
        odpD=findViewById(R.id.buttonD);
        sprawdz=findViewById(R.id.Check);
        dalej = findViewById(R.id.Nexty);
        pytanie=findViewById(R.id.question);
        pytanienr=findViewById(R.id.licznik);
        progressBar = findViewById(R.id.animate_progress_bar);

        toolbarName();
        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
        String ss = spref.getString("Multiply", "4");//multiply get shared preference
        multiply=Integer.valueOf(ss);
        String s = spref.getString("Wrong", "2"); // wrong get shared preferences
        wrong=Integer.valueOf(s);
        zapis();
        DownloadTask task=new DownloadTask();
        try{
            task.execute().get(); //z get czeka na wykonanie
            wylosuj();

        }catch (Exception e){
            e.printStackTrace();
        }
        progressBar.setMax(questionNumber);
        progressBar.setProgress(goodAnswers);
    }
    boolean zapisz; //alertdialog check if save state
    public void close(){
        if(autosave) {
            SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
            if (urlAdress.equals("http://hackheroes.cba.pl/plik1.txt"))//miernictwo
            {
                spref.edit().putString("MiernictwoState", ListForSharedPreferences.ArrayListConvert(question_count)).apply();
            } else if (urlAdress.equals("http://hackheroes.cba.pl/combined.txt"))//air
            {

            }
        }
        else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Zapis");
            builder.setMessage("Czy chcesz zapisać postęp");
            builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    zapisz=true;
                }
            });
            builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    zapisz=false;

                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
            if(zapisz){
                if (urlAdress.equals("http://hackheroes.cba.pl/plik1.txt"))//miernictwo
                {
                    spref.edit().putString("MiernictwoState", ListForSharedPreferences.ArrayListConvert(question_count)).apply();
                } else if (urlAdress.equals("http://hackheroes.cba.pl/combined.txt"))//air
                {

                }
                Toast.makeText(Miernictwo.this, "Zapisano", Toast.LENGTH_SHORT).show();
            }
            else{
                spref.edit().remove("MiernictwoState").apply();

            }
        }
    }
    private void toolbarName(){
        ActionBar actionBar = getSupportActionBar();
        if (urlAdress.equals("http://hackheroes.cba.pl/plik1.txt"))//miernictwo
        {
         actionBar.setTitle("Miernictwo");
        }
        else if (urlAdress.equals("http://hackheroes.cba.pl/combined.txt"))//air
        {
            actionBar.setTitle("Podstawy Air");
        }
        else if (urlAdress.equals("http://hackheroes.cba.pl/pt.txt"))//telekomuna
        {
            actionBar.setTitle("Podstawy Telekomunikacji");
        }
        else if (urlAdress.equals("http://hackheroes.cba.pl/pps.txt"))//pps
        {
            actionBar.setTitle("PPS");
        }
        else if (urlAdress.equals("http://hackheroes.cba.pl/pps2.txt"))//pps2
        {
            actionBar.setTitle("PPS 2");
        }
        else if (urlAdress.equals("http://hackheroes.cba.pl/izs.txt"))//izs
        {
            actionBar.setTitle("IZS");
        }
        else if (urlAdress.equals("http://hackheroes.cba.pl/po.txt"))//po
        {
            actionBar.setTitle("Programowanie Obiektowe");
        }
        invalidateOptionsMenu();
    }
    public void closee(){
        if(autosave) {
            SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
            if (urlAdress.equals("http://hackheroes.cba.pl/plik1.txt"))//miernictwo
            {
                spref.edit().putString("MiernictwoState", ListForSharedPreferences.ArrayListConvert(question_count)).apply();
                spref.edit().putInt("GoodAnswersMiernictwo",goodAnswers).apply();
                spref.edit().putInt("CountMiernictwo",questionNumber).apply();
            }
            else if (urlAdress.equals("http://hackheroes.cba.pl/combined.txt"))//air
            {
                spref.edit().putString("AirState", ListForSharedPreferences.ArrayListConvert(question_count)).apply();
                spref.edit().putInt("GoodAnswersAir",goodAnswers).apply();
                spref.edit().putInt("CountAir",questionNumber).apply();
            }
            else if (urlAdress.equals("http://hackheroes.cba.pl/pt.txt"))//telekomuna
            {
                spref.edit().putString("TelekomunaState", ListForSharedPreferences.ArrayListConvert(question_count)).apply();
                spref.edit().putInt("GoodAnswersTelekomuna",goodAnswers).apply();
                spref.edit().putInt("CountTelekomuna",questionNumber).apply();
            }
            else if (urlAdress.equals("http://hackheroes.cba.pl/pps.txt"))//pps
            {
                spref.edit().putString("PpsState", ListForSharedPreferences.ArrayListConvert(question_count)).apply();
                spref.edit().putInt("GoodAnswersPps",goodAnswers).apply();
                spref.edit().putInt("CountPps",questionNumber).apply();
            }
            else if (urlAdress.equals("http://hackheroes.cba.pl/pps2.txt"))//pps2
            {
                spref.edit().putString("Pps2State", ListForSharedPreferences.ArrayListConvert(question_count)).apply();
                spref.edit().putInt("GoodAnswersPps2",goodAnswers).apply();
                spref.edit().putInt("CountPps2",questionNumber).apply();
            }
            else if (urlAdress.equals("http://hackheroes.cba.pl/izs.txt"))//izs
            {
                spref.edit().putString("IzsState", ListForSharedPreferences.ArrayListConvert(question_count)).apply();
                spref.edit().putInt("GoodAnswersIzs",goodAnswers).apply();
                spref.edit().putInt("CountIzs",questionNumber).apply();
            }
            Toasty.info(this,"Pomyślnie zapisano",Toasty.LENGTH_SHORT,true).show();
        }
    }
    @Override
    public void onBackPressed() {
            closee();
            super.onBackPressed();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        menu.getItem(1).setEnabled(false);
        menu.getItem(1).setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            closee();
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
                odpA.setBackgroundColor(getResources().getColor(R.color.buttonClicked));
                odpAclick=true;
            }
            else{
                odpA.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                odpAclick=false;
            }
        }

        else if(view.getId()==R.id.buttonB){
            if(!odpBclick) {
                odpB.setBackgroundColor(getResources().getColor(R.color.buttonClicked));
                odpBclick=true;
            }
            else{
                odpB.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                odpBclick=false;
            }
        }

        else if(view.getId()==R.id.buttonC){
            if(!odpCclick) {
                odpC.setBackgroundColor(getResources().getColor(R.color.buttonClicked));
                odpCclick=true;
            }
            else{
                odpC.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                odpCclick=false;
            }
        }

        else if(view.getId()==R.id.buttonD){
            if(!odpDclick) {
                odpD.setBackgroundColor(getResources().getColor(R.color.buttonClicked));
                odpDclick=true;
            }
            else{
                odpD.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                odpDclick=false;
            }
        }
    }


    public void checkAnswer(View view){
        Toasty.Config.getInstance().allowQueue(false).apply();
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

            Toasty.success(this,"Poprawna odp",Toast.LENGTH_SHORT,true).show();
            goodAnswers++;
            progressBar.setMax(questionNumber);
            progressBar.setProgress(goodAnswers);
        }
        else{
            question_count.set(ktore,question_count.get(ktore)+wrong);
            Toasty.error(this,"Bledna odp",Toast.LENGTH_SHORT).show();

            progressBar.setMax(questionNumber);
        }

        int correct = pytania.get(ktore).get(0).indexOf("1");
        while(correct >= 0) {
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
            correct = pytania.get(ktore).get(0).indexOf("1", correct+1);
        }
        odpAclick=false;
        odpBclick=false;
        odpCclick=false;
        odpDclick=false;


        ArrayList<Integer>  temp_sort=new ArrayList<>(); // sortowanie i sprawdzenie czy jest wartosc rozna od 0
        temp_sort.addAll(question_count);
        Collections.sort(temp_sort);
        Collections.reverse(temp_sort);
        if(temp_sort.get(0)==0){
            Toast.makeText(this, "Gratulacje ukonczyles testownik srednia ilosc powtorzen:"+question_count.size()/questionNumber, Toast.LENGTH_SHORT).show();
            sprawdz.setVisibility(View.GONE);
            odpA.setVisibility(View.GONE);
            odpB.setVisibility(View.GONE);
            odpC.setVisibility(View.GONE);
            odpD.setVisibility(View.GONE);

            pytanie.setText("Gratulacje ukonczyles testownik srednia ilosc powtorzen:"+questionNumber/question_count.size());
            game_ended=true;
        }
        else {
            sprawdz.setVisibility(View.GONE);
            dalej.setVisibility(View.VISIBLE);
        }
    }

    public void Dalejj(View view){
        sprawdz.setVisibility(View.VISIBLE);
        dalej.setVisibility(View.GONE);
        odpA.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        odpB.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        odpC.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        odpD.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        wylosuj();
    }
}
