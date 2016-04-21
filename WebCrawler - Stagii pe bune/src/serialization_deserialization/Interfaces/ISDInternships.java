/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialization_deserialization.Interfaces;

import java.util.HashSet;
import webcrawler.Internal.Internship;

/**
 * Interface which modeling the serialization and deserialization process.
 * @author Adrian ISPAS, 243
 */
public interface ISDInternships {

    void serialization(HashSet<Internship> internships);
    void deserialization(HashSet<Internship> internships);
    
    
}
