package main.java.ningSL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortTest {
    public static void main(String[] args) {
          People p1 = new People(10,"nickn");
          People p2 = new People(12,"tom");
          People p3 = new People(14,"kyte");
          People p4 = new People(9,"jhon");
          ArrayList<People> al = new ArrayList<>();
          al.add(p1);
          al.add(p2);
          al.add(p3);
          al.add(p4);
        // new Comparator<People>()
        Collections.sort(al, (o1, o2) -> {
            if(o1.age == o2.age) return 0;
            else if(o1.age > o2.age) return 1;
            return -1;
        });
        System.out.println(p1.name.indexOf("n")) ;
          System.out.println(al.toString());

    }
/*    public static class logComparator implements Comparator<People> {
        @Override
        public int compare(People o1, People o2) {
            if(o1.age == o2.age) return 0;
            else if(o1.age > o2.age) return 1;
            return -1;
        }

    }*/
    public static class People{
        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public People(int age, String name) {
            this.age = age;
            this.name = name;
        }

        int age;

        @Override
        public String toString() {
            return "People{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        String name;


    }
}

