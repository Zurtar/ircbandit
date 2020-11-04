/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 *
 * @author Steve
 */
public class Unzipper {

    //TODO: Contains all methods for going from a zipped file -> to a txt file or book file.
//https://stackoverflow.com/questions/9324933/what-is-a-good-java-library-to-zip-unzip-files
    public static void unzip(String source, String dest) {
        try {
            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(dest);
        } catch (ZipException ex) {
            Logger.getLogger(ArchiveHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
