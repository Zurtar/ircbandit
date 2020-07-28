package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectAttemptFailedEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class MyListener extends ListenerAdapter {

    static int i = 0;

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        //When someone says ?helloworld respond with "Hello World"
        if (event.getMessage().startsWith("?Ook")) {
            event.respond("Hello world!");
        }
    }

    @Override
    public void onDisconnect(DisconnectEvent event) {
        System.out.println("Disconnected Ex: " + event.getDisconnectException().toString());
    }

    @Override
    public void onConnectAttemptFailed(ConnectAttemptFailedEvent event) {
        System.out.println("Connect Attempt Failed Ex: "+event.getConnectExceptions());
    }

    //currently we can not connect to undernet I have a theory one why, I think it may be due to the nickname verification? or a safeguard in place that is preventing the bot from connecting to the undernetservers. 
    public static void main(String[] args) throws Exception {
        //Configure what we want our bot to do
        Configuration config = new Configuration.Builder()
                .setName("Zurtar") //Set the nick of the bot. CHANGE IN YOUR CODE
                .addServer("irc.undernet.org") //Join the freenode network
                .addAutoJoinChannel("#Hdadkjaskjdkashd") //Join the official #pircbotx channel
                .addListener(new MyListener()) //Add our listener that will be called on Events
                .buildConfiguration();

        //Create our bot with the configuration
        PircBotX bot = new PircBotX(config);

        //Connect to the server
        bot.startBot();
        System.out.println("\n\n\n\n\n" + bot.getServerInfo());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }
}
