/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler.Internal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private int numberOfPages = 0;
    private String nameOfCrawler;
    private static CrawlerSPB instance = new CrawlerSPB();
    
    final private String base = "http://www.stagiipebune.ro/stagii.html";
    final private String page = "&page_num=";
    final private String intern = "&page=stagii";
    final private String category = "&category=";
    
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
        return new HashSet<>(internships);
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
     * @param pageNumber
     * @param categoryType
     */
    public void setURL(int pageNumber, int categoryType) {
        this.URL =  base + page + pageNumber + intern + category + categoryType;
    }  

    /**
     * Get the number of pages for the site.
     * @return Number of pages.
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Determines number of pages for site.
     * @return Number of pages.
     * @throws java.net.MalformedURLException
     */
    public int determinesNumberOfPages(int startPage, int categoryType) throws MalformedURLException, IOException{
        
        // Set start URL
        setURL(startPage, categoryType);
        
        URL siteURL = new URL(getURL());
        BufferedReader input = new BufferedReader(new InputStreamReader(siteURL.openStream()));
        
        Pattern numberOfPagesPattern = Pattern.compile("&nbsp; din (.+?) ");
        
        String inputLine;
        while ((inputLine = input.readLine()) != null){ 
            Matcher numberOfPagesMatcher = numberOfPagesPattern.matcher(inputLine);
            if (numberOfPagesMatcher.find()){
                String number = numberOfPagesMatcher.group(1);
                numberOfPages = Integer.parseInt(number);
                break;
            }
        }
        return numberOfPages;
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
    
    public void clearInternships(){
        internships.clear();
    }

    /**
     * Parse method for the web site stagiipebune.ro
     */
    @Override
    public void parse() throws MalformedURLException, IOException{
        
        URL siteURL;
        determinesNumberOfPages(0,114);
        
        internships.clear();
        
        String company = null;
        String name = null;
        String department = null;
        String city = null;
        String period = null;
        String seats = null;
        String applications = null;

        String inputLine = null;
        boolean nameAdded = false;
        boolean departmentAdded = false;
        boolean cityAdded = false;
        boolean periodAdded = false;
        boolean seatsAdded = false;
        boolean applicationsAdded = false; 
            
        for(int i = 0; i < numberOfPages; i++){
        
            determinesNumberOfPages(i, 115);
            siteURL = new URL(getURL());
            Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "URL: {0}", getURL());
            BufferedReader input = new BufferedReader(new InputStreamReader(siteURL.openStream()));
 
            Pattern companyInternshipPattern = Pattern.compile("<a href=.+? class='burgundtitles'>(.+?)</a>");
            Pattern nameInternshipPattern = Pattern.compile("<td><a href=.+?>(.+?)</a></td>");
            Pattern departmentInternshipPattern = Pattern.compile("<td>(.+?)</td>");
            Pattern cityInternshipPattern = Pattern.compile("<td>(.+?)</td>");
            Pattern periodInternshipPattern = Pattern.compile("<td>(.+?)</td>");
            Pattern seatsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
            Pattern applicationsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
            Pattern endInternshipPattern = Pattern.compile("<a href=.+? onmouseover=.+? onmouseout=.+?>(.+?)</a>");
            
            while ((inputLine = input.readLine()) != null){
                
                Matcher companyInternshipMatcher = companyInternshipPattern.matcher(inputLine);
                if (companyInternshipMatcher.find()){
                    company = companyInternshipMatcher.group(1);
                    //System.out.println("Company: " + company);
                    continue;
                }
                
                Matcher nameInternshipMatcher = nameInternshipPattern.matcher(inputLine);
                if (nameInternshipMatcher.find() && nameAdded == false){
                    name = nameInternshipMatcher.group(1);
                    nameAdded = true;
                    //System.out.println("Name: " + name);
                    continue;
                }
                
                Matcher departmentInternshipMatcher = departmentInternshipPattern.matcher(inputLine);
                if (departmentInternshipMatcher.find() && nameAdded == true && departmentAdded == false){
                    department = departmentInternshipMatcher.group(1);
                    departmentAdded = true;
                    //System.out.println("Department: " + department);
                    continue;
                }
                
                Matcher cityInternshipMatcher = cityInternshipPattern.matcher(inputLine);
                if (cityInternshipMatcher.find() && nameAdded == true && departmentAdded == true && cityAdded == false){
                    city = cityInternshipMatcher.group(1);
                    //System.out.println("City: " + city);
                    cityAdded = true;
                    continue;
                }
                
                Matcher periodInternshipMatcher = periodInternshipPattern.matcher(inputLine);
                if (periodInternshipMatcher.find() && nameAdded == true
                        && departmentAdded == true && cityAdded == true && periodAdded == false){
                    period = periodInternshipMatcher.group(1);
                    //System.out.println("Period: " + period);
                    periodAdded = true;
                    continue;
                }
                
                Matcher seatsInternshipMatcher = seatsInternshipPattern.matcher(inputLine);
                if (seatsInternshipMatcher.find() && nameAdded == true
                        && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == false){
                    seats = seatsInternshipMatcher.group(1);
                    //System.out.println("Seats: " + seats);
                    seatsAdded = true;
                    continue;
                }
                
                Matcher applicationsInternshipMatcher = applicationsInternshipPattern.matcher(inputLine);
                if (applicationsInternshipMatcher.find() && nameAdded == true
                        && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == true && applicationsAdded == false){
                    applications = applicationsInternshipMatcher.group(1);
                    //System.out.println("Applications: " + applications);
                    applicationsAdded = true;
                    continue;
                }
                
                Matcher endInternshipMatcher = endInternshipPattern.matcher(inputLine);
                if (endInternshipMatcher.find() && nameAdded == true
                        && departmentAdded == true && cityAdded == true
                        && periodAdded == true && seatsAdded == true
                        && applicationsAdded == true){
                    
                    Internship internship = new Internship();
                    
                    internship.setCompany(company);
                    internship.setName(name);
                    internship.setDepartment(department);
                    internship.setCity(city);
                    
                    // TO DO: "3-5" locuri si "2 " locuri.
                    // Solutie: Un parseInt personalizat :)
                    if(seats.contains("-") == true || seats.contains(" ") == true)
                        internship.setSeats(seats.charAt(seats.length() - 1));
                    else
                        internship.setSeats(Integer.parseInt(seats));
                    internship.setApplications(Integer.parseInt(applications));
                    
                    addInternship(internship);
                    
                    nameAdded = false;
                    departmentAdded = false;
                    cityAdded = false;
                    periodAdded = false;
                    seatsAdded = false;
                    applicationsAdded = false;
                    
                    //System.out.println();
                }
            }
       }
    }
}
