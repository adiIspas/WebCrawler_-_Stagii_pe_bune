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
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import webcrawler.Interfaces.*;

/**
 * CrawlerSPB is a singleton specialized to parse website stagiipebune.ro.
 * @author Adrian ISPAS, 243
 */
public class CrawlerSPB implements ICrawler {
    
    private String URL;
    private ArrayList<Internship> internships;
    private final ArrayList<String> categories;
    private final ArrayList<String> technologies;
    private final ArrayList<String> cities;
    private int numberOfPages = 0;
    private String nameOfCrawler;
    private static CrawlerSPB instance = new CrawlerSPB();
    
    final private String BASE = "http://www.stagiipebune.ro/stagii.html";
    final private String PAGE = "&page_num=";
    final private String INTERN = "&page=stagii";
    final private String CATEGORY = "&category=";
    final private String DOMAIN = "&domain=4";
    
    private JTextArea textArea;
    private JProgressBar progress;
    
    private int numberInternships = 1;
    
    /**
     * Private constructor for use a singleton design pattern.
     */
    private CrawlerSPB(){
        internships = new ArrayList<>();
        categories = new ArrayList<>();
        technologies = new ArrayList<>();
        cities = new ArrayList<>();
    }
    private CrawlerSPB(CrawlerSPB other){
        internships = new ArrayList<>();
        categories = new ArrayList<>();
        technologies = new ArrayList<>();
        cities = new ArrayList<>();
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
    
    public void setTextArea(JTextArea textArea){
        this.textArea = textArea;
    }
    
    public void setProgressBar(JProgressBar progress){
        this.progress = progress;
    }
    
    public void resetNumber(){
        numberInternships = 1;
    }
    
    public void setNumberInternships(int numberInternships){
        this.numberInternships = numberInternships;
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
        if(internships.contains(internship) == false){
            internships.add(0,internship);
            
            textArea.setText(numberInternships + ") " + internship + "\n" + textArea.getText());
            numberInternships++;
        }
    }
    
    public boolean checkInternship(Internship internship){
         
        if(!cities.contains(internship.getCity()))
            return false;
        
        boolean contains = false;
        for(String technology : internship.getTechnologies())
            if(technologies.contains(technology)){
                contains = true;
                break;
            }
        
        return contains != false;
    }
    
    /**
     * Set the actual internships.
     * @param internships Internships result from deserialization.
     */
    public void setInternships(ArrayList<Internship> internships){
        this.internships = internships;
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
     * @param pageNumber Number of PAGE to parse.
     * @param categoryType Category for parse.
     * @throws java.io.IOException
     */
    public void setURLCategory(int pageNumber, String categoryType) throws IOException {
        this.URL =  BASE + PAGE + pageNumber + INTERN + CATEGORY + categoryType;
        
        if(pageNumber == 0)
            determinesNumberOfPages();
    }  
    
    /**
     * Set the URL for the internship without categories.
     * @param pageNumber Number of PAGE to parse.
     * @throws IOException 
     */
    public void setURLHome(int pageNumber) throws IOException {
        this.URL =  BASE + PAGE + pageNumber + INTERN + DOMAIN;
        
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
        numberOfPages = 0;
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
     * Add a CATEGORY in list of options.
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
        return new ArrayList<>(categories);
    }
    
    /**
     * Clear the list of categories.
     */
    public void clearCategories(){
        categories.clear();
    }
    
    public void clearCities(){
        cities.clear();
    }
    
    public void clearTechnologies(){
        technologies.clear();
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
        return new ArrayList<>(technologies);
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
     * @throws java.net.MalformedURLException
     */
    @Override
    public void parse() throws MalformedURLException, IOException{
        
        boolean isCitiesToParse;
        boolean isTechnologiesToParse;
        
        String currentCategory;
        
        isCitiesToParse = !cities.isEmpty();
        isTechnologiesToParse = !technologies.isEmpty();
                
        if(categories.isEmpty()){
            currentCategory = "home";
            parseHelper(isTechnologiesToParse, isCitiesToParse, currentCategory);
        }
        else{
            currentCategory = "multiple";
            parseHelper(isTechnologiesToParse, isCitiesToParse, currentCategory);
        }
    }
    
    /**
     * Helper for parse method. 
     * @param isTechnologiesToParse Is true if the user want to search by technologies.
     * @param isCitiesToParse Is true if the user want to search by cities.
     * @param currentCategory The current CATEGORY to parse.
     * @throws MalformedURLException
     * @throws IOException
     */
    public void parseHelper(boolean isTechnologiesToParse, boolean isCitiesToParse, String currentCategory) throws MalformedURLException, IOException{

        SwingWorker<Boolean, Internship> worker = new SwingWorker<Boolean, Internship>() {
            String category = "";
            @Override
            protected Boolean doInBackground() throws Exception {
                if(currentCategory.equals("home")){
                    URL siteURL;
                    
                    boolean isCategoriesToParse;         
                    boolean nameAdded = false;
                    boolean departmentAdded = false;
                    boolean cityAdded = false;
                    boolean periodAdded = false;
                    boolean seatsAdded = false;
                    boolean applicationsAdded = false; 
                    
                    String company = null;
                    String name = null;
                    String department = null;
                    String city = null;
                    String period = null;
                    String seats = null;
                    String applications = null;
                    String URLInternship = null;
                    String URLInternshipBase = "http://www.stagiipebune.ro";
                    String inputLine;

                    if(currentCategory.equals("home") == true){
                        isCategoriesToParse = false;
                        setURLHome(0);
                    }
                    else{
                        isCategoriesToParse = true;
                        setURLCategory(0,currentCategory);
                    }

                    for(int i = 0; i <= numberOfPages; i++){

                        siteURL = new URL(getURL()); 
                        Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "URL: {0}", getURL());
                        BufferedReader input = new BufferedReader(new InputStreamReader(siteURL.openStream()));

                        //Pattern companyInternshipPattern = Pattern.compile("<a href=.+? class='burgundtitles'>(.+?)</a>");
                        //Pattern nameInternshipPattern = Pattern.compile("<td><a href=.+?>(.+?)</a></td>");
                        //Pattern URLInternshipPattern = Pattern.compile("<td><a href='(.+?)'>.+?</a></td>");
                        //Pattern departmentInternshipPattern = Pattern.compile("<td>(.+?)</td>");
                        //Pattern cityInternshipPattern = Pattern.compile("<td>(.+?)</td>");
                        //Pattern periodInternshipPattern = Pattern.compile("<td>(.+?)</td>");
                        //Pattern seatsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
                        //Pattern applicationsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
                        //Pattern endInternshipPattern = Pattern.compile("<td align='right'></td>");
                        //Pattern endInternshipPattern = Pattern.compile("<a href=.+? onmouseover=.+? onmouseout=.+?>(.+?)</a>");   
                        
                        String result;
                        while ((inputLine = input.readLine()) != null){
                            if((result = parseCompany(inputLine)).equals("NOTFOUND") == false){
                               company = result;
                               continue;
                            }
                            
                            if((result = parseURL(inputLine)).equals("NOTFOUND") == false && nameAdded == false){
                                URLInternship = URLInternshipBase + result;
                            }
                            
                            if((result = parseName(inputLine)).equals("NOTFOUND") == false && nameAdded == false){
                                name = result;
                                nameAdded = true;
                                continue;
                            }
                            
                            if((result = parseDepartment(inputLine)).equals("NOTFOUND") == false && nameAdded == true && departmentAdded == false){
                                department = result;
                                departmentAdded = true;
                                continue;
                            }
                            
                            if((result = parseCity(inputLine)).equals("NOTFOUND") == false  && nameAdded == true && departmentAdded == true && cityAdded == false){
                                city = result;
                                cityAdded = true;
                                continue;
                            }
                            
                            if((result = parsePeriod(inputLine)).equals("NOTFOUND") == false && nameAdded == true
                                    && departmentAdded == true && cityAdded == true && periodAdded == false){
                                period = result;
                                periodAdded = true;
                                continue;
                            }
                            
                            if((result = parseSeats(inputLine)).equals("NOTFOUND") == false && nameAdded == true
                                    && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == false){
                                seats = result;
                                seatsAdded = true;
                                continue;
                            }
                            
                            if((result = parseApplications(inputLine)).equals("NOTFOUND") == false && nameAdded == true
                                    && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == true && applicationsAdded == false){
                                applications = result;
                                applicationsAdded = true;
                                continue;
                            }

//                            Matcher companyInternshipMatcher = companyInternshipPattern.matcher(inputLine);
//                            if (companyInternshipMatcher.find()){
//                                company = companyInternshipMatcher.group(1);
//                                continue;
//                            }

//                            Matcher URLInternshipMatcher = URLInternshipPattern.matcher(inputLine);
//                            if (URLInternshipMatcher.find() && nameAdded == false){
//                                URLInternship = URLInternshipMatcher.group(1);
//                                URLInternship = URLInternship.replace("amp;", "");
//                                URLInternship = URLInternshipBase + URLInternship;
//                            }

//                            Matcher nameInternshipMatcher = nameInternshipPattern.matcher(inputLine);
//                            if (nameInternshipMatcher.find() && nameAdded == false){
//                                name = nameInternshipMatcher.group(1);
//                                nameAdded = true;
//                                continue;
//                            }

//                            Matcher departmentInternshipMatcher = departmentInternshipPattern.matcher(inputLine);
//                            if (departmentInternshipMatcher.find() && nameAdded == true && departmentAdded == false){
//                                department = departmentInternshipMatcher.group(1);
//                                departmentAdded = true;
//                                continue;
//                            }

//                            Matcher cityInternshipMatcher = cityInternshipPattern.matcher(inputLine);
//                            if (cityInternshipMatcher.find() && nameAdded == true && departmentAdded == true && cityAdded == false){
//                                city = cityInternshipMatcher.group(1);
//                                cityAdded = true;
//                                continue;
//                            }

//                            Matcher periodInternshipMatcher = periodInternshipPattern.matcher(inputLine);
//                            if (periodInternshipMatcher.find() && nameAdded == true
//                                    && departmentAdded == true && cityAdded == true && periodAdded == false){
//                                period = periodInternshipMatcher.group(1);
//                                periodAdded = true;
//                                continue;
//                            }

//                            Matcher seatsInternshipMatcher = seatsInternshipPattern.matcher(inputLine);
//                            if (seatsInternshipMatcher.find() && nameAdded == true
//                                    && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == false){
//                                seats = seatsInternshipMatcher.group(1);
//                                seatsAdded = true;
//                                continue;
//                            }

//                            Matcher applicationsInternshipMatcher = applicationsInternshipPattern.matcher(inputLine);
//                            if (applicationsInternshipMatcher.find() && nameAdded == true
//                                    && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == true && applicationsAdded == false){
//                                applications = applicationsInternshipMatcher.group(1);
//                                applicationsAdded = true;
//                                continue;
//                            }

                            //Matcher endInternshipMatcher = endInternshipPattern.matcher(inputLine);
                            if (parseEnd(inputLine).equals("DONE") && nameAdded == true
                                    && departmentAdded == true && cityAdded == true
                                    && periodAdded == true && seatsAdded == true
                                    && applicationsAdded == true){
                                
                                Internship internship = initInternship(company, name, department, city, period, seats, applications);
//                                internship.setCompany(company);
//                                internship.setName(name);
//                                internship.setDepartment(department);
//                                internship.setCity(city);
//                                internship.setPeriod(period);
//
//                                // TO DO: "3-5" locuri si "2 " locuri.
//                                // Solutie: Un parseInt personalizat :)
//                                if(seats.contains("-") == true || seats.contains(" ") == true)
//                                    internship.setSeats(seats.charAt(seats.length() - 1));
//                                else
//                                    internship.setSeats(Integer.parseInt(seats));
//
//                                internship.setApplications(Integer.parseInt(applications));

                                parseTechnology(URLInternship, internship);
                                
                                // Add internship only if is in the selected cities.
//                                if(isCitiesToParse == false && isTechnologiesToParse == false){
//                                    addInternship(internship);
//                                    added = true;
//                                }
//                                else if(isCitiesToParse == true && isTechnologiesToParse == false && added == false)
//                                    for(String cityWanted : cities){
//                                        if(internship.getCity().equals(cityWanted)){
//                                            addInternship(internship);
//                                            added = true;
//                                            break;
//                                        }
//                                    }
//                                else if(isTechnologiesToParse == true && isCitiesToParse == false && added == false)
//                                    for(String technologyWanted : technologies){
//                                        if(internship.getTechnologies().contains(technologyWanted)){
//                                            addInternship(internship);
//                                            added = true;
//                                            break;
//                                        }
//                                    }
//                                else{
//
//                                    for(String cityWanted : cities){
//                                        if(internship.getCity().equals(cityWanted)){
//                                            passedOne = true;
//
//                                            for(String technologyWanted : technologies){
//                                                if(internship.getTechnologies().contains(technologyWanted)){
//                                                    passedTwo = true;
//                                                    break;
//                                                }
//                                            }
//                                        }
//                                    }
//
//                                    if(passedOne == true && passedTwo == true){
//                                        addInternship(internship);
//                                        added = true;
//                                        Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "CITY: {0}", internship.getCity());
//                                        Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "PASSED: {0}", passedOne + " " + passedTwo);
//                                        passedOne = passedTwo = false;
//                                    }
//                                }

                                if(checkInternship(internship, isCategoriesToParse, isCategoriesToParse) == true)
                                    addInternship(internship);


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
                        
                        int currentProgress;
                        if(numberOfPages != 0){
                            currentProgress = 100 / numberOfPages + 1;
                            progress.setValue(currentProgress * i);
                        }
                        else{
                            currentProgress = 100;
                            progress.setValue(currentProgress);
                        }
                   }
                }
                else{
                    for(String cat : categories){
                        category = cat;
                        URL siteURL;
                        boolean isCategoriesToParse;
                        boolean passedOne = false;
                        boolean passedTwo = false;
                        boolean added = false;

                        if(currentCategory.equals("home") == true){
                            isCategoriesToParse = false;
                            setURLHome(0);
                        }
                        else{
                            isCategoriesToParse = true;
                            //setURLCategory(0,currentCategory);
                            setURLCategory(0,category);
                        }

                        String company = null;
                        String name = null;
                        String department = null;
                        String city = null;
                        String period = null;
                        String seats = null;
                        String applications = null;
                        String URLInternship = null;
                        String URLInternshipBase = "http://www.stagiipebune.ro";

                        String inputLine;
                        boolean nameAdded = false;
                        boolean departmentAdded = false;
                        boolean cityAdded = false;
                        boolean periodAdded = false;
                        boolean seatsAdded = false;
                        boolean applicationsAdded = false; 

                        for(int i = 0; i <= numberOfPages; i++){

                            siteURL = new URL(getURL()); 
                            Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "URL: {0}", getURL());
                            BufferedReader input = new BufferedReader(new InputStreamReader(siteURL.openStream()));

                            Pattern companyInternshipPattern = Pattern.compile("<a href=.+? class='burgundtitles'>(.+?)</a>");
                            Pattern nameInternshipPattern = Pattern.compile("<td><a href=.+?>(.+?)</a></td>");
                            Pattern URLInternshipPattern = Pattern.compile("<td><a href='(.+?)'>.+?</a></td>");
                            Pattern departmentInternshipPattern = Pattern.compile("<td>(.+?)</td>");
                            Pattern cityInternshipPattern = Pattern.compile("<td>(.+?)</td>");
                            Pattern periodInternshipPattern = Pattern.compile("<td>(.+?)</td>");
                            Pattern seatsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
                            Pattern applicationsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
                            Pattern endInternshipPattern = Pattern.compile("<td align='right'></td>");
                            //Pattern endInternshipPattern = Pattern.compile("<a href=.+? onmouseover=.+? onmouseout=.+?>(.+?)</a>");

                            while ((inputLine = input.readLine()) != null){
                                Matcher companyInternshipMatcher = companyInternshipPattern.matcher(inputLine);
                                if (companyInternshipMatcher.find()){
                                    company = companyInternshipMatcher.group(1);
                                    continue;
                                }

                                Matcher URLInternshipMatcher = URLInternshipPattern.matcher(inputLine);
                                if (URLInternshipMatcher.find() && nameAdded == false){
                                    URLInternship = URLInternshipMatcher.group(1);
                                    URLInternship = URLInternship.replace("amp;", "");
                                    URLInternship = URLInternshipBase + URLInternship;
                                    Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "URL Internship: {0}", URLInternship);
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
                                    internship.setPeriod(period);

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

                                    // Parse internship for search technologies.
                                    //if(isTechnologiesToParse == true)
                                    parseTechnology(URLInternship, internship);



                                    // Add internship only if is in the selected cities.
                                    if(isCitiesToParse == false && isTechnologiesToParse == false){
                                        addInternship(internship);
                                        added = true;
                                    }
                                    else if(isCitiesToParse == true && isTechnologiesToParse == false && added == false)
                                        for(String cityWanted : cities){
                                            if(internship.getCity().equals(cityWanted)){
                                                addInternship(internship);
                                                added = true;
                                                break;
                                            }
                                        }
                                    else if(isTechnologiesToParse == true && isCitiesToParse == false && added == false)
                                        for(String technologyWanted : technologies){
                                            if(internship.getTechnologies().contains(technologyWanted)){
                                                addInternship(internship);
                                                added = true;
                                                break;
                                            }
                                        }
                                    else{

                                        for(String cityWanted : cities){
                                            if(internship.getCity().equals(cityWanted)){
                                                passedOne = true;

                                                for(String technologyWanted : technologies){
                                                    if(internship.getTechnologies().contains(technologyWanted)){
                                                        passedTwo = true;
                                                        break;
                                                    }
                                                }
                                            }
                                        }

                                        if(passedOne == true && passedTwo == true){
                                            addInternship(internship);
                                            added = true;
                                            Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "CITY: {0}", internship.getCity());
                                            Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "PASSED: {0}", passedOne + " " + passedTwo);
                                            passedOne = passedTwo = false;
                                        }
                                    }


                                    nameAdded = false;
                                    departmentAdded = false;
                                    cityAdded = false;
                                    periodAdded = false;
                                    seatsAdded = false;
                                    applicationsAdded = false;
                                    added = false;
                                }
                            }

                            if(isCategoriesToParse == false){
                                setURLHome(i + 1);
                            }
                            else{
                                setURLCategory(i + 1, category); 
                            }

                            int currentProgress = 0;
                            if(numberOfPages != 0){
                                currentProgress = 100 / numberOfPages + 1;
                                progress.setValue(currentProgress * i);
                            }
                            else{
                                currentProgress = 100;
                                progress.setValue(currentProgress);
                            }
                       }
                    }
                }
            return true;}
        };
        
        worker.execute();
    }
    
