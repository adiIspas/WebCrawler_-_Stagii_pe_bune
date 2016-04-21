/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler.Internal;
import java.util.ArrayList;
import java.util.HashSet;
import webcrawler.Interfaces.*;

/**
 * CrawlerSPB is a singleton specialized to parse website stagiipebune.ro.
 * @author Adrian ISPAS, 243
 */
public class CrawlerSPB implements ICrawler {
    
    private String URL;
    private HashSet<Internship> internships;
    private ArrayList<String> categories;
    private ArrayList<String> technologies;
    private ArrayList<String> cities;
    private int numberOfPages;
    private String nameOfCrawler;
    private static CrawlerSPB instance = new CrawlerSPB();
    
    /**
     * Private constructor for use a singleton design pattern.
     */
    private CrawlerSPB(){
        internships = new HashSet<Internship>();
        categories = new ArrayList<String>();
        technologies = new ArrayList<String>();
        cities = new ArrayList<String>();
    }
    private CrawlerSPB(CrawlerSPB other){
        internships = new HashSet<Internship>();
        categories = new ArrayList<String>();
        technologies = new ArrayList<String>();
        cities = new ArrayList<String>();
    }
    
    /**
     * Get instance of class or create an instance if not exist. 
     * @return The instance of CrawlerSPB.
     */
    public static CrawlerSPB getInstance(){
         if(instance == null)
            return new CrawlerSPB();
        return instance;
    }
    
    /**
     * Set the name of the crawler.
     * @param nameOfCrawler Name of crawler.
     */
    public void setNameCrawler(String nameOfCrawler){
        this.nameOfCrawler = nameOfCrawler;
    }
    
    /**
     * Get the name of the crawler.
     * @return Name of the crawler.
     */
    public String getNameCrawler(){
        return this.nameOfCrawler;
    }  
    
    /**
     * Get the internships what be found.
     * @return The array with internships.
     */
    public HashSet<Internship> getInternships() {
        return internships;
    }

    /**
     * Add an internship in list.
     * @param internship Internship to add.
     */
    public void addInternship(Internship internship) {
        internships.add(internship);
    }

    /**
     * Get the URL for the internship.
     * @return URL of internship.
     */
    public String getURL() {
        return URL;
    }

    /**
     * Set the URL for the internship.
     * @param URL URL of the internship.
     */
    public void setURL(String URL) {
        this.URL = URL;
    }  

    /**
     * Get the number of pages for the site.
     * @return Number of pages.
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Set the number of pages for the site.
     * @param numberOfPages Number of pages.
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * Determines number of pages for site.
     * @return Number of pages.
     */
    public int determinesNumberOfPages(){
        return -1;
    }

    /**
     * Get the next page for site.
     * @return Next page.
     */
    public String nextPage(){
        return null;
    }

    /**
     * Add a category in list of options.
     * @param category Category to add.
     */
    public void addCategory(String category){
       categories.add(category);
    }
    
    /**
     * Get the categories selected for the search.
     * @return Categories for internships.
     */
    public ArrayList<String> getCategories(){
        return categories;
    }

    /**
     * Add a technology in list of options.
     * @param technology Technology to add.
     */
    public void addTechnology(String technology){
        technologies.add(technology);
    }
    
    /**
     * Get the technologies selected for the search.
     * @return Technologies for internships.
     */
    public ArrayList<String> getTechnologies(){
        return technologies;
    }

    /**
     * Add a city in list of options.
     * @param city City to add.
     */
    public void addCity(String city){
        cities.add(city);
    }
    
    /**
     * Get the cities selected for the search.
     * @return Cities for internships.
     */
    public ArrayList<String> getCities(){
        return cities;
    }

    /**
     * Parse method for the web site stagiipebune.ro
     */
    @Override
    public void parse() {

    }
}
