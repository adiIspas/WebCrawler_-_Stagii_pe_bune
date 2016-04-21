/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler.Internal;

import org.junit.Test;
import static org.junit.Assert.*;
import webcrawler.Internal.Internship;

/**
 *
 * @author Adrian ISPAS, 243 
 */
public class InternshipTest {
    
    @Test
    public void SetGetCompanyTest(){
        Internship internship = new Internship();
        
        internship.setCompany("Google");
        
        assertTrue(internship.getCompany().equals("Google"));
    }
    
    @Test
    public void SetGetNameTest(){
        Internship internship = new Internship();
        
        internship.setName("Programator Java");
        
        assertTrue(internship.getName().equals("Programator Java"));
    }
    
    @Test 
    public void SetGetDepartmentTest(){
        Internship internship = new Internship();
        
        internship.setDepartment("Mobile Department");
        
        assertTrue(internship.getDepartment().equals("Mobile Department"));
    }
    
    @Test 
    public void SetGetCityTest(){
        Internship internship = new Internship();
        
        internship.setCity("Bucharest");
        
        assertTrue(internship.getCity().equals("Bucharest"));
    }
    
    @Test
    public void SetGetPeriodTest(){
        Internship internship = new Internship();
        
        internship.setPeriod("01.07.2016 - 30.09.2016");
        
        assertTrue(internship.getPeriod().equals("01.07.2016 - 30.09.2016"));
    }
    
    @Test 
    public void SetGetSeats(){
        Internship internship = new Internship();
        int numberOfSeats = 10;
        
        internship.setSeats(numberOfSeats);
        
        assertTrue(internship.getSeats() == numberOfSeats);
    }
    
    @Test
    public void SetGetApplications(){
        Internship internship = new Internship();
        int numberOfApplications = 10;
        
        internship.setApplications(numberOfApplications);
        
        assertTrue(internship.getApplications() == numberOfApplications);
    }
    
    @Test
    public void SetGetReport(){
        Internship internship = new Internship();
        int report = 27 / 10;
        
        internship.setReport(report);
        
        assertTrue(internship.getReport() == report);
    }
    
    @Test
    public void SetIsSeen(){
        Internship internship = new Internship();
        boolean seen = true;
        
        internship.setSeen(seen);
        
        assertTrue(internship.isSeen());
    }
    
    @Test
    public void SetIsFavorite(){
        Internship internship = new Internship();
        boolean favorite = true;
        
        internship.setFavorite(favorite);
        
        assertTrue(internship.isFavorite());
    }
    
    @Test
    public void SetIsApplied(){
        Internship internship = new Internship();
        boolean favorite = true;
        
        internship.setApplied(favorite);
        
        assertTrue(internship.isApplied());
    }
}
