package u21g;

import javax.swing.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *     The registUserInputValidator class validates user input from the UserRegistration class.  The JTextField input
 *      for each field is checked against an appropriate regular expression.  This class contains the tests for the 
 *      registUserInputValidator class.  The tests check various types of user input against the validator methods.
 */
public class registUserInputValidatorTest {


    // USERNAME TESTS

    @Test
    @DisplayName("True is returned when username entered with alpha input")
    void userNameAlpha() {
        assertTrue(RegistUserInputValidator.validateUserName("dave"));
    }

    @Test
    @DisplayName("True is returned when username entered with alphanumeric input")
    void userNameAlphaNum() {
        assertTrue(RegistUserInputValidator.validateUserName("dave123"));
    }

    @Test
    @DisplayName("False returned when username begins with a number")
    void UserNameNumStart() {
        assertFalse(RegistUserInputValidator.validateUserName("123da1ve"));
    }

    @Test
    @DisplayName("False is returned when username entered with alphanumeric and an impermissible (special) character ('@'')")
    void userNameAlphaNumImpermissChar() {
        assertFalse(RegistUserInputValidator.validateUserName("dav1e@"));
    }

    @Test
    @DisplayName("False is returned when username entered with alphanumeric and a space included)")
    void userNameAlphaNumSpace() {
        assertFalse(RegistUserInputValidator.validateUserName("da 1ve"));
    }

    @Test
    @DisplayName("True is returned when username entered including capital letters")
    void userNameCapitals() {
        assertTrue(RegistUserInputValidator.validateUserName("DOgtheBounTyH"));
    }

    @Test
    @DisplayName("False is returned when username exceeds 20 characters")
    void userNameExceeds20() {
        assertFalse(RegistUserInputValidator.validateUserName("DOgtheBounTyHunter1234"));
    }

    @Test
    @DisplayName("False is returned when username is less than 2 characters")
    void userNameLessThan2() {
        assertFalse(RegistUserInputValidator.validateUserName("D"));
    }
    

    // PASSWORD TESTS

    @Test
    @DisplayName("True is returned when password entered with alpha input")
    void passwordAlpha() {
        assertTrue(RegistUserInputValidator.validatePassword("dave"));
    }

    @Test
    @DisplayName("True is returned when password entered with alphanumeric input")
    void passwordAlphaNum() {
        assertTrue(RegistUserInputValidator.validatePassword("dave123"));
    }

    @Test
    @DisplayName("True is returned when password begins with a number")
    void passwordNumStart() {
        assertTrue(RegistUserInputValidator.validatePassword("123da1ve"));
    }

    @Test
    @DisplayName("True is returned when password entered with alphanumeric and an special character ('@'')")
    void passwordAlphaNumSpecialChar() {
        assertTrue(RegistUserInputValidator.validatePassword("dav1e@"));
    }

    @Test
    @DisplayName("False is returned when password entered with alphanumeric and a space included)")
    void passwordAlphaNumSpace() {
        assertFalse(RegistUserInputValidator.validatePassword("da 1ve"));
    }

    @Test
    @DisplayName("True is returned when password entered including capital letters")
    void passwordCapitals() {
        assertTrue(RegistUserInputValidator.validatePassword("DOgtheBounTyH"));
    }

    @Test
    @DisplayName("False is returned when password exceeds 20 characters")
    void passwordExceeds20() {
        assertFalse(RegistUserInputValidator.validatePassword("DOgtheBounTyHunter1234"));
    }