    private String parseCompany(final String inputLine){
        String result = "NOTFOUND";
        Pattern companyInternshipPattern = Pattern.compile("<a href=.+? class='burgundtitles'>(.+?)</a>");

	Matcher companyInternshipMatcher = companyInternshipPattern.matcher(inputLine);
        if (companyInternshipMatcher.find())
            result = companyInternshipMatcher.group(1);
        
        return result;      
    }
    
    private String parseURL(final String inputLine){
        String result = "NOTFOUND";
        Pattern URLInternshipPattern = Pattern.compile("<td><a href='(.+?)'>.+?</a></td>");
        
        Matcher URLInternshipMatcher = URLInternshipPattern.matcher(inputLine);
        if (URLInternshipMatcher.find()){
            result = URLInternshipMatcher.group(1);
            result = result.replace("amp;", "");
            return result;
        }
        
        return result;
    }
    
    private String parseName(final String inputLine){
        String result = "NOTFOUND";
        Pattern nameInternshipPattern = Pattern.compile("<td><a href=.+?>(.+?)</a></td>");
        
        Matcher nameInternshipMatcher = nameInternshipPattern.matcher(inputLine);
        if (nameInternshipMatcher.find())
            result = nameInternshipMatcher.group(1);
        
        return result;
    }
    
