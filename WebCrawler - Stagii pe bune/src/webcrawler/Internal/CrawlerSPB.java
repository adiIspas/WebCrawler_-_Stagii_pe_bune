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
    private ArrayList<Internship> internships;
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
    final private String domain = "&domain=4";
    
    /**
     * Private constructor for use a singleton design pattern.
     */
    private CrawlerSPB(){
        internships = new ArrayList<Internship>();
        categories = new ArrayList<String>();
        technologies = new ArrayList<String>();
        cities = new ArrayList<String>();
    }
    private CrawlerSPB(CrawlerSPB other){
        internships = new ArrayList<Internship>();
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
    public ArrayList<Internship> getInternships() {
        return new ArrayList<>(internships);
    }

    /**
     * Add an internship in list.
     * @param internship Internship to add.
     */
    public void addInternship(Internship internship) {
        if(internships.contains(internship) == false)
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
     * Set the URL for the internship with categories.
     * @param pageNumber Number of page to parse.
     * @param categoryType Category for parse.
     * @throws java.io.IOException
     */
    public void setURLCategory(int pageNumber, String categoryType) throws IOException {
        this.URL =  base + page + pageNumber + intern + category + categoryType;
        
        if(pageNumber == 0)
            determinesNumberOfPages();
    }  
    
    /**
     * Set the URL for the internship without categories.
     * @param pageNumber Number of page to parse.
     * @throws IOException 
     */
    public void setURLHome(int pageNumber) throws IOException {
        this.URL =  base + page + pageNumber + intern + domain;
        
        if(pageNumber == 0)
            determinesNumberOfPages();
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
    public int determinesNumberOfPages() throws MalformedURLException, IOException{
        
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
    
    /**
     * Clear the list of internships.
     */
    public void clearInternships(){
        internships.clear();
    }

    /**
     * Parse method for the web site stagiipebune.ro
     */
    @Override
    public void parse() throws MalformedURLException, IOException{
        
        boolean isCategoryParse = false;
        boolean citiesToParse = false;
        
        String currentCategory;
        
        internships.clear();
        
        if(cities.isEmpty()){
            citiesToParse = false;
        }
        else{
            citiesToParse = true;
        }
                
        if(categories.isEmpty()){
            currentCategory = "home";
            parseHelper(citiesToParse, currentCategory);
        }

    }
    
    /**
     *
     * @param isCitiesToParse
     * @param currentCategory
     * @throws MalformedURLException
     * @throws IOException
     */
    public void parseHelper(boolean isCitiesToParse, String currentCategory) throws MalformedURLException, IOException{
        
        URL siteURL;
        boolean isCategoriesToParse;
        
        if(currentCategory.equals("home") == true){
            isCategoriesToParse = false;
            setURLHome(0);
        }
        else{
            isCategoriesToParse = true;
            setURLCategory(0,currentCategory);
        }

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
                    continue;
                }

                Matcher nameInternshipMatcher = nameInternshipPattern.matcher(inputLine);
                if (nameInternshipMatcher.find() && nameAdded == false){
                    name = nameInternshipMatcher.group(1);
                    nameAdded = true;
                    continue;
                }

                Matcher departmentInternshipMatcher = departmentInternshipPattern.matcher(inputLine);
                if (departmentInternshipMatcher.find() && nameAdded == true && departmentAdded == false){
                    department = departmentInternshipMatcher.group(1);
                    departmentAdded = true;
                    continue;
                }

                Matcher cityInternshipMatcher = cityInternshipPattern.matcher(inputLine);
                if (cityInternshipMatcher.find() && nameAdded == true && departmentAdded == true && cityAdded == false){
                    city = cityInternshipMatcher.group(1);
                    cityAdded = true;
                    continue;
                }

                Matcher periodInternshipMatcher = periodInternshipPattern.matcher(inputLine);
                if (periodInternshipMatcher.find() && nameAdded == true
                        && departmentAdded == true && cityAdded == true && periodAdded == false){
                    period = periodInternshipMatcher.group(1);
                    periodAdded = true;
                    continue;
                }

                Matcher seatsInternshipMatcher = seatsInternshipPattern.matcher(inputLine);
                if (seatsInternshipMatcher.find() && nameAdded == true
                        && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == false){
                    seats = seatsInternshipMatcher.group(1);
                    seatsAdded = true;
                    continue;
                }

                Matcher applicationsInternshipMatcher = applicationsInternshipPattern.matcher(inputLine);
                if (applicationsInternshipMatcher.find() && nameAdded == true
                        && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == true && applicationsAdded == false){
                    applications = applicationsInternshipMatcher.group(1);
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

                    //Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "Company: {0}", company);
                    //Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "Name: {0}", name);
                    //Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "Department: {0}", department);
                    //Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "City: {0}", city);

                    // Add internship only if is in the selected cities.
                    if(isCitiesToParse != true){
                        addInternship(internship);
                    }
                    else{
                        for(String cityWanted : cities){
                            if(internship.getCity().equals(cityWanted)){
                                addInternship(internship);
                                break;
                            }
                        }
                    }

                    nameAdded = false;
                    departmentAdded = false;
                    cityAdded = false;
                    periodAdded = false;
                    seatsAdded = false;
                    applicationsAdded = false;
                }
            }

            if(isCategoriesToParse == false){
                setURLHome(i + 1);
            }
            else{
                setURLCategory(i + 1, currentCategory); 
            }
       }
    }
}
