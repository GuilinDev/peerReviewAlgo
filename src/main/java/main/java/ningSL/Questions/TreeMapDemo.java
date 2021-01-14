package main.java.ningSL.Questions;

import java.util.*;

public class TreeMapDemo{

    public static void main(String args[]){
        HashMap<Integer,String> hm = new HashMap<>();

        TreeMap<Integer,String> tm=new TreeMap<Integer,String>();

        NavigableMap<Integer, String> nm1 = new TreeMap<Integer, String>();

        NavigableMap<Integer, String> nm2 = new TreeMap<Integer, String>();

        NavigableMap<Integer, String> nm3 = new TreeMap<Integer, String>();

        NavigableMap<Integer, String> nm4 = new TreeMap<Integer, String>();

        tm.put(98,"Core");

        tm.put(100,"I");

        tm.put(103,"Java");

        tm.put(101,"Love");

        nm1=tm.subMap(97, true, 104, true);

        nm2=tm.subMap(96, false, 103, false);

        nm3=tm.subMap(98, true, 103, false);

        nm4=tm.subMap(98, false,103, true);



        System.out.println("values of Navigable Map 1: "+nm1);

        System.out.println("values of Navigable Map 2: "+nm2);

        System.out.println("values of Navigable Map 3: "+nm3);

        System.out.println("values of Navigable Map 4: "+nm4);
    }
}