    @Test
    @DisplayName("False is returned when password is less than 2 characters")
    void passwordLessThan2() {
        assertFalse(RegistUserInputValidator.validatePassword("A"));
    }


    
// EMAIL TESTS

@Test
@DisplayName("True is returned when email is entered in correct __@__.__ format")
void emailFormat() {
    assertTrue(RegistUserInputValidator.validateEmail("dave@dave.com"));
}

@Test
@DisplayName("False is returned when no '@' is included in email format")
void emailFormatAtSign() {
    assertFalse(RegistUserInputValidator.validateEmail("davedave.com"));
}

@Test
@DisplayName("True is returned when email entered with 2 letter top level domain ")
void emailAlphaNum() {
    assertTrue(RegistUserInputValidator.validateEmail("dave123@dave.de"));
}

@Test
@DisplayName("True is returned when email begins with a number")
void emailNumStart() {
    assertTrue(RegistUserInputValidator.validateEmail("123da1ve@dave.com"));
}

@Test
@DisplayName("False is returned when no top level domain is provided")
void emailAlphaNumSpecialChar() {
    assertFalse(RegistUserInputValidator.validateEmail("dav1e@dave"));
}

@Test
@DisplayName("False is returned when email entered with including a space")
void emailAlphaNumSpace() {
    assertFalse(RegistUserInputValidator.validateEmail("da 1ve@dave.com"));
}

@Test
@DisplayName("True is returned when email entered including capital letters")
void emailCapitals() {
    assertTrue(RegistUserInputValidator.validateEmail("DOgtheBounTyH@dave.com"));
}

@Test
@DisplayName("False is returned when email exceeds 20 characters in first segment")
void emailExceeds46() {
    assertFalse(RegistUserInputValidator.validateEmail("DOgtheBounTyHunter1234@dave.com"));
}


@Test
@DisplayName("False is returned when email is less than 1 characters in first segment")
void emailLessThan1() {
    assertFalse(RegistUserInputValidator.validateEmail("@Dave.com"));
}


//uriid Tests

@Test
@DisplayName("True is returned when uriid is numeric")
void uriidNumeric() {
    assertTrue(RegistUserInputValidator.validateUriID("1234"));
}

@Test
@DisplayName("False is returned if uriid includes alpha characters")
void uriidIncludesAlpha() {
    assertFalse(RegistUserInputValidator.validateUriID("123d"));
}

@Test
@DisplayName("False is returned if uriid inlcudes special characters")
void uriidSpecialChar() {
    assertFalse(RegistUserInputValidator.validateUriID("1234%"));
}

@Test
@DisplayName("False is returned if uriid includes a space")
void uriidIncludesSpace() {
    assertFalse(RegistUserInputValidator.validateUriID("123 12"));
}

@Test
@DisplayName("False is returned if uriid starts with a 0")
void uriidStartsWithZero() {
    assertFalse(RegistUserInputValidator.validateUriID("01234"));
}

@Test
@DisplayName("False is returned if URIID exceeds 20 characters")
void uriidExceeds20() {
    assertFalse(RegistUserInputValidator.validateUriID("1234567891234567891231"));
}

// First Name tests

@Test
@DisplayName("True is returned when name entered with alpha input")
void firstNameAlpha() {
    assertTrue(RegistUserInputValidator.validateFirstName("dave"));
}

@Test
@DisplayName("False is returned when firstName entered including numeric input")
void firstNameAlphaNum() {
    assertFalse(RegistUserInputValidator.validateFirstName("dave1"));
}

@Test
@DisplayName("False returned when firstName includes a space")
void FirstNameNumStart() {
    assertFalse(RegistUserInputValidator.validateFirstName("David Andrew"));
}

@Test
@DisplayName("False is returned when firstName entered with a special character ('@'')")
void firstNameAlphaNumImpermissChar() {
    assertFalse(RegistUserInputValidator.validateFirstName("dave@"));
}


@Test
@DisplayName("True is returned when firstName entered including capital letters")
void firstNameCapitals() {
    assertTrue(RegistUserInputValidator.validateFirstName("DOgtheBounTyH"));
}

@Test
@DisplayName("False is returned when firstName exceeds 20 characters")
void firstNameExceeds20() {
    assertFalse(RegistUserInputValidator.validateFirstName("DOgtheBounTyHunterasdfsdfasdfscv"));
}

@Test
@DisplayName("False is returned when firstName is less than 2 characters")
void firstNameLessThan2() {
    assertFalse(RegistUserInputValidator.validateFirstName("D"));
}

// Last name tests

@Test
@DisplayName("True is returned when name entered with alpha input")
void lastNameAlpha() {
    assertTrue(RegistUserInputValidator.validateLastName("dave"));
}

@Test
@DisplayName("False is returned when lastName entered including numeric input")
void lastNameAlphaNum() {
    assertFalse(RegistUserInputValidator.validateLastName("dave1"));
}

@Test
@DisplayName("False returned when lastName includes a space")
void LastNameNumStart() {
    assertFalse(RegistUserInputValidator.validateLastName("David Andrew"));
}

@Test
@DisplayName("False is returned when lastName entered with a special character ('@'')")
void lastNameAlphaNumImpermissChar() {
    assertFalse(RegistUserInputValidator.validateLastName("dave@"));
}


@Test
@DisplayName("True is returned when lastName entered including capital letters")
void lastNameCapitals() {
    assertTrue(RegistUserInputValidator.validateLastName("DOgtheBounTyH"));
}

@Test
@DisplayName("False is returned when lastName exceeds 20 characters")
void lastNameExceeds20() {
    assertFalse(RegistUserInputValidator.validateLastName("DOgtheBounTyHunterasdfsdfasdfscv"));
}

@Test
@DisplayName("False is returned when lastName is less than 2 characters")
void lastNameLessThan2() {
    assertFalse(RegistUserInputValidator.validateLastName("D"));
}


}
