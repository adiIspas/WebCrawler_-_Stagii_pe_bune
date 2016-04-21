/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler.Internal;

import java.util.ArrayList;
import java.util.HashSet;
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
        HashSet<Internship> internships = new HashSet<>();
        
        
        Internship internship1 = new Internship();
        Internship internship2 = new Internship();

        
        internship1.setName("Java developer");
        internship1.setCompany("Google");
        internship1.setCity("Bucharest");
        internship1.setDepartment("AI");
        crawler.addInternship(internship1);
        internships.add(internship1);
        
        internship2.setName("C/C++ developer");
        internship2.setCompany("Google");
        internship2.setCity("Bucharest");
        internship2.setDepartment("AI");
        crawler.addInternship(internship2);
        internships.add(internship2);
        
        assertTrue(crawler.getInternships().equals(internships));
    }
    
    @Test
    public void SetGetURL(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        
        crawler.setURL("www.google.ro");
        
        assertEquals(crawler.getURL(),"www.google.ro");
    }
    
    @Test
    public void SetGetNameCrawler(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        
        crawler.setNameCrawler("Stagii pe bune");
        
        assertEquals(crawler.getNameCrawler(),"Stagii pe bune");
    }
    
    @Test
    public void SetGetNumberOfPages(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        int numberOfPages = 10;
        
        crawler.setNumberOfPages(numberOfPages);
        
        assertEquals(crawler.getNumberOfPages(),numberOfPages);
    }
    
    @Test 
    public void GetAddCategory(){
        CrawlerSPB crawler = CrawlerSPB.getInstance();
        ArrayList<String> categories = new ArrayList<>();
        
        crawler.addCategory("Cloud");
        categories.add("Cloud");
        crawler.addCategory("Mobile");
        categories.add("Mobile");
        crawler.addCategory("Network");
        categories.add("Network");
       
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
   
}
