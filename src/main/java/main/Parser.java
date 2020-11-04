/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Steve
 */
public class Parser {

    public String foo() throws FileNotFoundException, IOException {
        //TODO:
        //Fix class name
        //pass an arg for file type
        //setup a return or something

        String searchTerm = InputListener.input;
        searchTerm = searchTerm.substring(6, searchTerm.length());

        BufferedReader br = new BufferedReader(new FileReader(new File("a")));

        //We skip the first 6 lines because of how the search results are formated.
        for (int i = 0; i < 6; i++) {
            br.readLine();
        }

        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("epub") && line.contains(searchTerm)) return line;
        }
        
        //Failed search
        return null;
    }

}
