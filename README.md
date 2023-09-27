# Chronos
## A 2D game engine in Java
![GitHub contributors](https://img.shields.io/github/contributors/imma-Spring/Chronos)
[![GitHub stars](https://img.shields.io/github/stars/imma-Spring/Chronos)](https://github.com/imma-Spring/Chronos/stargazers)
[![GitHub issues](https://img.shields.io/github/issues/imma-Spring/Chronos)](https://github.com/imma-Spring/Chronos/issues)
![GitHub closed issues](https://img.shields.io/github/issues-closed/imma-Spring/Chronos)
![GitHub pull requests](https://img.shields.io/github/issues-pr-raw/imma-Spring/Chronos)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/imma-Spring/Chronos)
[![GitHub license](https://img.shields.io/github/license/imma-Spring/Chronos)](https://github.com/imma-Spring/Chronos)

The **Chronos Engine** is a Java-based game engine designed to provide a framework for creating 2D games. It offers functionalities for managing game objects, handling input, and rendering graphics. This engine is designed to be versatile and customizable, allowing developers to create a wide range of games.

## Features

- **Game Loop**: The engine implements a game loop that handles updating game logic and rendering frames.

- **Input Handling**: Provides an input system for capturing user interactions.

- **Sprite Rendering**: Supports rendering of sprites onto the screen.

- **Collision Detection**: Implements basic collision detection between game objects.

- **Flexible Body Management**: Allows for the addition, removal, and updating of game objects (bodies).

## Getting started

1. Download the zip file
2. Unzip the download
3. Include it in you project
4. Create a class and make it extend Chronos 

## Using the KinematicBody Class

The `KinematicBody` class is a specialized type of `Body` designed for game objects with predefined motion that do not respond to external forces. It inherits properties and methods from the `Body` class and provides specific behavior for certain types of game objects.
```java    
    public class MovingPlatform extends KinematicBody {

        private float speed;
        private boolean directionRight;

        public MovingPlatform(Sprite sprite, Vector3<Float> position, String name, float speed) {
            super(sprite, position, name);
            this.speed = speed;
            this.directionRight = true;
        }

        @Override
        public void start() {
            // Initialization logic for the moving platform
        }

        @Override
        public void update(float dt) {
            // Update logic for moving platform
            if (directionRight) {
                position.x += speed * dt;
            } else {
                position.x -= speed * dt;
            }
        }
    }
```
## Using the StaticBody Class
The `StaticBody` class represents game objects that do not have predefined motion and remain stationary in the game world. It provides specific behavior for static game objects.
```java  
    public class Obstacle extends StaticBody {

        public Obstacle(Sprite sprite, Vector3<Float> position, String name) {
            super(sprite, position, name);
        }

        @Override
        public void start() {
            // Initialization logic for the obstacle
        }

        @Override
        public void update(float dt) {
            // No update logic for a static obstacle
        }
    }
```
## Using the Signal Class
The `Signal` class provides a simple messaging system that allows different parts of the game to communicate and exchange information.
```java  
    // Sending a signal
    Signal.sendSignal("player_hit", player, damage);

    // Retrieving a signal
    Optional<List<Object[]>> hitSignal = Signal.getSignal("player_hit");
    if (hitSignal.isPresent()) {
        List<Object[]> packetsList = hitSignal.get();
        for (Object[] packets : packetsList) {
            // Process packets
        }
    }
```
## Contributing
Feel free to contribute to the Chronos Engine by reporting issues, suggesting improvements, or submitting pull requests.

### Bugs
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


### Feature Requests
Is your feature request related to a problem? Please describe. A clear and concise description of what the problem is. Ex. I'm always frustrated when [...]

Describe the solution you'd like A clear and concise description of what you want to happen.

Describe alternatives you've considered A clear and concise description of any alternative solutions or features you've considered.

Additional context Add any other context or screenshots about the feature request here.