    private String parseDepartment(final String inputLine){
        String result = "NOTFOUND";
        Pattern departmentInternshipPattern = Pattern.compile("<td>(.+?)</td>");
        
        Matcher departmentInternshipMatcher = departmentInternshipPattern.matcher(inputLine);
        if (departmentInternshipMatcher.find())
            result = departmentInternshipMatcher.group(1);
        
        return result;
    }
    
    private String parseCity(final String inputLine){
        String result = "NOTFOUND";
        Pattern cityInternshipPattern = Pattern.compile("<td>(.+?)</td>");
        
        Matcher cityInternshipMatcher = cityInternshipPattern.matcher(inputLine);
        if (cityInternshipMatcher.find())
            result = cityInternshipMatcher.group(1);
        
        return result;
    }
    
    private String parsePeriod(final String inputLine){
        String result = "NOTFOUND";
        Pattern periodInternshipPattern = Pattern.compile("<td>(.+?)</td>");
        
        Matcher periodInternshipMatcher = periodInternshipPattern.matcher(inputLine);
        if (periodInternshipMatcher.find())
            result = periodInternshipMatcher.group(1);
        
        return result;
    }
    
    private String parseSeats(final String inputLine){
        String result = "NOTFOUND";
        Pattern seatsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
        
        Matcher seatsInternshipMatcher = seatsInternshipPattern.matcher(inputLine);
        if (seatsInternshipMatcher.find())
            result = seatsInternshipMatcher.group(1);
        
        return result;
    }
    
