# Contributing to Chronos Engine

Thank you for considering contributing to the Chronos Engine! We welcome contributions from the community to help make this project even better. Please take a moment to review this document for guidelines on how to contribute.

## Getting Started

To start contributing, follow these steps:

1. [Fork the repository on GitHub](../../fork).
2. Clone your forked repository to your local machine.

    ```bash
   git clone https://github.com/imma-Spring/Chronos.git
    ```
   
4. Create a new branch for your changes.

     ```bash
   git checkout -b feature/your_feature
     ```
   
6. Make your changes and test them thoroughly.

## Making Changes

When making changes, keep the following guidelines in mind:

- Follow the [code style and conventions](#code-style-and-conventions).
- Ensure your code is well-documented.
- Write meaningful commit messages.

## Code Style and Conventions

### Indentation and Formatting

- Place opening braces `{` on the same line as the statement or declaration, and place closing braces `}` on their own line.
- Keep lines of code within a reasonable length (typically 80-120 characters).

### Naming Conventions

- Class names should be in CamelCase (e.g., `MyClass`).
- Method names should also be in CamelCase and should start with a lowercase letter (e.g., `calculateValue()`).
- Variable names should be in camelCase (e.g., `myVariable`).
- Constants should be in uppercase with underscores separating words (e.g., `MAX_VALUE`).
- Package names should be in lowercase and follow the reverse domain name convention (e.g., `com.chronos.package`).

### Comments

- Use meaningful comments to explain complex logic, non-obvious behavior, or important decisions (comments are not required).
- Avoid redundant or unnecessary comments that merely restate the code.

### Javadoc Comments

- Use Javadoc comments for classes, methods, and important fields to generate API documentation.
- Include information about parameters, return values, exceptions, and usage examples.

```java
/**
* Calculates and returns the total value.
*
* @return The calculated total value.
*/
public int calculateValue() {
    // Code here
}
```

### Imports

- Organize imports and remove unused ones. Use wildcard imports sparingly.
```java
import java.util.List;
import java.util.ArrayList;
```

### Whitespace

- Use a single space after keywords like if, for, while, etc., and before opening parentheses.
```java
if (condition) {
    // Code here
}
```

### Avoid Magic Numbers

- Use named constants instead of hardcoding values.
```java
private static final int MAX_SIZE = 100;
```

### Error Handling

- Use try-catch blocks for handling exceptions(unless function is meant to throw an error, if so, mark function as `throws`.
- Avoid catching generic exceptions unless necessary.
```java
try {
    // Code that may throw an exception
} catch (SpecificException e) {
    // Handle specific exception
}
```
### Class Structure

- Group related methods and fields together.
- Use access modifiers appropriately (e.g., public, private, protected).
- Mark class as final unless meant to be extended.
```java
public final class MyClass {
    private int myField;

    public void myMethod() {
        // Code here
    }

    private void helperMethod() {
        // Code here
    }
}
```

### Avoid Hardcoding Paths and URLs

- Use constants or configuration files for paths and URLs.

## Testing

Make sure to thoroughly test your changes before submitting a pull request. Include relevant tests in your pull request.

## Submitting a Pull Request

When you are ready to submit your changes, follow these steps:

1. Push your changes to your forked repository.

     ```bash
   git push origin feature/your_feature
     ```
  
3. Create a [pull request](../../compare) from your forked repository to the original repository.

In your pull request, provide a clear description of the changes you have made and any relevant context.

## Reporting Bugs

If you encounter a bug, have a suggestion, or want to discuss a feature, please [open an issue](../../issues) on the GitHub repository.

When reporting an issue, please provide the following information:

- A clear and concise description of the issue or suggestion.
- Steps to reproduce the behavior (if applicable).
- Expected behavior.
- Any relevant screenshots or error messages.

Your contributions through issue reporting are invaluable in helping us improve Chronos Engine. Thank you for taking the time to help make this project better!


## Feature Requests

If you have a feature request, please [open an issue](../../issues) on the GitHub repository to discuss it.

## Code of Conduct

Please note that this project is released with a [Contributor Code of Conduct](CODE_OF_CONDUCT.md). By participating in this project, you agree to abide by its terms.

## License

By contributing to the Chronos Engine, you agree that your contributions will be licensed under the [MIT](LICENSE) file of this project.

---

Thank you for your contributions!

[Chronos Engine](https://github.com/imma-Spring/Chronos)
