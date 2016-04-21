/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialization_deserialization.Internal;

import java.io.File;
import java.util.HashSet;
import serialization_deserialization.Interfaces.ISDInternships;
import webcrawler.Internal.Internship;

/**
 * 
 * @author Adrian ISPAS, 243
 */
public class SDInternships implements ISDInternships {
    
    private static SDInternships instance;
    private File file;
    
    /***
     * Private constructor for use a singleton design pattern.
     */
    private SDInternships(){}
    private SDInternships(SDInternships other){}
    
    /***
     * Get instance of class or create an instance if not exist. 
     * @return The instance of SDInternships.
     */
    public static SDInternships getInstance() {
        if(instance == null)
            return new SDInternships();
        return instance;
    }
    
    /***
     * Get the file where will be serialization or deserialization the internships.
     * @return The file.
     */
    public File getFile() {
        return file;
    } 
    
    /***
     * Set the file where will be serialization or deserialization the internships.
     * @param file The file.
     */
    public void setFile(File file) {
        this.file = file;
    }
       
    /***
     * Serialization the internships in a file.
     * @param internships The array of internships.
     */
    @Override
    public void serialization(HashSet<Internship> internships) {
       
    }
    
    /***
     * Deserialization the internships from the file
     * @param internships The array of internships.
     */
    @Override
    public void deserialization(HashSet<Internship> internships) {
        
    }
}
