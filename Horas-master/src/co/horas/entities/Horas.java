/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.horas.entities;

import co.horas.UI.UI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

/**
 *
 * @author monitor
 */
public class Horas {
    public Horas(){
        
    }
    public int Inicio(){
        Calendar calendario = Calendar.getInstance();
        int tiempo=0;
        int hora=calendario.get(Calendar.HOUR_OF_DAY);
        int minutos=calendario.get(Calendar.MINUTE);
        System.out.println("minutos1 "+minutos);
        tiempo= hora*60+minutos;
        return tiempo;
    }
    
    public int fin(){
        Calendar calendario = Calendar.getInstance();
        int tiempo=0;
        int hora=calendario.get(Calendar.HOUR_OF_DAY);
        int minutos=calendario.get(Calendar.MINUTE);
        System.out.println("minutos2 "+minutos);
        tiempo= hora*60+minutos;
        return tiempo;
    }
    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) throws IOException {
        UI face = new UI();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int control=-2;
        int ini=0, fi=0;
        control= Integer.parseInt(br.readLine());
        if (control==0){
            ini=Inicio();
            System.out.println("son "+ini);
        }
        control= Integer.parseInt(br.readLine());
        fi=fin();
        System.out.println("con "+fi);
        System.out.println("total fueron en minutos "+(fi-ini));
    }
    */
    
}
