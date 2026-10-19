
package userregistration;

import java.util.Scanner;


public class UserRegistration 
{
    private static String username;
    private static String password;
    private static String cellphoneNumber;
    private static String name;
    private static String surname;

   
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        System.out.println("*****User Registration*****");

        // ==================
        // NAME AND SURNAME
        // ==================
        System.out.print("Please enter your name: ");
        name = input.nextLine();

        if (name.isEmpty()) 
        {
            System.out.println("First name cannot be empty");
            return;
        }

        System.out.print("Please enter your last name: ");
        surname = input.nextLine(); 
        
          if (surname.isEmpty()) 
          {
            System.out.println("Last name cannot be empty");
            return;
          }

        // Create Login object with names
        Login login = new Login(name, surname);
        
        
        // ==================
        // CELLPHONE LOOP
        // ==================
        while (true) 
        {
            System.out.println("Enter your Cellphone Number (e.g., +27730712215):");
            cellphoneNumber = input.nextLine();

            if (login.checkCellPhoneNumber(cellphoneNumber)) 
            {
                System.out.println("Cellphone number successfully captured.");
                break;
            } else {
                System.out.println("Cellphone number is not correctly formatted.");
                System.out.println("Cellphone number must contain an international code (e.g., +27).");
            }
        }
        // ==================
        // USERNAME LOOP
        // ==================
        while (true) 
        {
            System.out.println("Enter your username (must contain '_' and be <= 5 chars):");
            username = input.nextLine();

            if (login.checkUserName(username)) 
            {
                System.out.println("Username successfully captured.");
                break; // Exit loop if valid
            } else {
                System.out.println("Username is not correctly formatted.");
                System.out.println("Username must contain an underscore (_) and be no more than 5 characters long.");
            }
        }
        // ==================
        // PASSWORD LOOP
        // ==================
        while (true) 
        {
            System.out.println("Enter your password (min 8 chars, 1 capital, 1 number, 1 special char):");
            password = input.nextLine();

            if (login.checkPasswordComplexity(password)) 
            {
                System.out.println("Password successfully captured.");
                break; 
            } else {
                System.out.println("Password is not correctly formatted.");
                System.out.println("Password must contain at least 8 characters, a capital letter, a number, and a special character.");
            }
        }
        // ==================
        // REGISTER AND LOGIN
        // ==================
        String regStatus = login.registerUser(username, password, cellphoneNumber, name, surname);
        System.out.println("\n" + regStatus);

        // If registration is successful, allow login
        if (regStatus.equals("Registration successful.")) 
        {
            System.out.println("\n==============================================");
            System.out.println("                    Login                       ");
            System.out.println("================================================");
            
            boolean loggedIn = false;
            
            while(!loggedIn)
            {
                System.out.println("Enter the username you created: ");
                String loginUsername = input.nextLine();
                
                System.out.println("Enter the password you created: ");
                String loginPassword = input.nextLine();
                
                String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
                System.out.println(loginStatus);
                
                if(loginStatus.contains("Welcome"))
                {
                    loggedIn = true;
                } else {
                    System.out.println("Login failed, please try again");
                }
            }
        }
    }    
    public static void startQuickChat(Scanner input)
    {
        System.out.println("\n==================================================");
        System.out.println("                    Welcome to QuickChat            ");
        System.out.println("====================================================");
        
        //Ask the user how many messages they want to enter
        System.out.println("How many messages would you like to enter?");
        int totalMessage = input.nextInt();
        input.nextLine();
        
        SendMessage[] message = new SendMessage[totalMessage];
        int messageSentCount = 0;
        
        boolean running = true;
        SendMessage lastMessage = null;
        
        //The app
        while(running){
            System.out.println("\n==============================================");
            System.out.println("                    QuickChat                   ");
            System.out.println("================================================");
            
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("Choose an option: ");
            
            int option = input.nextInt();
            input.nextLine();
            
            switch(option){
                case 1:
                    
                    for(int i = 0; i < totalMessage; i++)
                    {
                        messageSentCount++;
                        
                        System.out.println("Enter cellphone number (with international code");
                        String recipient = input.nextLine();
                        
                        System.out.println("Enter message (max 250 characters: ");
                        String messageText = input.nextLine();
                        
                        SendMessage newMessage = new SendMessage(messageSentCount, recipient, messageText);
                        lastMessage = newMessage;
                        
                        String  lengthCheck = newMessage.checkMessageLength();
                        System.out.println(lengthCheck);
                        
                        if(lengthCheck.startsWith("Message exceeds"))
                        {
                            continue;
                        }
                        System.out.println(newMessage.checkRecipientCell());
                        
                        System.out.println("\n1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");
                        System.out.println("Choose: ");
                        int choice = input.nextInt();
                        
                        System.out.println(newMessage.SentMessage(choice));
                        System.out.println("\n"+ newMessage.printMessage());
                    }
                    
                    if(lastMessage != null)
                    {
                        System.out.println("\nTotal messages sent: "+ messageSentCount);
                    }
                case 2:
                    System.out.println("Coming Soon");
                    break;
                    
                case 3:
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid option. Please choose 1,2, or 3.");
            }
            
        }    
        input.close();
    }
    
}
