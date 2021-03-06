/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler.Internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrian ISPAS, 243
 */
public class CrawlerSPBTest {
    
    @Test
    public void getInstance(){
        CrawlerSPB crawler1 = CrawlerSPB.getInstance();
        CrawlerSPB crawler2 = CrawlerSPB.getInstance();
        
        crawler1.setNameCrawler("stagiipebune.ro");
              
        assertTrue(crawler2.getNameCrawler().equals("stagiipebune.ro"));
    }
    
    @Test
    public void addInternships(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        JTextArea text = new JTextArea();
        crawler.setTextArea(text);
        
        Internship internship1 = new Internship();
        Internship internship2 = new Internship();
        Internship internship3 = new Internship();
        
        internship1.setName("Java developer");
        internship1.setCompany("Google");
        internship1.setCity("Bucharest");
        internship1.setDepartment("AI");
        crawler.addInternship(internship1);
        
        internship2.setName("C/C++ developer");
        internship2.setCompany("Google");
        internship2.setCity("Bucharest");
        internship2.setDepartment("AI");
        crawler.addInternship(internship2);
        
        internship3.setName("C/C++ developer");
        internship3.setCompany("Google");
        internship3.setCity("Bucharest");
        internship3.setDepartment("AI");
        crawler.addInternship(internship3);
        
        
        assertTrue(crawler.getInternships().contains(internship1));
        assertTrue(crawler.getInternships().contains(internship2));
        assertTrue(crawler.getInternships().size() == 2);
    }
    
    @Test
    public void getInternships(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        crawler.clearInternships();
        
        ArrayList<Internship> internships = new ArrayList<>();
        
        Internship internship1 = new Internship();
        Internship internship2 = new Internship();
        
        crawler.clearInternships();

        internship1.setName("Java developer");
        internship1.setCompany("Google");
        internship1.setCity("Bucharest");
        internship1.setDepartment("AI");
        internship1.setReport(0);
        internship1.setPeriod("10-10-2016");
        
        internship2.setName("C/C++ developer");
        internship2.setCompany("Google");
        internship2.setCity("Bucharest");
        internship2.setDepartment("AI");
        internship2.setReport(0);
        internship2.setPeriod("10-10-2016");
        
        crawler.addInternship(internship1);
        crawler.addInternship(internship2);
        
        internships.add(internship2);
        internships.add(internship1);

        assertTrue(crawler.getInternships().equals(internships));
    }
    
    @Test
    public void SetGetURL() throws IOException{
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        
        crawler.setURLCategory(0, "117");
        assertEquals(crawler.getURL(),"http://www.stagiipebune.ro/stagii.html&page_num=0&page=stagii&category=117");
    }
    
    @Test
    public void SetGetNameCrawler(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        
        crawler.setNameCrawler("Stagii pe bune");
        
        assertEquals(crawler.getNameCrawler(),"Stagii pe bune");
    }
    
    @Test 
    public void GetAddCategory(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        ArrayList<String> categories = new ArrayList<>();
        
        crawler.clearCategories();
        
        crawler.addCategory("112");
        categories.add("112");
        crawler.addCategory("113");
        categories.add("113");
        crawler.addCategory("114");
        categories.add("114");
        
        assertTrue(crawler.getCategories().equals(categories));

    }
    
    @Test 
    public void GetAddTechnology(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        ArrayList<String> technologies = new ArrayList<>();
        
        crawler.addTechnology("Java");
        technologies.add("Java");
        crawler.addTechnology("C/C++");
        technologies.add("C/C++");
        crawler.addTechnology("C#");
        technologies.add("C#");
       
        assertTrue(crawler.getTechnologies().equals(technologies));
    }
    
    @Test 
    public void GetAddCity(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        ArrayList<String> cities = new ArrayList<>();
        
        crawler.addCity("Bucharest");
        cities.add("Bucharest");
        crawler.addCity("Iasi");
        cities.add("Iasi");
        crawler.addCity("Cluj");
        cities.add("Cluj");
       
        assertTrue(crawler.getCities().equals(cities));
    }
    
    @Test
    public void determinateNumberOfPages() throws IOException{
       
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        
        assertEquals(crawler.determinesNumberOfPages(),0);
    }
    
    @Test
    public void parse() throws IOException{
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        JTextArea text = new JTextArea();
        crawler.setTextArea(text);
        crawler.clearInternships();
        
        crawler.addCategory("128");
        
        crawler.parse();

        ArrayList<Internship> internships = new ArrayList<>();

        Internship internship = new Internship();

        internship.setCompany("AQUASoft");
        internship.setName("Management &amp; Back-Office Specialist");
        internship.setDepartment("Management");
        internship.setCity("Bucuresti");
        internship.setSeats(2);
        internship.setApplications(4);
        internship.setReport(0);
        internship.setPeriod("01.04.16 - 31.10.16");
        internships.add(internship);
        //crawler.addInternship(internship);
        
        Logger.getLogger(CrawlerSPBTest.class.getName()).log(Level.INFO, "Stagiu: " + crawler.getInternships().get(0));

        assertTrue(crawler.getInternships().equals(internships));
        
        
        
    }
   
}
