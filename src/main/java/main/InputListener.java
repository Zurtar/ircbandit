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
public class InputListener implements Runnable {

    Scanner sc = new Scanner(System.in);

    public void run() {

        while (true) {
            if (sc.hasNextLine()) {
                try {
                    Main.bot.sendIRC().message("#bookz", sc.nextLine());
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(InputListener.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

}