    private String parseApplications(final String inputLine){
        String result = "NOTFOUND";
        Pattern applicationsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
        
        Matcher applicationsInternshipMatcher = applicationsInternshipPattern.matcher(inputLine);
        if (applicationsInternshipMatcher.find())
            result = applicationsInternshipMatcher.group(1);
        
        return result;
    }
    
    private String parseEnd(final String inputLine){
        String result = "NOTFOUND";
        Pattern endInternshipPattern = Pattern.compile("<td align='right'></td>");
        
        Matcher endInternshipMatcher = endInternshipPattern.matcher(inputLine);
        if (endInternshipMatcher.find())
            result = "DONE";
        
        return result;
    }
    
    private Internship initInternship(String company, String name, String department, String city, String period, String seats, String applications){
        Internship internship = new Internship();
        
        internship.setCompany(company);
        internship.setName(name);
        internship.setDepartment(department);
        internship.setCity(city);
        internship.setPeriod(period);
        internship.setApplications(Integer.parseInt(applications));
        
        // TO DO: "3-5" locuri si "2 " locuri.
        // Solutie: Un parseInt personalizat :)
        if(seats.contains("-") == true || seats.contains(" ") == true)
            internship.setSeats(seats.charAt(seats.length() - 1));
        else
            internship.setSeats(Integer.parseInt(seats));

        return internship;
    }
    
