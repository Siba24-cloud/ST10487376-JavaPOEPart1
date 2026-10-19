
package userregistration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class SendMessage 
{
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    
    private static ArrayList<SendMessage> sentMessage = new ArrayList<>();
    private static ArrayList<SendMessage> disregardedMessage = new ArrayList<>();
    private static ArrayList<SendMessage> storedMessage = new ArrayList<>();
    private static int totalMessage = 0;
    private SendMessage json;
    
    public SendMessage(int messageNumber, String messageID, String recipient)
    {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }
    
    public static String generateMessageID()
    {
        Random rand = new Random();
        long id = (long) (rand.nextDouble()*9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }
    
    public String getMessageID()
    {
        return messageID;
    }
    
    public String getMessageHash()
    {
        return messageHash;
    }
    public String getRecipient()
    {
        return recipient;
    }
    public int getMessageNumber()
    {
        return messageNumber;
    }
    public boolean checkMessageID()
    {
        return messageID.length() <= 10;
    }
    public boolean checkRecipientCell()
    {
        if(recipient == null)
        {
            return false;
        }
        String regex = "^\\+\\d{10,13}$";
        return recipient.matches(regex);
    }
    public String createMessageHash()
    {
        String firstTwoNumbers;
        if(messageID  != null && messageID.length() >= 2)
        {
            firstTwoNumbers = messageID.substring(0, 2);
        } else {
            firstTwoNumbers = messageID;
        }
        String firstWord = "";
        String lastWord = "";
        
        if(message != null && !message.trim().isEmpty())
        {
            String messageText = message.trim();
            String[] words = messageText.split("\\s+");
            firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
            lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        }
        return(firstTwoNumbers+ ":"+ messageNumber+ ":"+ firstWord+ lastWord).toUpperCase();
    }
    public String checkMessageLength()
    {
        if(messageText.length() <= 250)
        {
            return "Message ready to send";
        }
        int excess = messageText.length() - 250;
        return "Message excceds 250 characters by "+ excess + "; please reduce the size.";
    }
    public String SentMessage(int choice)
    {
        switch(choice){
             case 1:
                sentMessage.add(this);
                totalMessage++;
                return "Message successfully sent.";
            case 2:
                disregardedMessage.add(this);
                return "Press 0 to delete the message.";
            case 3:
                storedMessage.add(this);
                storeMessage(); 
                return "Message successfully stored.";
            default:
                return "Invalid option selected.";
        }
    }
    public String printMessage()
    {
         return "Message ID: " + messageID + 
               "\nMessage Hash: " + messageHash + 
               "\nRecipient: " + recipient + 
               "\nMessage: " + message;
    }
    public void storeMessage()
    {
        storedMessage.add(json);
        
        try(FileWriter file = new FileWriter("message.json", true)) 
        {
            String json = "{\n" +
                    "  \"MessageID\": \"" + this.messageID + "\",\n" +
                    "  \"MessageHash\": \"" + this.messageHash + "\",\n" +
                    "  \"Recipient\": \"" + this.recipient + "\",\n" +
                    "  \"Message\": \"" + this.message + "\"\n" +
                    "}\n";
            file.write(json);
        }   catch(IOException e)
        {
            System.out.println("Errorsaving message: "+ e.getMessage());
        }
    }
    public int returnTotalMessage()
    {
        return storedMessage.size();
    }
}