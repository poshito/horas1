/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.horas.UI;

import co.horas.entities.Horas;
import co.horas.entities.Usuario;
import co.horas.labadm.services.ServicesFacade;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;


public class UI extends JFrame implements ActionListener {
    private Usuario user;
    List<Usuario> usuarios= ServicesFacade.getInstance("config.properties").loadAllUsuario();
    JButton[] b = new JButton[6];
    JButton[] b1 = new JButton[6];
    JLabel[] u = new JLabel[6];
    JLabel[] h = new JLabel[6];
    
    
    public UI() {
        setLayout(new GridLayout(6, 4));
        for (int i = 0; i < 6; i++) {
            u[i] = new JLabel(usuarios.get(i).getNombre());
            h[i] = new JLabel(Integer.toString(usuarios.get(i).getHoras()));
        }
        
        for (int i = 0; i < 6; i++) {
            b[i]= new JButton("Up");
            b1[i] = new JButton("Down");
        }
        
        for (int i = 0; i < 6; i++) {
            add(u[i]);
            add(h[i]);
            add(b[i]);
            add(b1[i]);
        }
        
        for (int i = 0; i < 6; i++) {
            b[i].addActionListener(this);
            b1[i].addActionListener(this);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 6; i++) {
            if (e.getSource()== b[i]){
                String nombre=usuarios.get(i).getNombre();
                ServicesFacade.getInstance("config.properties").saveTiempo(nombre,1);
            }
        }
        for (int i = 0; i < 6; i++) {
            if (e.getSource()== b1[i]){
                String nombre=usuarios.get(i).getNombre();
                ServicesFacade.getInstance("config.properties").saveTiempo(nombre,-1);
            }
        }
    }
    
    
    public static void main(String[] ar) {
        UI formulario1=new UI();
        formulario1.setTitle("Horas");
        formulario1.setBounds(0,0,450,350);
        formulario1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formulario1.setVisible(true);
    }
    
}