    private boolean checkInternship(Internship internship, boolean isCitiesToParse, boolean isTechnologiesToParse){
        boolean passedOne = false;
        boolean passedTwo = false;
        boolean added = false;      
        
        if(isCitiesToParse == false && isTechnologiesToParse == false){
            //addInternship(internship);
            return true;
        }
        else if(isCitiesToParse == true && isTechnologiesToParse == false && added == false)
            for(String cityWanted : cities){
                if(internship.getCity().equals(cityWanted)){
                    //addInternship(internship);
                    return true;
                }
            }
        else if(isTechnologiesToParse == true && isCitiesToParse == false && added == false)
            for(String technologyWanted : technologies){
                if(internship.getTechnologies().contains(technologyWanted)){
                    //addInternship(internship);
                    return true;
                }
            }
        else{

            for(String cityWanted : cities){
                if(internship.getCity().equals(cityWanted)){
                    passedOne = true;

                    for(String technologyWanted : technologies){
                        if(internship.getTechnologies().contains(technologyWanted)){
                            passedTwo = true;
                            break;
                        }
                    }
                }
            }

            if(passedOne == true && passedTwo == true){
                //addInternship(internship);
                Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "CITY: {0}", internship.getCity());
                Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "PASSED: {0}", passedOne + " " + passedTwo);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Parse method for technologies.
     * @param URLInternship Internship PAGE for parse.
     * @param internship Internship object.
     * @throws MalformedURLException
     * @throws IOException 
     */
    public void parseTechnology(String URLInternship, Internship internship) throws MalformedURLException, IOException{
        String inputLine;
        URL siteURL = new URL(URLInternship);
        BufferedReader input = new BufferedReader(new InputStreamReader(siteURL.openStream()));

        while ((inputLine = input.readLine()) != null){
            if(parseCC(inputLine))
                internship.addTechnologies("C/C++");
            
            if(parseJava(inputLine))
                internship.addTechnologies("Java");
            
            if(parseRuby(inputLine)){
                internship.addTechnologies("Ruby");
            }
            
            if(parsePython(inputLine)){
                internship.addTechnologies("Python");
            }
            
            if(parsePerl(inputLine)){
                internship.addTechnologies("Perl");
            }
        }
    }
    
    private boolean parseJava(final String inputLine) throws IOException{
        String language;
        Pattern JavaPattern = Pattern.compile("\\bJava\\b");

        Matcher JavaMatcher = JavaPattern.matcher(inputLine);
        if (JavaMatcher.find()){
            language = JavaMatcher.group();
            Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "Language: {0}", language);
            return true;
        }
        
        return false;
    }
    
