package u21g;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/** A class to hold a sttic method to validate user input in terms of character-types
 *  allowed and length into the 'register a user' page forms.
 * 
 */
public class RegistUserInputValidator {

    
    //  Regex for email addresses.  Email addresses follow patern of ___@___.__ .  First and second segs can have 1-20 chars.  
    // Last seg can have 2-6.  Alpha numeric plus a few other special characters are allowed for the first segment.
    // dashes allowed in second segment.  last segment must be alpha characters only.  Entire
    // email address is case insensitive.
    
    /**
    *  Regex for email addresses.  Email addresses follow patern of ___@___.__ .  First and second segs can have 1-20 chars.  
    * Last seg can have 2-6.  Alpha numeric plus a few other special characters are allowed for the first segment.
    * dashes allowed in second segment.  last segment must be alpha characters only.  Entire
    * email address is case insensitive.
     */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]{1,20}@[A-Z0-9.-]{1,20}\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    /**
    *  Regex for usernames.  Usernames must start with an alpha letter, and the rest must be alphnumeric.  Case insensitive.
    *  Usernames must be bewteeen 1 and 20 characters with no spaces.  
     */
    private static final Pattern VALID_USERNAME_REGEX = 
    Pattern.compile("^[A-Za-z][A-Za-z0-9]{1,20}$");
    
    /**
    *  Regex for passwords.  Passwords can include any characters except spaces and must be 2-20 characters long.  
     */
    private static final Pattern VALID_PASSWORD_REGEX =
    Pattern.compile("^[\\S]{2,20}$");
    /**
    *  Regex for first names.  For simplicity's sake they must be a single word with no spaces.  They must all be alpha characters, 
    *  but are case insensitive and 2-20 characters long.  
     */
    private static final Pattern VALID_FIRSTNAME_REGEX =
    Pattern.compile("^[A-Za-z]{2,20}$");
        /**
    *  Regex for last names.  For simplicity's sake they must be a single word with no spaces.  They must all be alpha characters, 
    *  but are case insensitive and 2-20 characters long.  
     */
    private static final Pattern VALID_LASTNAME_REGEX =
    Pattern.compile("^[A-Za-z]{2,20}$");
            /**
    *  Regex for uriid's.  Uriid's must all be numeric characters, a single number with no spaces, cannot start with a 0, and must 
    *  be 1-20 characters long.  
     */
    private static final Pattern VALID_URIID_REGEX =
    Pattern.compile("^[1-9][0-9]{0,19}$");

/**
 * Checks the string entered in the JTextfield by the user against
 * an appropriate regular expression.
 * 
 * @param emailStr, the String captured by the email JTextfield
 * @return true if the string matches the regex.
 */
public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
}   

/**
 * Checks the string entered in the JTextfield by the user against
 * an appropriate regular expression.
 * 
 * @param userName, the String captured by the userName JTextfield
 * @return true if the string matches the regex.
 */
public static boolean validateUserName(String userName){
    Matcher matcher = VALID_USERNAME_REGEX.matcher(userName);
    return matcher.find();
}

/**
 * Checks the string entered in the JTextfield by the user against
 * an appropriate regular expression.
 * 
 * @param firstName, the String captured by the firstName JTextfield
 * @return true if the string matches the regex.
 */
public static boolean validateFirstName(String firstName){
    Matcher matcher = VALID_FIRSTNAME_REGEX.matcher(firstName);
    return matcher.find();
}

/**
 * Checks the string entered in the JTextfield by the user against
 * an appropriate regular expression.
 * 
 * @param lastName, the String captured by the lastName JTextfield
 * @return true if the string matches the regex.
 */
public static boolean validateLastName(String lastName){
    Matcher matcher = VALID_LASTNAME_REGEX.matcher(lastName);
    return matcher.find();
}

/**
 * Checks the string entered in the JTextfield by the user against
 * an appropriate regular expression.
 * 
 * @param password, the String captured by the password JTextfield
 * @return true if the string matches the regex.
 */
public static boolean validatePassword(String password){
    Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
    return matcher.find();
}

/**
 * Checks the string entered in the JTextfield by the user against
 * an appropriate regular expression.
 * 
 * @param uriid, the String captured by the uriid JTextfield
 * @return true if the string matches the regex.
 public static boolean validateUriID(String uriid){
 */
public static boolean validateUriID(String uriid){
    Matcher matcher = VALID_URIID_REGEX.matcher(uriid);
    return matcher.find();
}
}
