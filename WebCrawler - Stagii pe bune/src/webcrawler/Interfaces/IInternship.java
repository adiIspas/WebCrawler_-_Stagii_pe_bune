/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler.Interfaces;

/**
 * Interface which modeling the internship object.
 * @author Adrian ISPAS, 243
 */
public interface IInternship {
    /***
     * Set the company for the internship.
     * @param company Company of the internship.
     */
    void setCompany(String company);
    
    /***
     * Get the company for the internship.
     * @return Company of the internship.
     */
    String getCompany();
    
    /***
     * Set name for the internship.
     * @param name Name of the internship.
     */
    void setName(String name);
    
    /***
     * Get name for the internship.
     * @return Name of the internship.
     */
    String getName();
    
    /***
     * Set the city for the internship.
     * @param city City of the internship.
     */
    void setCity(String city);
    
    /***
     * Get the city for the internship.
     * @return City of the internship.
     */
    String getCity();
}
