package pgn.examenMarzo.utiles;

//package Ficheros;
 
//IMPORTACION DE LIBRERIAS
import java.awt.*; //libreria que contiene  los controles
import java.awt.event.*; //libreria que contiene los eventos de los controles
import java.io.*;
 
public class Ficheros extends Frame implements ActionListener
{
      //DECLARACIÓN DE VARIABLES Y OBJETOS
      TextField t1,t2,t3,t4;
      TextArea ta;
      Label l1,l2,l3;
      Button b1,b2,b3;
 
 
      //FUNCIÓN CONSTRUCTOR
      public Ficheros()
      {
            inicializar();
      }
      //FUNCIÓN INICIALIZAR
      public void inicializar()
      {
            this.setLayout(null);
            this.setTitle("Ficheros");
            l1= new Label("Nombre del Fichero");
            l1.setBounds(10,50,200,20);
            this.add(l1);
            t1= new TextField();
            t1.setBounds(10,80,200,20);
            t1.setText("Fichero.txt");
            t1.setVisible(true);
            this.add(t1);
            l2= new Label("Texto a guardar");
            l2.setBounds(10,110,200,20);
            this.add(l2);
            ta= new TextArea();
            ta.setBounds(10,130,200,100);
            ta.setVisible(true);
            this.add(ta);
            b1= new Button("Leer");
            b1.setBounds(110,240,100,20);
            this.add(b1);
            b2= new Button("Guardar");
            b2.setBounds(10,240,100,20);
            this.add(b2);
            l3= new Label("");
            l3.setBounds(10,270,200,20);
            this.add(l3);
 
            // Para poder recojer los eventos de los botones se los añadimos
            //y los redireccionamos a la misma clase Frame
            b1.addActionListener(this);
            b2.addActionListener(this);
            //añado la función adaptadora para cerrar desde la ventana
            this.addWindowListener(new Cerrar());
      }
 
      public void actionPerformed(ActionEvent ae)
      {
            if(ae.getSource()==b1)
            {
                  abrir();
            }
 
            if(ae.getSource()==b2)
            {
                  guardar();
            }
      }
      public void abrir()
      {
            FileReader fr;
 
 
            //Abrimos el fichero para escribir
            if(t1.equals(""))
            {
            }
            else
            {
                  try
                  {
                        fr= new FileReader(t1.getText());
                        l3.setText("");
                  }
 
                  catch(IOException io)
                  {
                        l3.setText("Error al abrir el fichero");
                        return;
                  }
 
                  //Leemos
                  char[] buffer=new char[256];
                  int longitud;
                  try
                  {
                        while((longitud = fr.read(buffer))!=-1)
                        {
                             String s = new String(buffer, 0,longitud);
                             ta.setText(ta.getText()+s);
                        }
                        l3.setText("Fichero leido");
                  }
 
                  catch(IOException io)
                  {
                        l3.setText("Error al leer");
                  }
 
                  //cerramos el fichero
                  try
                  {
                        fr.close();
                  }
                  catch(IOException io)
                  {
                        l3.setText("Error al cerrar el archivo");
                  }
            }
 
      }
      public void guardar()
      {
            FileWriter fw;
            try
            {
                  fw= new FileWriter(t1.getText());
            }
            catch(IOException io)
            {
                  l3.setText("Error al abrir el fichero");
                  return;
            }
 
            //Escribimos
            try
            {
                  fw.write(ta.getText());
                  l3.setText("Fichero guardado");
            }
 
            catch(IOException io)
            {
                  l3.setText("Error al escribir");
            }
 
            //cerramos el fichero
            try
            {
                  fw.close();
            }
 
            catch(IOException io)
            {
                  l3.setText("Error al cerrar el archivo");
            }             
      }
      //Función cerrar desde la ventana
      class Cerrar extends WindowAdapter
      {
            public void windowClosing(WindowEvent  we)
            {
                  System.exit(0);
            }
      }
 
      //FUNCIÓN PRINCIPAL DEL PROGRAMA
      public static void main(String[] args)
      {
            Ficheros f= new Ficheros();
            f.setSize(220,300);
            f.setVisible(true);
      }
}