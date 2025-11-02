# Enum

**Enum** (short for enumeration) is a special Java type that represents a **fixed set of constants**. Unlike integers or strings (which can be misused), enums provide **type-safety, readability, and built-in methods**.

---

## Why Use Enum?

- Avoids “magic numbers” or raw strings, making code easier to maintain.
- Provides **compile-time checking** (e.g., a `switch` must cover all enum values).
- Each enum constant is a **singleton object** → memory-safe and consistent. Constants are **created when the enum class is loaded**, ensuring memory safety and consistent behavior.
- Can contain **fields, methods, and constructors**, making them more powerful than in many other languages.
- Works seamlessly with collections like `EnumSet` and `EnumMap` (optimized for enums).
- Safer and more readable in large codebases where states or statuses are fixed.

---

## When to Use Enum

- When you have a **predefined, finite set of values** (e.g., days of the week, status codes, directions, roles).
- When values represent **categories or states that don’t change during runtime**.
- When you need **both constant names and extra metadata** (e.g., HTTP code + message).

---

## How Enums Work Internally

- Each enum constant is a `public static final` instance of the enum class.
- Methods available:
  - `.name()` → returns the enum constant name
  - `.ordinal()` → returns the position in the enum declaration (starting from 0). **Use it cautiously**, relying on ordinals for logic is discouraged.
  - `.values()` → returns an array of all enum constants
- Enums **cannot be extended** (implicitly final), but **can implement interfaces**.
- Enums are **type-safe**: you cannot assign unrelated values to them.

---

## Other Useful Enum Features

### 1. EnumSet
- High-performance `Set` implementation specifically for enums.
- Faster and more memory-efficient than `HashSet`.
- Internally uses a **bit vector** for fast operations.
- Maintains **natural order** (order of declaration in the enum, by `.ordinal()`).
- Can use `EnumSet.range(FIRST, LAST)` to create sets of consecutive constants.
- Example:
```java
EnumSet<WeekDays> weekend = EnumSet.of(WeekDays.SATURDAY, WeekDays.SUNDAY);
```

### 2. EnumMap
- Specialized `Map` with enum keys.
- Faster and smaller than `HashMap` when keys are enums.
- Example:
```java
EnumMap<Status, String> messages = new EnumMap<>(Status.class);
messages.put(Status.SUCCESS, "All good.");
messages.put(Status.ERROR, "Something went wrong.");
```

---

## Key Benefits and Takeaways

- Enums are **more than constants**: they can implement behavior and act almost like classes.
- **Type-safety:** prevents assigning invalid values.
- **Clarity:** self-documenting code instead of magic numbers or raw strings.
- **Extensible:** can hold fields, codes, messages, and methods (cannot be subclassed).
- **Optimized for collections:** `EnumSet` (fast set operations), `EnumMap` (map keys as enums).
- Use `.values()`, `.ordinal()`, `.valueOf()` for reflection or introspection.

---

## Real-World Examples

- **OrderStatus** in e-commerce: `NEW`, `PROCESSING`, `SHIPPED`, `DELIVERED`, `CANCELED`.
- **HTTP Status codes:** `OK`, `NOT_FOUND`, `ERROR`.
- **WeekDays** or **Directions** (`NORTH`, `SOUTH`, `EAST`, `WEST`).

**Note:** Enums should **not be overused**. Only use them when the set of constants is truly finite and stable.

