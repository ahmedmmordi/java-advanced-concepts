package org.example;

import java.util.List;

public class Generics {
    public static void main(String[] args) {
        // getItems1 --> works with ANY type
        getItems1(List.of('A', 'B'));
        getItems1(List.of("One", "Two", "Three"));
        getItems1(List.of(123, 732, 523, 234, 854));
        System.out.println();

        // getItems2 --> returns the same list of ANY type
        List<String> words = getItems2(List.of("Hello", "World"));
        System.out.println(words);

        List<Double> doubles = getItems2(List.of(1.1, 2.2, 3.3));
        System.out.println(doubles);
        System.out.println();

        // getItems3 --> only accepts subclasses of Number
        List<Integer> nums = getItems3(List.of(10, 20, 30));
        System.out.println(nums);

        List<Float> floats = getItems3(List.of(1.5f, 2.5f, 3.5f));
        System.out.println(floats);
        System.out.println();

        // getItems4 --> works with ANY list type (unknown)
        getItems4(List.of("Apple", "Banana", "Orange"));
        getItems4(List.of(100, 200, 300));
        System.out.println();

        // getItems5 --> works with Number or its subclasses
        getItems5(List.of(1, 2, 3, 4, 5));             // Integers
        getItems5(List.of(2.5, 3.5, 4.5));             // Doubles
        getItems5(List.of(1.1f, 2.2f, 3.3f));          // Floats
        System.out.println();

        // getItems6 --> accepts Integer, Number, or Object
        getItems6(List.of(1, 2, 3));                   // List<Integer>
        getItems6(List.of(10, 20.5, 30.7f));           // List<Number>
        getItems6(List.of("Text", 99, 3.14, true));    // List<Object>
    }

    // Generic method with <T>. Works with ANY type.
    public static <T> void getItems1(List<T> items) {
        for(T x : items) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    // Generic method that RETURNS a list of type T.
    public static <T> List<T> getItems2(List<T> items) {
        return items;
    }

    // Generic method where T must be a subtype of Number (int, double, float, ...).
    public static <T extends Number> List<T> getItems3(List<T> items) {
        return items;
    }

    // Accepts a list of ANY type (unknown). Can only be read safely as Object.
    public static void getItems4(List<?> items) {
        for (Object x : items) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    // Accepts a list of Number or its subclasses (Integer, Double, ...).
    public static void getItems5(List<? extends Number> items) {
        for (Number x : items) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    // Accepts a list whose element type is Integer or any of its superclasses (Number, Object).
    public static void getItems6(List<? super Integer> items) {
        for (Object x : items) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
