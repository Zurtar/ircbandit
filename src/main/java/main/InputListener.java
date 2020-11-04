/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.types.GenericMessageEvent;

/**
 *
 * @author Steve
 */
//TODO: RIght now it just takes input and throws it right into a message
//We will need to have it check a STATE or something to see if its sending a message to the irc channel or maybe choosing a file from a list(1,2,3..etc) or confirming a download (Y/N)
//Basicly just need to extend the logic to cover the different cases that we need to read input for
public class InputListener implements Runnable {

    public static String input = "";
    Scanner sc = new Scanner(System.in);

    public void run() {

        while (true) {
            if (sc.hasNextLine()) {
                try {
                    input = sc.nextLine();
                    Main.bot.sendIRC().message(Main.CHANNEL_NAME, input);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }

            }
        }
    }

}
