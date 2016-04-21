/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler.Internal;
import java.util.Objects;
import webcrawler.Interfaces.*;

/**
 * Modeling the internship object for crawler.
 * @author Adrian Ispas, 243
 */
public class Internship implements IInternship {
    private String company = "";
    private String name = "";
    private String department = "";
    private String city = "";
    private String period = "";
    private int seats = 0;
    private int applications = 0;
    private int report = 0;
    private boolean seen = false;
    private boolean favorite = false;
    private boolean applied = false;

    /**
     * Get the company for the internship.
     * @return Company of the internship.
     */
    @Override
    public String getCompany(){
        return company;
    }

    /**
     * Set the company for the internship.
     * @param company Company of the internship.
     */
    @Override
    public void setCompany(String company){
        this.company = company;
    }

    /**
     * Get the name of the internship.
     * @return Name of the internship.
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * Set the name for the internship.
     * @param name Name of the internship.
     */
    @Override
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get the department for the internship.
     * @return Department of the internship.
     */
    public String getDepartment(){
        return department;
    }

    /**
     * Set the department for the internship.
     * @param department Department for the internship.
     */
    public void setDepartment(String department){
        this.department = department;
    }

    /**
     * Get the city for the internship.
     * @return City of the internship.
     */
    @Override
    public String getCity(){
        return city;
    }

    /**
     * Set the city for the internship.
     * @param city City of the internship.
     */
    @Override
    public void setCity(String city){
        this.city = city;
    }

    /**
     * Get the period of the internship.
     * @return Period of the internship.
     */
    public String getPeriod(){
        return period;
    }

    /**
     * Set the period for the internship.
     * @param period The period of the internship.
     */
    public void setPeriod(String period){
        this.period = period;
    }

    /**
     * Get the seats for the internship.
     * @return Seats of the internship.
     */
    public int getSeats(){
        return seats;
    }

    /**
     * Set the seats for the internship.
     * @param seats Seats of the internship.
     */
    public void setSeats(int seats){
        this.seats = seats;
    }

    /**
     * Get the applications number for the internship.
     * @return Applications number.
     */
    public int getApplications(){
        return applications;
    }

    /**
     * Set the applications number for the internship.
     * @param applications Applications number.
     */
    public void setApplications(int applications){
        this.applications = applications;
    }

    /**
     * Get the report between applications number and the seats number for the internship.
     * @return report of the internship.
     */
    public int getReport(){
        return report;
    }

    /**
     * Set the report between applications number and the seats number for the internship.
     * @param report Report of the internship.
     */
    public void setReport(int report){
        this.report = report;
    }

    /**
     * Check if the internship is seen.
     * @return Is or not seen.
     */
    public boolean isSeen(){
        return seen;
    }

    /**
     * Set the value of the seen internship.
     * @param seen Is or not seen.
     */
    public void setSeen(boolean seen){
        this.seen = seen;
    }

    /**
     * Check if the internship is favorite.
     * @return Is or not favorite.
     */
    public boolean isFavorite(){
        return favorite;
    }

    /**
     * Set the value of the favorite internship.
     * @param favorite Is or not favorite.
     */
    public void setFavorite(boolean favorite){
        this.favorite = favorite;
    }

    /**
     * Check if the user applied at internship.
     * @return Applied or no.
     */
    public boolean isApplied(){
        return applied;
    }

    /**
     * Set if the user applied at internship.
     * @param applied Applied or no.
     */
    public void setApplied(boolean applied){
        this.applied = applied;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Internship){
            Internship stagiu = (Internship) obj;
            
            if((stagiu.company.equals(((Internship) obj).company)) 
                && (stagiu.name.equals(((Internship) obj).name))
                && (stagiu.department.equals(((Internship) obj).department))
                && (stagiu.city.equals(((Internship) obj).city)))
                return true;
        }
        return false;
  }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.company);
        hash = 83 * hash + Objects.hashCode(this.department);
        hash = 83 * hash + Objects.hashCode(this.city);
        return hash;
    }
}
