/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.MainGUI;
import java.io.IOException;
import webcrawler.Internal.CrawlerSPB;


/**
 * Main class for run application.
 * @author Adrian ISPAS, 243
 */
public class Main {
    public static void main(String[] args) throws IOException{
        
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        crawler.setNameCrawler("Stagii pe bune");

        MainGUI graphics = new MainGUI();
        graphics.setVisible(true);
    }
}
