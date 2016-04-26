package testcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Pattern p = Pattern.compile("<option value=.+?>(.+?)</option>"); = for get a cities.
 * Pattern p = Pattern.compile("<a href=.+? class='burgundtitles'>(.+?)</a>"); = for get a company.
 * Pattern p = Pattern.compile("<td><a href=.+?>(.+?)</a></td>"); = get name of internship.
 * @author Adrian ISPAS, 243
 */

public class TestCrawler {

    public static void main(String[] args) throws MalformedURLException, IOException {
        String base = "http://www.stagiipebune.ro/stagii.html";
        String page = "&page_num=";
        String intern = "&page=stagii";
        String category = "&category=";
        String pageNumber = "0";
        String categoryType = "128";
        
        String url = base + page + pageNumber + intern + category + categoryType;
        URL siteURL = new URL(url);
        //URL siteURL = new URL("http://www.stagiipebune.ro/stagii.html&page_num=0&page=stagii&category=114");
        BufferedReader input = new BufferedReader(new InputStreamReader(siteURL.openStream()));

        String inputLine;
        boolean nameAdded = false;
        boolean departmentAdded = false;
        boolean cityAdded = false;
        boolean periodAdded = false;
        boolean seatsAdded = false;
        boolean applicationsAdded = false;
        
        int numberOfPages = -1;

        Pattern companyInternshipPattern = Pattern.compile("<a href=.+? class='burgundtitles'>(.+?)</a>");
        Pattern nameInternshipPattern = Pattern.compile("<td><a href=.+?>(.+?)</a></td>");
        Pattern departmentInternshipPattern = Pattern.compile("<td>(.+?)</td>");
        Pattern cityInternshipPattern = Pattern.compile("<td>(.+?)</td>");
        Pattern periodInternshipPattern = Pattern.compile("<td>(.+?)</td>");
        Pattern seatsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
        Pattern applicationsInternshipPattern = Pattern.compile("<td align=.+?>(.+?)</td>");
        Pattern endInternshipPattern = Pattern.compile("<a href=.+? onmouseover=.+? onmouseout=.+?>(.+?)</a>");
        Pattern numberOfPagesPattern = Pattern.compile("&nbsp; din (.+?) ");
        
        while ((inputLine = input.readLine()) != null){ 
            Matcher numberOfPagesMatcher = numberOfPagesPattern.matcher(inputLine);
            if (numberOfPagesMatcher.find()){
                String number = numberOfPagesMatcher.group(1);
                numberOfPages = Integer.parseInt(number);
                System.out.println("<-- Number: " + (numberOfPages - 1));
                continue;
            }
            
            Matcher companyInternshipMatcher = companyInternshipPattern.matcher(inputLine);
            if (companyInternshipMatcher.find()){
                String company = companyInternshipMatcher.group(1);
                System.out.println("Company: " + company);
                continue;
            }
            
            Matcher nameInternshipMatcher = nameInternshipPattern.matcher(inputLine);
            if (nameInternshipMatcher.find() && nameAdded == false){
                String name = nameInternshipMatcher.group(1);
                nameAdded = true;
                System.out.println("Name: " + name);
                continue;
            }
            
            Matcher departmentInternshipMatcher = departmentInternshipPattern.matcher(inputLine);
            if (departmentInternshipMatcher.find() && nameAdded == true && departmentAdded == false){
                String department = departmentInternshipMatcher.group(1);
                departmentAdded = true;
                System.out.println("Department: " + department);
                continue;
            }
            
            Matcher cityInternshipMatcher = cityInternshipPattern.matcher(inputLine);
            if (cityInternshipMatcher.find() && nameAdded == true && departmentAdded == true && cityAdded == false){
                String city = cityInternshipMatcher.group(1);
                System.out.println("City: " + city);
                cityAdded = true;
                continue;
            }
            
            Matcher periodInternshipMatcher = periodInternshipPattern.matcher(inputLine);
            if (periodInternshipMatcher.find() && nameAdded == true 
                    && departmentAdded == true && cityAdded == true && periodAdded == false){
                String period = periodInternshipMatcher.group(1);
                System.out.println("Period: " + period);
                periodAdded = true;
                continue;
            }
            
            Matcher seatsInternshipMatcher = seatsInternshipPattern.matcher(inputLine);
            if (seatsInternshipMatcher.find() && nameAdded == true 
                    && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == false){
                String seats = seatsInternshipMatcher.group(1);
                System.out.println("Seats: " + seats);
                seatsAdded = true;
                continue;
            }
            
            Matcher applicationsInternshipMatcher = applicationsInternshipPattern.matcher(inputLine);
            if (applicationsInternshipMatcher.find() && nameAdded == true 
                    && departmentAdded == true && cityAdded == true && periodAdded == true && seatsAdded == true && applicationsAdded == false){
                String applications = applicationsInternshipMatcher.group(1);
                System.out.println("Applications: " + applications);
                applicationsAdded = true;
                continue;
            }
            
            Matcher endInternshipMatcher = endInternshipPattern.matcher(inputLine);
            if (endInternshipMatcher.find() && nameAdded == true 
                    && departmentAdded == true && cityAdded == true 
                    && periodAdded == true && seatsAdded == true 
                    && applicationsAdded == true){

                nameAdded = false;
                departmentAdded = false;
                cityAdded = false;
                periodAdded = false;
                seatsAdded = false;
                applicationsAdded = false;
                
                System.out.println();
            }
        }
        input.close();
    }
}
