/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import webcrawler.Internal.CrawlerSPB;
import webcrawler.Internal.Internship;


/**
 * The class for tests progress of implementation.
 * @author Adrian ISPAS, 243
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException{
        
        CrawlerSPB crawler = CrawlerSPB.getInstance();

        crawler.setNameCrawler("Stagii pe bune");
        
        crawler.addCity("Iasi");
        crawler.addCity("Bucuresti");
        //crawler.addCity("Bacau");
        crawler.addCategory("123");
        crawler.addCategory("122");
        //crawler.addCategory("116");
        crawler.addTechnology("Python");
        //crawler.addTechnology("Perl");
        crawler.addTechnology("Java");
        crawler.parse();

        int nr = 1;
        System.out.println();
        for(Internship i : crawler.getInternships()){
            System.out.print(nr + ")" + i);
            for(String language : i.getTechnologies())
                System.out.print(" " + language);
            System.out.println();
            nr++;
        }
    }
}
