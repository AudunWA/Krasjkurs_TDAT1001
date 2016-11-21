package opengl;// opengl.Eksamen_2015_GL2.java  OpenGL2 revidert JHN 20151101
//
// Java classes:
//import java.awt.*;   // Color og Graphics
import javax.swing.*;  // JFrame og JPanel

/*
import java.awt.*;   // Color og Graphics
import javax.swing.*;  // JFrame og JPanel
import java.util.*;
import javax.media.opengl.*; // JOGL
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*;
*/

class Vindu extends JFrame{

    public Vindu(){
        setTitle("Pyramide_Eksamen H2015");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Oppgave sirkel = new Oppgave();
//			Sirkel_OPenGL2 sirkel = new Sirkel_OPenGL2();
        sirkel.setSize(1200, 1000);
        add(sirkel);
        pack();

    }
}

class Eksamen_2015_GL2 {
    public static void main(String[] args){
        Vindu Eksamen_2015_GL2 = new Vindu();
        Eksamen_2015_GL2.setVisible(true);

    }

}


