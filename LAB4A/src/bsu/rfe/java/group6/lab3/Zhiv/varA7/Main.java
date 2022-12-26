package bsu.rfe.java.group6.lab3.Zhiv.varA7;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    private static double x(double t){
        return Math.pow(Math.sin(t),3);
    }

    private static double y(double t){
        return 2 * Math.cos(t);
    }
    private static void createData(File file) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));


        ArrayList<Double> l = new ArrayList<Double>();

        for (double t = 0; t <= 2 * Math.PI; t+=0.2){
            l.add(10*x(t));
            l.add(10*y(t));
        }


        for (Double v : l){
            out.writeDouble(v);
        }

        out.close();
    }

    private static void getData(File file) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(file));

        String s = in.readUTF();
        System.out.println(s);
        in.close();
    }

    private static void transform(File file) throws IOException{
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        String new_name = file.getName().split("\\.")[0] + "_new.bin";
        File new_file = new File(new_name);
        DataOutputStream out = new DataOutputStream(new FileOutputStream(new_file));

        int i = 0;
        double a;
        while(in.available() > 0){
            if (i++%100 == 0){
                out.writeDouble(in.readDouble());
                out.writeDouble(2*in.readDouble());
            }
            else{
                a = in.readDouble();
                a = in.readDouble();
            }
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("data_new.bin");
        transform(file);

        Plot plot = new Plot();
        plot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        plot.setVisible(true);
    }

}
