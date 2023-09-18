# Chronos
## A 2D game engine in Java

The Chronos Engine is a Java-based game engine designed to provide a framework for creating 2D games. It offers functionalities for managing game objects, handling input, and rendering graphics. This engine is designed to be versatile and customizable, allowing developers to create a wide range of games.

## Features
Game Loop: The engine implements a game loop that handles updating game logic and rendering frames.

Input Handling: Provides an input system for capturing user interactions.

Sprite Rendering: Supports rendering of sprites onto the screen.

Collision Detection: Implements basic collision detection between game objects.

Flexible Body Management: Allows for the addition, removal, and updating of game objects (bodies).

## Getting Started
To start using the Chronos Engine in your project, follow these steps:

Import the Engine: Include the com.Chronos.engine package in your project.

Extend the Chronos Class: Create a class that extends Chronos and implement the addBodies and update methods to define your game logic.

Initialize the Game: Instantiate your custom class, providing necessary parameters such as window dimensions, scaling factor, and background color.

    public class MyGame extends Chronos {
        // Implement the required methods
        // ...
    
        public static void main(String[] args) {
            new MyGame("My Awesome Game", 800, 600, 2, 0xFF000000);
        }
    
        @Override
        public void addBodies() {
            // Add game objects here
        }

        @Override
        public void update() {
            // Define game logic here
        }
    }

Add Game Objects: Within the addBodies method, add instances of your custom Body classes.

Implement Game Logic: In the update method, define the behavior and interactions of your game objects.

Compile and Run: Build your project and run the main class.

## Usage
Adding Bodies: Use the addBody method to add game objects to the engine.

Updating Game Logic: Implement the update method to define the behavior of game objects.

Collision Handling: Override the collision-related methods (onCollisionEnter, onCollisionExit, onCollision) to handle collisions.

Input Handling: Access user input through the input object.

Rendering: Customize rendering by providing your own sprites and backgrounds.

## Contributing
Feel free to contribute to the Chronos Engine by reporting issues, suggesting improvements, or submitting pull requests.

## License
This project is licensed under the MIT License.

## Bugs
**Describe the bug**
A clear and concise description of what the bug is.

**To Reproduce**
Steps to reproduce the behavior:
1. Go to '...'
2. Click on '....'
3. Scroll down to '....'
4. See error

**Expected behavior**
A clear and concise description of what you expected to happen.


## Feature Requests
Is your feature request related to a problem? Please describe. A clear and concise description of what the problem is. Ex. I'm always frustrated when [...]

Describe the solution you'd like A clear and concise description of what you want to happen.

Describe alternatives you've considered A clear and concise description of any alternative solutions or features you've considered.

Additional context Add any other context or screenshots about the feature request here.
