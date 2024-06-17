# Two-Player Snake Game


## Introduction
This project is a two-player snake game developed using the LibGDX framework. The game includes features such as movement, collision detection, snake growth upon eating fruits, and speed increase over time. The four main classes that make up the game are `Snakegame`, `Snake`, `SnakePlayerTwo`, and `Fruit`.

## LibGDX Framework Installation
To run this project, you need to have the LibGDX framework installed. Follow the steps below to set up the environment:

1. **Install the Java Development Kit (JDK)**:
   - Ensure that the JDK is installed on your system. You can download it from the official Oracle website.

2. **Download and Set Up LibGDX**:
   - Visit the [official LibGDX website](https://libgdx.com/).
   - Use the LibGDX setup tool to generate a basic project. This will download all necessary dependencies and create the basic project structure.
   - After the initial setup, add the provided classes (`Snakegame`, `Snake`, `SnakePlayerTwo`, and `Fruit`) to your project.

## Class Descriptions

### Snakegame
- This is the main class of the game, extending LibGDX's `ApplicationAdapter`.
- Responsible for the initial game setup (`create` method), updating and rendering the game state each frame (`render` method), and cleaning up resources (`dispose` method).
- Manages the two players (`Snake` and `SnakePlayerTwo`), the fruit (`Fruit`), and controls the collision logic and win conditions.

### Snake
- Represents a player's snake.
- Contains methods for updating (`update`), rendering (`render`), growing (`grow`), increasing speed (`increaseSpeed`), and checking for collisions with other snakes (`checkHeadCollisionWithOtherSnake`).
- Manages the snake's movement based on a list of body segments.

### SnakePlayerTwo
- Subclass of `Snake` that adds specific controls for the second player.
- Overrides the `handleInput` method to map control keys (`W`, `A`, `S`, `D`).
- Renders the second player's snake in a different color to distinguish between players.

### Fruit
- Represents the fruit that the snakes must eat to grow.
- Contains methods for rendering (`render`), checking if it has been eaten (`isEaten`), and repositioning (`reposition`).
- Plays a sound when eaten for auditory feedback.

## Important Points
- **Speed Increases**: The game increases the speed of the snakes at each defined time interval (`SPEED_INCREMENT_INTERVAL`).
- **Collisions**: The game ends if a snake collides with the other. There are specific win messages for each collision scenario.
- **Sound and Music**: The game includes background music and sound effects for a more immersive experience.
- **Resizing**: The `Snakegame` class adapts the size of game elements when the window is resized.

## Final Considerations
This project demonstrates the effective use of the LibGDX framework to create an interactive and engaging game. The modular structure and use of inheritance facilitate code maintenance and extension. For future improvements, features such as a scoreboard, difficulty levels, and support for more players could be added.

---

This report provides an overview of the code and functionalities of the two-player snake game. To run the project, follow the LibGDX installation instructions and add the provided classes to your generated project.
