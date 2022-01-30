package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("amount cannot be negative or have more than 2 decimal places");

        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }
    /**
     * @post deposit amount to bankaccount
     */
    public void deposit(double amount) throws IllegalArgumentException {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("amount cannot be negative or have more than 2 decimal places");
        }
        else {
            balance = balance + amount;
        } 
    }

     /**
     * @post transfering amount to another bankaccount
     */
    public void transferAmount(BankAccount account, double amount) throws InsufficientFundsException, IllegalArgumentException{
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount Invalid");
        }
        else{
           withdraw(amount);
            account.deposit(amount); //account to which we trasfer to
        }
    
    }


   /**
    * @return true if the amount is positive and has two decimal points or less, and false otherwise
    */
    public static boolean  isAmountValid(double amount){
        String doubleStr = Double.toString(amount);

        if(amount < 0){
            return false;
            
        }
        else if(doubleStr.substring(doubleStr.lastIndexOf('.'), doubleStr.length() - 1).length() > 2){ //check if amount has 5 or more digits (possibility that there is 3 decimals) 300.67 , 30.678
            return false;
        }
        else{
            return true;
        }


    }
     /**
     * @post checks for valid emails
     */
    public static boolean isEmailValid(String email){
        int count = 0;

        for(int i = 0; i < email.length(); i++){
            if(email.charAt(i) == '@'){
                count++;
            }
        }

        if(count != 1){
            return false;
        }

        String[] splitEmail = email.split("@", 2);

        if(splitEmail[0].contains("!") || splitEmail[0].contains("#") || splitEmail[0].contains("$") || 
        splitEmail[0].contains("%") || splitEmail[0].contains("^") || splitEmail[0].contains("&") ||
        splitEmail[0].contains("*") || splitEmail[0].contains("(") || splitEmail[0].contains(")") ||
        splitEmail[0].contains("+") || splitEmail[0].contains("=") || splitEmail[0].contains("[") ||
        splitEmail[0].contains("]") || splitEmail[0].contains("{") || splitEmail[0].contains("}") ||
        splitEmail[0].contains("<") || splitEmail[0].contains(">") || splitEmail[0].contains(",") ||
        splitEmail[0].contains("/") || splitEmail[0].contains("?") || splitEmail[0].contains("|")){
            return false;
        }
        else if(splitEmail[0].charAt(0) == '.'){
            return false;
        }

        if(splitEmail[1].contains("!") || splitEmail[1].contains("#") || splitEmail[1].contains("$") || 
        splitEmail[1].contains("%") || splitEmail[1].contains("^") || splitEmail[1].contains("&") ||
        splitEmail[1].contains("*") || splitEmail[1].contains("(") || splitEmail[1].contains(")") ||
        splitEmail[1].contains("+") || splitEmail[1].contains("=") || splitEmail[1].contains("[") ||
        splitEmail[1].contains("]") || splitEmail[1].contains("{") || splitEmail[1].contains("}") ||
        splitEmail[1].contains("<") || splitEmail[1].contains(">") || splitEmail[1].contains(",") ||
        splitEmail[1].contains("/") || splitEmail[1].contains("?") || splitEmail[1].contains("|") ||
        splitEmail[1].contains("_")){
            return false;
        }
        if(!splitEmail[1].contains(".")){
            return false;
        }
        if(splitEmail[1].length() < 3){
            return false;
        }

        else{
            return true;
        }

    }
}