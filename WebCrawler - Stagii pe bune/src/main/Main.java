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
        Internship intern = new Internship();
        intern.setCompany("Google");
        intern.setName("Java developer");
        intern.setDepartment("Product delivery");
        intern.setCity("Bucharest");
        intern.setSeats(10);
        intern.setApplications(20);
        intern.setReport(2);
        
        crawler.addInternship(intern);
        
        crawler.parse();
        
        int nr = 1;
        for(Internship i : crawler.getInternships()){
            System.out.println(nr + ") " + i);
            nr++;
        }    
    }
}
