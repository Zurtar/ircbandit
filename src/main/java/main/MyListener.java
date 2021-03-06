package main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import org.pircbotx.hooks.events.*;

public class MyListener extends ListenerAdapter {

    @Override
    public void onIncomingFileTransfer(IncomingFileTransferEvent event) {
        //Checks if the file transfer is coming from the search bot -- good saftey messure
        //Can be duped by faking a nick but I think the nick is always taken on #bookz so it should be good
        //Anyway this project is not designed to stand up to the security requirements of large scale use

//TODO: Right now it treats every file transfer as a search transfer need to have 
//a way to specify wether we are geting a file from a search or getting a file from a download
        String nick = event.getUser().getNick();

        //SEARCH DOWNLOAD CASE
        if (nick.equals("SearchOok")) {
            try {
                String fileName = event.getSafeFilename();
                File destFile = new File(Main.SEARCH_FILE_DIR + fileName);

                event.acceptAndTransfer(destFile);
                Unzipper.unzip(destFile.getPath(), Main.SEARCH_FILE_DIR + fileName);
                destFile.delete();
                
                
            } catch (IOException ex) {
                Logger.getLogger(MyListener.class.getName()).log(Level.SEVERE, null, ex);
            }

            return;
        }

        //BOOK DOWNLOAD CASE
        if (nick.equals("000000000")) {

        }
    }

    @Override
    public void onConnect(ConnectEvent event) {

        //Runs our thread that listenes to System.in
        Thread scannerThread = new Thread(new InputListener());
        scannerThread.start();
    }

    //DEBUG METHODS FOR GETTING INFO FROM SERVER
    @Override
    public void onServerResponse(ServerResponseEvent event) {
//        System.out.println(event.getParsedResponse());
    }

    @Override
    public void onGenericMessage(GenericMessageEvent event) {

    }

    //
    @Override
    public void onDisconnect(DisconnectEvent event) {
//        System.out.println("Disconnected Ex: " + event.getDisconnectException().toString());
    }

    @Override
    public void onConnectAttemptFailed(ConnectAttemptFailedEvent event) {
//        System.out.println("Connect Attempt Failed Ex: " + event.getConnectExceptions());
    }

    @Override
    public void onException(ExceptionEvent event) {
//        System.out.println(event.getException());
    }

}
