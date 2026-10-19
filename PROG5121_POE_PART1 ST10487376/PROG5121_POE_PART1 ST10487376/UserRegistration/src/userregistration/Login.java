package userregistration;


public class Login 
{
    private String cellphoneNumber;
    private String name;
    private String surname;

    // Variables for checking login
    private String storedUsername;
    private String storedPassword;

    public Login(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }
    
    // ==========================================
    // Checks the username format
    // ==========================================
    public boolean checkUserName(String username)
    {
        // Must contain an underscore AND be no more than 5 characters long
        return username.contains("_") && username.length() <= 5;
    }
    // ==========================================
    // Checks password complexity
    // ==========================================
    public boolean checkPasswordComplexity(String password) 
    {
        // Must be at least 8 chars, 1 capital, 1 number, 1 special char
        boolean passwordOkay = false;
        boolean hasNumber = false;
        boolean hasCapitalLetter = false;
        boolean hasSpecialCharacter = false;
        char current;

        if (password.length() >= 8) 
        {
            for (int i = 0; i < password.length(); i++) 
            {
                current = password.charAt(i);

                if (Character.isUpperCase(current)) 
                {
                    hasCapitalLetter = true;
                }
                if (Character.isDigit(current)) 
                {
                    hasNumber = true;
                }
                if (!(Character.isLetterOrDigit(current))) 
                {
                    hasSpecialCharacter = true;
                }
            }
            if (hasNumber && hasCapitalLetter && hasSpecialCharacter) 
            {
                passwordOkay = true;
            }
        }
        return passwordOkay;
    }
    // ==========================================
    // Checks cell phone number using Regex
    // ==========================================
    public boolean checkCellPhoneNumber(String number) 
    {
        // Regex for international format: starts with +, followed by 10 to 13 digits
        // Example: +27730712215
        String regex = "^\\+\\d{10,13}$";
        return number.matches(regex);
    }
    
    // ==========================================
    // Registers the user and returns status messages
    // ==========================================
    public String registerUser(String username, String password, String cellphoneNumber, String name, String surname) 
    {
        this.name = name;
        this.surname = surname;

        if (!checkUserName(username)) 
        {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) 
        {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellphoneNumber)) 
        {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // If all validations pass, store the data for later login
        this.cellphoneNumber = cellphoneNumber;
        this.storedUsername = username;
        this.storedPassword = password;

        return "Registration successful.";
    }

    public boolean loginUser(String username, String password) 
    {
        return username.equals(this.storedUsername) && password.equals(this.storedPassword);
    }
     public String returnLoginStatus(String username, String password) 
     {
        if (loginUser(username, password)) 
        {
            return "Welcome " + this.name + " " + this.surname + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
