package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectAttemptFailedEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.ExceptionEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.ServerResponseEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class MyListener extends ListenerAdapter {

    @Override
    public void onServerResponse(ServerResponseEvent event) {
        System.out.println(event.getParsedResponse());
    }

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        try {
            //When someone says ?helloworld respond with "Hello World"
            if (event.getMessage().startsWith("?Ook")) {
                event.respond("Hello world!");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            event.getBot().sendIRC().message("#FrenkWentDownToFlorida", s);
        } catch (IOException ex) {
            Logger.getLogger(MyListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void onDisconnect(DisconnectEvent event) {
        System.out.println("Disconnected Ex: " + event.getDisconnectException().toString());
    }

    @Override
    public void onConnectAttemptFailed(ConnectAttemptFailedEvent event) {
        System.out.println("Connect Attempt Failed Ex: " + event.getConnectExceptions());
    }

    @Override
    public void onException(ExceptionEvent event) {
        System.out.println(event.getException());
    }

    //currently we can not connect to undernet I have a theory one why, I think it may be due to the nickname verification? or a safeguard in place that is preventing the bot from connecting to the undernetservers. 
    public static void main(String[] args) throws Exception {
        //Configure what we want our bot to do
        Configuration config = new Configuration.Builder()
                .setName("bandito") //Set the nick of the bot. CHANGE IN YOUR CODE
                .setLogin("zurtar")
                .addServer("irc.undernet.org") //Join the freenode network
                .addAutoJoinChannel("#FrenkWentDownToFlorida") //Join the official #pircbotx channel
                .addListener(new MyListener()) //Add our listener that will be called on Events
                .buildConfiguration();

        //Create our bot with the configuration
        PircBotX bot = new PircBotX(config);

        //Connect to the server
        bot.startBot();
    }
}
