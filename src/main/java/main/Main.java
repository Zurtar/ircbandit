/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

/**
 *
 * @author Steve
 */
public class Main {

    public static PircBotX bot;
    public static MyListener listener = new MyListener();

    public static void main(String[] args) throws Exception {
        //Configure what we want our bot to do
        Configuration config = new Configuration.Builder()
                .setName("LilTar") //Set the nick of the bot. 
                .setLogin("Zurtar")
                .addServer("irc.undernet.org") //Join the  network
                .addAutoJoinChannel("#bookz") //Join the  channel
                .addListener(listener) //Add our listener that will be called on Events
                .buildConfiguration();

        //Create our bot with the configuration
        bot = new PircBotX(config);
//        new InputListener().run();
        //This method is stopping
        bot.startBot();

    }
}
