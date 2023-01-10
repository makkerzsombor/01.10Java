package hu.petrik.konyvtarasztali;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Statisztika {
    static List<Konyv> konyvek = new ArrayList<Konyv>();
    static KonyvDB db;

    public static void main(String[] args) {
        //if (Arrays.asList(args).contains("--stat")){
            try {
                db = new KonyvDB();
                feladatok();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        //}else{
        //    Application.main(args);
        //}

    }

    private static void feladatok() throws SQLException {
        List<Konyv> konyves = db.readKonyv();

        int darabSzam = 0;
        for (int i = 0; i < konyves.size(); i++) {
            if (konyves.get(i).getPage_count() > 500){
                darabSzam++;
            }
        }
        System.out.println("500 oldalnál hosszabb könyvek száma: " + darabSzam);

        //Faladat 2
        boolean van = false;
        for (int i = 0; i < konyves.size(); i++) {
            if (konyves.get(i).getPublish_year() < 1950){
                van = true;
            }
        }
        if (van){
            System.out.println("Van 1950 előtt írt könyv!");
        }else{
            System.out.println("Nincs 1950 előtt írt könyv!");
        }
        //Feladat 3
        int max = 0;
        int index = 0;
        for (int i = 0; i < konyves.size(); i++) {
            if (konyves.get(i).getPage_count() > max){
                max = konyves.get(i).getPage_count();
                index = konyves.get(i).getId();
            }
        }
        System.out.println("A leghosszabb könyv:");
        System.out.println(konyves.get(index));
        //Feladat 4

        //Feladat 5
        System.out.print("Adjon meg egy könyv címet: ");
        Scanner sc = new Scanner(System.in);
        String cim = sc.next();
        int szerzosIndex = 0;
        boolean vanszerzo = false;
        for (int i = 0; i < konyves.size(); i++) {
            if (konyves.get(i).getAuthor().equals(cim)){
                szerzosIndex = konyves.get(i).getId();
                vanszerzo = true;
            }
        }
        if (vanszerzo){
            System.out.println("Az megadott könyv szerzője: " + konyves.get(szerzosIndex));
        }else{
            System.out.println("Az adott könyvnek nincsen szerzője!");
        }

    }


}
