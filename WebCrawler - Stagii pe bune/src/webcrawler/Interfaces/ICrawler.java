/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler.Interfaces;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Interface which modeling the crawler functionality.
 * @author Adrian ISPAS, 243
 */
public interface ICrawler {
    /***
     * Parse algorithm for the crawler.
     */
    void parse()throws MalformedURLException, IOException;
}
