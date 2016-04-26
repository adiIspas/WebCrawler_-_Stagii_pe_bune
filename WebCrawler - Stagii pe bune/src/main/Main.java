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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        crawler.parse();
        
        int nr = 1;
        for(Internship i : crawler.getInternships()){
            System.out.println(nr + ") " + i);
            nr++;
        }    
    }
}
