package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExceptionHandling {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        System.out.println("Un-comment each part alone to see and run.");



        // try {
        //     int a = 10, b = 0;
        //     int result = a / b;   // ArithmeticException: / by zero
        //     System.out.println("Will never reach here.");
        // }
        // catch (Exception e) { // General Exception, catches ArithmeticException
        //     System.out.println(e);
        // }



        // try {
        //     int arr[] = {1, 2, 3};
        //     System.out.println(arr[6]);
        // }
        // catch (ArithmeticException | ArrayIndexOutOfBoundsException e) { // Union catch (multiple exceptions in one block)
        //     System.out.println(e);
        // }



        // // Multiple catch order (child first, then parent)
        // // Child exceptions must come before parent exceptions, otherwise it’s unreachable code.
        // try {
        //     int num = Integer.parseInt("123a"); // NumberFormatException
        //     System.out.println("The number is: " + num);
        // }
        // catch (NumberFormatException e) { // More specific (child class of IllegalArgumentException). If you know the types of the expected exceptions, write them instead of the parent class Exception. This is faster.
        //     System.out.println(e + "\nThis string contains non-numeric character.");
        // }
        // catch (Exception e) { // If you do not know the type of the exception, this parent class will consider all (generic fallback)
        //     System.out.println(e);
        // }


        // // Nested try-catch (with custom exception)
        // try {
        //     System.out.print("Enter your age: ");
        //     String input = in.nextLine();
        //     try {
        //         int age = Integer.parseInt(input); // Convert to int
        //         if (age <= 0) throw new IllegalArgumentException("The negative age cannot be real.");
        //         else if (age < 18) throw new UnderAgeException("Sorry, your age is less than 18.");
        //         else System.out.println("You are approved.");
        //     }
        //     catch (NumberFormatException e) {
        //         e.printStackTrace();
        //     }
        //     catch (UnderAgeException e) { // Our Custom exception
        //         System.out.println("Custom Exception: " + e);
        //         e.printStackTrace();
        //     }
        // }
        // catch (Exception e) {
        //     e.printStackTrace();
        // }


        // // Multiple catch with finally
        // try {
        //     int arr[] = {1, 2, 3};
        //     arr[5] = 10 / 0;
        // }
        // catch (ArithmeticException e) {
        //     System.out.println(e);
        // }
        // catch (Exception e) {
        //     System.out.println(e);
        // }
        // finally {
        //     // The finally block executes always (unless System.exit or fatal JVM error)
        //     System.out.println("Will be executed in any case.");
        // }


        // // Try-With-Resources (auto-closes file)
        // String filePath = "test.txt";
        // try (FileInputStream fin = new FileInputStream(filePath)) {
        //     // pla pla
        // }
        // catch (Exception e) {
        //     // pla pla
        // }


        // // File reading with specific exceptions
        // String path = "example.txt";
        // try {
        //     // Call the method that may throw FileNotFoundException or IOException
        //     readFiles(path);
        //     System.out.println("\nFile read successfully!");
        // }
        // catch (FileNotFoundException e) {
        //     // Handles when file does not exist
        //     System.out.println("Error: File not found --> " + e.getMessage());
        //     e.printStackTrace();
        // }
        // catch (IOException e) {
        //     // Handles other IO-related problems
        //     System.out.println("Error: An I/O problem occurred --> " + e.getMessage());
        //     e.printStackTrace();
        // }
        // finally {
        //     // Always runs (even if exception occurs)
        //     System.out.println("Program finished (finally block executed).");
        // }


        // // Catching Error (not recommended in real apps)
        // try {
        //     infinity();
        // }
        // catch (Error e) {
        //     System.out.println(e);
        //     e.printStackTrace();
        // }



        // // Custom checked exception (InsufficientFundsException)
        // validatePrice(100);



        // // withdraw(80); // This alone outside: The compiler will forces you to handle it with try-catch or throws.
        // try {
        //     withdraw(80); // Change this 80 to 120 and run again.
        // }
        // catch (InsufficientFundsException e) {
        //     e.printStackTrace();
        //     System.out.println("Message: " + e.getMessage());
        // }


        // // Another custom exception (registerUser)
        // // We can use it alone outside try-catch block.
        // registerUser(15); // Change to -15 and run again.



        // // Using Logger instead of System.out
        // final Logger logger = Logger.getLogger(Main.class.getName());
        // logger.info("Application started\n");
        // try {
        //     int res = 5 / 0;
        // }
        // catch (ArithmeticException e) {
        //     logger.severe("An error occurred: " + e.getMessage());
        //     // Logger records error messages
        // }
        // logger.info("Application finished");



        in.close();
    }


    public static void validatePrice(double price) {
        if (price <= 0) {
            // If the price is zero or negative -> IllegalArgumentException is thrown.
            // IllegalArgumentException is a RuntimeException (unchecked),
            // typically used to indicate that a method has received an invalid parameter.
            throw new IllegalArgumentException("Sorry, price cannot be negative.");
        }
    }

    public static class UnderAgeException extends Exception { // Custom Checked Exception
        // This class defines a custom checked exception.
        // "Checked" means: compiler forces the method to either handle it (try-catch)
        // or declare it in its throws clause.
        public UnderAgeException(String message) {
            // Calls parent Exception class constructor with the given message.
            super(message);
        }
    }

    static void readFile(String filePath) {
        FileReader reader = null;
        try {
            reader = new FileReader(filePath);
            // If file does not exist, FileNotFoundException (a subclass of IOException) is thrown.
        }
        catch (IOException e) { // Catches IO-related problems, e.g., file not found, read failure.
            System.out.println(e);
        }
        catch (Exception e) { // Generic catch-all for other unexpected exceptions.
            System.out.println(e);
        }
        finally {
            try {
                reader.close(); // Close the file reader (important to avoid resource leaks).
            }
            catch (IOException e) {
                // If closing fails (rare), it is handled here.
                System.out.println(e);
            }
        }
    }

    static void readFiles(String filePath) throws FileNotFoundException, IOException { // Exception Propagation
        // The method signature declares that it may throw FileNotFoundException or IOException.
        // This means: the caller of this method MUST handle these exceptions (or propagate further).
        try (FileReader reader = new FileReader(filePath)) {
            // This is "try-with-resources". It automatically closes the FileReader at the end,
            // even if an exception occurs -> no need for an explicit finally block.
            // If the file does not exist -> FileNotFoundException is thrown.
            // If there’s a general I/O problem -> IOException is thrown.
            int data;
            while((data = reader.read()) != -1) { // Reads character by character until end of file (-1 means EOF).
                System.out.print((char)data);
            }
        }
        // No catch block here --> exceptions are propagated to the caller.
    }

    static void infinity() {
        // Demonstrates infinite recursion (no base case to stop recursion).
        // Each call consumes stack memory -> eventually causes a StackOverflowError.
        System.out.println("Infinity");
        infinity();
    }


    public static class InsufficientFundsException extends Exception { // Custom Checked Exception
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    // Simulates withdrawing money from a bank account.
    public static void withdraw(double amount) throws InsufficientFundsException {
        double currentBalance = 100.0;
        if (currentBalance < amount) {
            // If the withdrawal amount is more than balance -> throw checked exception.
            throw new InsufficientFundsException("Sorry, Your balance is not enough.");
        }
        currentBalance -= amount;
        System.out.println("Withdrawal successful.");
    }

    public static class InvalidAgeException extends RuntimeException { // Custom Unchecked Exception
        // Custom unchecked exception for invalid user age input.
        // Unchecked means compiler does not enforce handling.
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    public static void registerUser(int age) {
        if (age <= 0) {
            // If invalid age -> throw custom unchecked exception.
            throw new InvalidAgeException("Invalid Age.");
        }
        System.out.println("User registered successfully.");
    }
}