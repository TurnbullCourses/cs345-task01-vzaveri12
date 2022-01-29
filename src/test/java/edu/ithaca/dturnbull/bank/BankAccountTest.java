package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance(), 0.001);
    
        BankAccount bankAccount2 = new BankAccount("abc@gmail.com", -1200);
        assertThrows(InsufficientFundsException.class, () -> bankAccount2.getBalance());


    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        assertThrows(InsufficientFundsException.class, () -> bankAccount2.withdraw(-300)); 
        
    }

    @Test
    void isAmountValid(){
        // Testing for decimal places and positive numbers
        assertTrue(BankAccount.isAmountValid(300.00));
        assertTrue(BankAccount.isAmountValid(30.50));
        assertFalse(BankAccount.isAmountValid(70.345));
        assertFalse(BankAccount.isAmountValid(-70.00));

    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string, border case
        assertFalse(BankAccount.isEmailValid("a@"));        // has no domain, border case
        assertTrue(BankAccount.isEmailValid("aa@c.edu"));   // valid email address, not a border case
        assertFalse(BankAccount.isEmailValid("a@$"));       // has no domain and special character, border case
        assertFalse(BankAccount.isEmailValid("@@gmail.com"));
        assertTrue(BankAccount.isEmailValid("ab@gmail.com"));
        assertTrue(BankAccount.isEmailValid("012_abc@gmail.com"));
        assertFalse(BankAccount.isEmailValid("$$$@gmail.com"));
        assertFalse(BankAccount.isEmailValid(".ab@com"));


        
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}