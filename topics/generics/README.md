# Generics

Generics in Java provide parameterized types, allowing us to write code that works with different data types using a single class, interface, or method.

Instead of creating separate versions for each type, we use type parameters like `<T>` to make the code reusable and type-safe.

---

## What Are Generics?

**Generics:** parameterized types

They allow you to define methods, classes, or interfaces that can operate on any type while maintaining compile-time type checking.

Example:
```java
<T> void printList(List<T> list)
```

- Works with any type (`String`, `Integer`, `Person`, etc.)
- `T` is a type parameter, resolved at compile time.

---

## Inheritance Chain

In Java, the inheritance chain for Integer is:

```text
Object ← Number ← Integer
```

`List<? super Integer>` means the list can hold `Integer` or any of its supertypes. When reading, elements are treated as `Object`, because the compiler only knows it’s some supertype of `Integer`.

- **Example valid lists:**
  - `List<Integer>`
  - `List<Number>`
  - `List<Object>`

- When **reading** from such lists, elements are treated as `Object` (safest).
- When **adding**, you can safely add `Integer` (or its subclasses).

---

## Bounded Generics — `<T extends SomeType>`

Sometimes we want to restrict the type parameter.

```java
<T extends Number> void process(List<T> list)
```

Valid: `List<Integer>`, `List<Double>`, `List<Float>`
Invalid: `List<String>`

- `T` can only be `Number` or its subclasses.
- Useful when performing numeric operations or methods that depend on certain behavior.

---

## Wildcards — `?`

Sometimes, we don’t care about the exact type parameter.

Example:
```java
List<?> items = new ArrayList<Integer>();
```

- `List<?>` means a list of **some unknown type**.
- You can **read** elements as `Object`.
- You **cannot add** any elements except `null`, because the exact type is unknown.

---

## Upper Bounded Wildcards — `? extends T`

Used when the collection **produces values for reading**.

```java
List<? extends Number> numbers
```

Accepts: `List<Integer>`, `List<Double>`, `List<Float>`
- Good for **reading values** safely.
- Cannot **add any items** except `null` because the exact subtype is unknown.

Rule: **Producer Extends** → if it produces values for you, use `extends`.

---

## Lower Bounded Wildcards — `? super T`

Used when the collection **consumes values for writing**.

```java
List<? super Integer> ints
```

Accepts: `List<Integer>`, `List<Number>`, `List<Object>`
- Good for adding `Integer` values safely.
- When reading, elements are only guaranteed to be of type `Object` because the exact supertype is unknown.

Rule: **Consumer Super** → if it consumes values you put in, use `super`.

---

## Summary Table

| Usage | Syntax | Meaning | Safe for |
|:------|:--------|:---------|:----------|
| Generic Type | `<T>` | Generic method/class | Both read & write |
| Bounded Type | `<T extends X>` | Restrict to a family of types | When specific behavior is needed |
| Wildcard | `<?>` | Unknown type | Read only |
| Upper Bound | `<? extends X>` | Any subclass of X | Producer (read) |
| Lower Bound | `<? super X>` | Any superclass of X | Consumer (write) |

---

## Quick Recap

- Generics = code reusability + type safety.
- `<T extends X>` = restrict type to a **specific family of types (subtypes of X)**.
- `?` = unknown type.
- `? extends X` = good for reading (Producer).
- `? super X` = good for writing (Consumer).
- **PECS Rule:** Producer Extends, Consumer Super.

