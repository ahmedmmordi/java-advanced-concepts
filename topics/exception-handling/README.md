# Exception Handling

Exception Handling in Java is a mechanism to manage runtime errors, ensuring that the program's flow continues without crashing.  

---

## What is an Exception?

An **exception** is an unexpected event that disrupts the normal flow of a program.  
If the program or JVM has no way to handle it, the program will crash. Handling exceptions allows programs to **catch errors and continue execution**.

**Difference Between Error & Exception:**

| Type  | Description | Example |
|-------|------------|---------|
| Error | Serious issues related to the JVM or system. Cannot be handled easily. | OutOfMemoryError, StackOverflowError |
| Exception | Issues that can be handled in the program. | IOException, NullPointerException |

---

## When Do Exceptions Occur?

- **Runtime errors** – divide by zero, null object access, invalid array index.  
- **External issues** – file not found, network down, database unavailable.  
- **Bad user input** – invalid number, wrong format.  
- **Programmer mistakes** – wrong logic, missing resources.

---

## Why Use Exception Handling?

- Prevent program crashes from unexpected issues.  
- Separate error-handling code from normal code.  
- Recover gracefully instead of terminating the program.  
- Provide meaningful error messages.  
- Improve code readability and reliability.

---

## Simple Example

Without handling:

```java
int a = 10, b = 0;
int result = a / b;   // ArithmeticException: / by zero
System.out.println("Will never reach here");
```
---

## Exception Hierarchy

All exceptions and errors are subclasses of **Throwable**.

![Exception Hierarchy](https://media.geeksforgeeks.org/wp-content/uploads/20251008124212466472/object.webp)  
*Image source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/exceptions-in-java)*

---

## Types of Java Exceptions

![Types of Exceptions](https://media.geeksforgeeks.org/wp-content/uploads/20240730174225/Exceptions-in-Java-1-768.webp)  
*Image source: [GeeksforGeeks](https://www.geeksforgeeks.org/java/exceptions-in-java)*

### 1. Checked Exceptions
- Compiler **forces** you to handle these.  
- Extend `Exception` (but not `RuntimeException`).  
- Use `try-catch` or declare with `throws`.  
- **Examples:** IOException, SQLException, ClassNotFoundException  
- **Use case:** Recoverable problems (e.g., missing file).

### 2. Unchecked Exceptions
- Compiler **does not force** you to handle these.  
- Extend `RuntimeException`.  
- **Examples:** NullPointerException, ArithmeticException, IndexOutOfBoundsException  
- **Use case:** Programming errors or bugs (e.g., passing null to a method).

### 3. Custom Exceptions

- Create by extending `Exception` (checked) or `RuntimeException` (unchecked).  
- Useful to give meaningful names for business logic errors and improve code clarity.  

---

## Exception Handling Keywords

| Keyword | Purpose |
|---------|--------|
| try     | Block to test for exceptions |
| catch   | Block to handle exceptions |
| finally | Block that always executes (cleanup resources) |
| throw   | Explicitly throw an exception |
| throws  | Declare exceptions a method may throw |

**Throw vs Throws**

- **throw** → used to **throw** an exception.  
- **throws** → used to **declare** exceptions in a method signature.  

---

## Nested try-catch

- Used when multiple risky operations require independent handling.  
- Avoid overusing to prevent messy code.

---

## Stack Trace

- Shows the **sequence of method calls** when an exception occurred.  
- Includes exception type, message, class, method, and line number.  
- Use `printStackTrace()` for quick debugging; in production, use a logging framework.

---

## Logging

- Use **Logger** (`java.util.logging.Logger`, Log4j, SLF4J) instead of `System.out.println` or `printStackTrace()` in production.  
- Benefits: configurable levels, timestamps, file output, and less console spam.  
- Common levels: SEVERE, WARNING, INFO, DEBUG.

---

## Key Takeaways

- Exception handling separates normal code from error-handling code.  
- Checked exceptions = recoverable; unchecked exceptions = programming errors.  
- Use custom exceptions to model domain-specific errors clearly.  
- Logging is crucial for production-ready applications.

---

## Best Practices

- Catch **specific exceptions** first; avoid generic `Exception` unless necessary.  
- Do not swallow exceptions; always log or rethrow.  
- Prefer **try-with-resources** for managing resources.  
- Use meaningful **custom exception classes** for domain-specific errors.  
- Keep exception messages clear and descriptive.  
- Use **nested try-catch** only when necessary.  
- Avoid catching `Error` (e.g., OutOfMemoryError); fix root cause instead.  
- Development: `printStackTrace()` is fine; Production: use **Logger**.

