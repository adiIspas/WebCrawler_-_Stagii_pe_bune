/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler.Internal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import webcrawler.Interfaces.*;

/**
 * Modeling the internship object for crawler.
 * @author Adrian Ispas, 243
 */
public class Internship implements IInternship, Serializable{
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
    private ArrayList<String> technologies = new ArrayList<>();

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
    
    public ArrayList<String> getTechnologies(){
        return technologies;
    }

    public void addTechnologies(String technology){
        if(technologies.contains(technology) == false)
            technologies.add(technology);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.company);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.department);
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + Objects.hashCode(this.period);
        hash = 97 * hash + this.seats;
        hash = 97 * hash + this.applications;
        hash = 97 * hash + this.report;
        hash = 97 * hash + (this.seen ? 1 : 0);
        hash = 97 * hash + (this.favorite ? 1 : 0);
        hash = 97 * hash + (this.applied ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Internship other = (Internship) obj;
        if (this.seats != other.seats) {
            return false;
        }
        if (this.applications != other.applications) {
            return false;
        }
        if (this.report != other.report) {
            return false;
        }
        if (this.seen != other.seen) {
            return false;
        }
        if (this.favorite != other.favorite) {
            return false;
        }
        if (this.applied != other.applied) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.period, other.period)) {
            return false;
        }
        return true;
    }

    /*@Override
    public boolean equals(Object obj){
        if (obj instanceof Internship){
            Internship stagiu = (Internship) obj;
            
            if((stagiu.company.equals(((Internship) obj).company)) 
                && (stagiu.name.equals(((Internship) obj).name))
                && (stagiu.department.equals(((Internship) obj).department))
                && (stagiu.city.equals(((Internship) obj).city))
                && (stagiu.seats == ((Internship) obj).seats)
                && (stagiu.applications == ((Internship) obj).applications))
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
    }*/

    @Override
    public String toString() {
        return this.company + " | " + this.name + " | " 
                + this.department + " | " + this.city + " | "
                + this.seats + " | " + this.applications + " | "
                + this.report + " | " + this.period;        
    } 
}
