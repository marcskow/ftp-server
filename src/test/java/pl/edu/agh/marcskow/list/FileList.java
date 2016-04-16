package pl.edu.agh.marcskow.list;


import sun.awt.image.ImageWatched;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class FileList {

    public static void main(String[] args) {
        File current = new File("/home/intenso/Downloads");

       // LinkedList<String> table = listFiles(current);
        //for (String s : table) {
         //   System.out.println(s);
        //}
    }
/*
    private static LinkedList<String> listFiles(File currentDirectory){
        File[] filesList = currentDirectory.listFiles();
        LinkedList<String> files = new LinkedList<>();

        for(File f : filesList){
            if(f.isDirectory())
                files.add(f.getName() + " DIR " + f.length());
            else if(f.isFile()){
                files.add(f.getName() + " FILE " + f.length());
            }
        }

        return files;
    }
*/
    //Rekurencyjnie
    private static void getAllFiles(File current){
        File[] filesList = current.listFiles();
        for(File f : filesList){
            if(f.isDirectory())
                getAllFiles(f);
            if(f.isFile()){
                System.out.println(f.getName());
            }
        }
    }
}
