
# 🧮 Arithmetic Expressions in Java

**Arithmetic Expressions** is a Java-based project that enables the evaluation, simplification, and differentiation of arithmetic expressions. It supports trigonometric functions like sine and cosine, and allows for variable assignments and derivative calculations.

---

## ✨ Features
- Supports arithmetic operations: addition, subtraction, multiplication, division, exponentiation.
- Handles trigonometric functions like sine and cosine.
- Can assign values to variables and compute derivatives.
- Includes unit tests for validation of functionality.

---

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher.

### Cloning the Repository
1. Open your terminal.
2. Run the following command to clone the repository:
   ```bash
   git clone https://github.com/username/repository.git
   cd repository
   ```

---

## 🏗️ How to Run

### Compile the Program
1. Navigate to the project folder:
   ```bash
   cd <repository-folder>
   ```
2. Compile the program:
   ```bash
   javac -d bin src/*.java
   ```

### Execute the Program
Run the program by using the following commands:
```bash
java -cp bin ExpressionsTest
```

---

## 📂 Example Usage

### Input
```java
Expression sinExpr = new Sin(new Var("x"));
System.out.println(sinExpr.evaluate(Map.of("x", Math.PI / 2))); // Should print 1.0
```

### Output
```plaintext
1.0
```

---

## 🧪 Example Code Execution
```java
Expression expression = new Sin(new Var("x"));
System.out.println(expression.evaluate(Map.of("x", Math.PI / 2)));  // Expected output: 1.0
```

### Output:
```plaintext
1.0
```

---

## 🧑‍💻 Project Structure

- **`Expression.java`**: Interface defining core methods for expressions.
- **`BaseExpression.java`**: Abstract class that implements basic expression functionality.
- **`UnaryExpression.java`**: Abstract class for unary expressions (e.g., sine, cosine).
- **`BinaryExpression.java`**: Abstract class for binary expressions (e.g., addition, subtraction).
- **`Var.java`**: Class for representing variables.
- **`Sin.java`**: Class for representing the sine function.
- **`Cos.java`**: Class for representing the cosine function.
- **`Num.java`**: Class for representing numerical values.
- **`ExpressionsTest.java`**: Test cases for validating expressions.

---

## 📜 Derivative and Simplification Logic

- **Derivative**: Differentiation for trigonometric functions such as `sin(x)` and `cos(x)` uses standard calculus rules.
- **Simplification**: Trigonometric and arithmetic expressions are simplified where possible.

---