    private boolean parseCC(final String inputLine){
        String language;
        Pattern CCPlusPlusPattern = Pattern.compile("\\bC/C++\\b");
        
        Matcher CCPlusPlusMatcher = CCPlusPlusPattern.matcher(inputLine);
        if (CCPlusPlusMatcher.find()){
            language = CCPlusPlusMatcher.group();
            Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "Language: {0}", language);
            return true;
        }
            
        return false;
    }
    
    private boolean parseRuby(final String inputLine){
        String language;
        Pattern RubyPattern = Pattern.compile("\\bRuby\\b");
        
        Matcher RubyMatcher = RubyPattern.matcher(inputLine);
        if (RubyMatcher.find()){
            language = RubyMatcher.group();
            Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "Language: {0}", language);
            return true;
        }
        
        return false;
    }
    
    private boolean parsePython(final String inputLine){
        String language;
        Pattern PythonPattern = Pattern.compile("\\bPython\\b");
        
        Matcher PythonMatcher = PythonPattern.matcher(inputLine);
        if (PythonMatcher.find()){
            language = PythonMatcher.group();
            Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "Language: {0}", language);
            return true;
        }
        
        return false;
    }
    
    private boolean parsePerl(final String inputLine){
        String language;
        Pattern PerlPattern = Pattern.compile("\\bPerl\\b");
        
        Matcher PerlMatcher = PerlPattern.matcher(inputLine);
        if (PerlMatcher.find()){
            language = PerlMatcher.group();
            Logger.getLogger(CrawlerSPB.class.getName()).log(Level.INFO, "Language: {0}", language);
            return true;
        }
        
        return false;
    }
}