package org.example;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class Enums {
    public static void main(String[] args) {
        // Example 1: Using enum in a switch-case
        WeekDays today = WeekDays.TUESDAY;
        switch (today) {
            case TUESDAY: {
                System.out.println("It's Tuesday, keep going.");
                // You can write more stuff here as you want to
                // Do not forget to use break to avoid fall-through
                break;
            }
            case WEDNESDAY: {
                System.out.println("Midweek already.");
                break;
            }
            default: {
                System.out.println("It's another day of the week.");
                break;
            }
        }

        // Example 2: Using enum with extra fields (Status with code + message)
        Status currentStatus = Status.SUCCESS;
        // Switch expression + string concatenation to simulate a response for client
        String sendToClient = "Hello Client, " + switch (currentStatus) {
            case PENDING -> "Your request is being processed.";
            case SUCCESS -> "Operation completed successfully.";
            case ERROR -> "An error occurred, please try again.";
            case NOT_FOUND -> "Requested resource not found.";
            // No need for a default case here since all enum values are covered
        } + " Status code: " + currentStatus.getCode() + ", Internal Message: " + currentStatus.getMessage();
        // The final message
        System.out.println(sendToClient);

        // Example 3: Iterating over enum values
        System.out.println();
        for(WeekDays day : WeekDays.values()) { // .values() gives array of all constants
            System.out.println(day + " is day number " + day.ordinal()); // .ordinal() --> position index
        } System.out.println();

        // Example 4: Ordinal and comparison between enum constants
        System.out.println(WeekDays.MONDAY.ordinal());
        System.out.println(WeekDays.WEDNESDAY.ordinal());
        System.out.println(WeekDays.WEDNESDAY.compareTo(WeekDays.SATURDAY));
        System.out.println(WeekDays.SATURDAY.compareTo(WeekDays.WEDNESDAY));

        // Example 5: Using EnumSet
        EnumSet<WeekDays> weekend = EnumSet.of(WeekDays.SATURDAY, WeekDays.SUNDAY); // Creates a fast set of weekend days
        // Note: EnumSet created with 'of' is effectively immutable (fixed elements); sets created with methods like 'allOf' are mutable
        System.out.println("Weekend days using EnumSet:");
        for(WeekDays day : weekend) {
            System.out.println(day);
        }

        // Optional alternative: create EnumSet containing all WeekDays
        EnumSet<WeekDays> allDays = EnumSet.allOf(WeekDays.class); // Includes MONDAY to SUNDAY
        System.out.println("\nAll days using EnumSet.allOf():");
        for(WeekDays day : allDays) {
            System.out.println(day);
        }

        // Example 6: Using EnumMap
        EnumMap<Status, String> statusMessages = new EnumMap<>(Status.class);
        statusMessages.put(Status.PENDING, "Please wait...");
        statusMessages.put(Status.SUCCESS, "Hooray. Success.");
        statusMessages.put(Status.ERROR, "Oops, error occurred.");
        statusMessages.put(Status.NOT_FOUND, "Resource missing.");

        System.out.println("\nMessages from EnumMap:");
        // Iterate over EnumMap entries to get both key (Status) and value (message) efficiently
        for(Map.Entry<Status, String> entry : statusMessages.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Optional alternative: iterate using keySet()
        // Note: This is optional; entrySet() is preferred for efficiency, keySet() is simpler if you only need keys
        for(Status s : statusMessages.keySet()) { // Iterate over keys only
            System.out.println(s + " -> " + statusMessages.get(s));
        }

        // Example 7: Enum implementing an interface
        System.out.println("\nEnum implementing interface:");
        for(Operation op : Operation.values()) {
            System.out.println(op + ": " + op.apply(5, 3));
        }
    }

    // Enum WeekDays --> simple enum without fields
    enum WeekDays {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    // Enum Status --> enum with constructors, fields, and methods
    enum Status {
        PENDING, // default constructor called
        SUCCESS(200, "Operation successful."),
        ERROR(500, "Internal error."),
        NOT_FOUND(404, "Not found."); // Semicolon required after enum constants when followed by fields/methods

        // Extra fields for code + message
        private final int code;
        private final String message;

        // Default constructor (used by PENDING)
        Status() {
            this.code = 0;
            this.message = null;
        }

        // Parameterized constructor (used by SUCCESS, ERROR, NOT_FOUND)
        Status(int code, String message) {
            this.code = code;
            this.message = message;
        }
        public int getCode() {
            return this.code;
        }
        public String getMessage() {
            return this.message;
        }
    }

    // Enum implementing an interface
    interface Calculator {
        int apply(int a, int b);
    }

    enum Operation implements Calculator {
        ADD { // Performs addition
            @Override
            public int apply(int a, int b) {
                return a + b;
            }
        },
        SUBTRACT { // Performs subtraction
            @Override
            public int apply(int a, int b) {
                return a - b;
            }
        },
        MULTIPLY { // Performs multiplication
            @Override
            public int apply(int a, int b) {
                return a * b;
            }
        },
        DIVIDE { // Performs division
            @Override
            public int apply(int a, int b) {
                if(b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };
        // Note: Enums implementing interfaces allow polymorphic behavior.
        // Each constant can have its own implementation of the interface method.
    }
}
