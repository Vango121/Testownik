package com.vango.testownik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vango on 25.03.2020.
 */

public class ListForSharedPreferences {
    public static String ArrayListConvert (List<Integer> lista) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            output.append(lista.get(i)+"s");
        }
        return output.toString();

    }
    public static ArrayList<Integer> ListToString(String string){
        ArrayList<Integer>lista = new ArrayList<Integer>();
        int lastindex=0;
        int index = string.indexOf("s");
        lista.add(Integer.valueOf(string.substring(lastindex,index)));
        while (index >= 0) {
            lastindex=index+1;
            index = string.indexOf("s", index + 1);
            if(index>=0) {
                lista.add(Integer.valueOf(string.substring(lastindex,index)));
            }
        }


        return lista;

    }
}
