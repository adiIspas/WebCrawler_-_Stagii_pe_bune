/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.MainGUI;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import serialization_deserialization.Internal.SDInternships;
import webcrawler.Internal.CrawlerSPB;
import webcrawler.Internal.Internship;


/**
 * The class for tests progress of implementation.
 * @author Adrian ISPAS, 243
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException{
        
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        SDInternships SD = SDInternships.getInstance();
        ArrayList<Internship> internships = new ArrayList<>();
        
        MainGUI graphics = new MainGUI();
        graphics.setVisible(true);
        
        crawler.setNameCrawler("Stagii pe bune");
        
        SD.setFile("internships.ysp");
       
        //internships = SD.deserialization();
        System.out.println("SIZE_1: " + internships.size());
        crawler.setInternships(internships);
        
        System.out.println("SIZE_2: " + crawler.getInternships().size());
        
        
        System.out.println("SIZE_3: " + crawler.getInternships().size());
        //SD.serialization(crawler.getInternships());
        
        // La afisare se va mai face o filtrare conform cerintelor
//        int nr = 1;
//        System.out.println();
//        for (Internship i : crawler.getInternships()) {
//            System.out.print(nr + ") " + i);
//            i.getTechnologies().stream().forEach((language) -> {
//                System.out.print(" " + language);
//            });
//            System.out.println();
//            nr++;
//        }
    }
}
