# Project 2048: README

Welcome to the 2048 Game. This project involves implementing the popular sliding tile puzzle game, 2048, in Java. The aim of the game is to combine tiles with the same value to reach the tile with the value of 2048.

## Game Overview
2048 is played on an `m x n` grid (typically `4x4`). The game starts with two tiles placed randomly on the grid. Each tile has a value of 2 (90% probability) or 4 (10% probability). The player can slide the tiles in four directions: up, down, left, or right. When tiles with the same value collide, they merge into a single tile with a doubled value. The objective is to create a tile with the value of 2048.


### Game Rules
- **Grid:** An `m x n` grid where `m` and `n` are usually 4.
- **Tiles:** Each tile is associated with a positive integer value.
- **Starting Condition:** The game begins with two tiles placed randomly on the grid.
- **Turns:** In each turn, the player chooses a direction (up, down, left, right) to slide all tiles.
- **Merging:** Tiles with the same value merge into a single tile with double the value when they collide.

### Tasks and Requirements
1. **Initial Setup:** Create the grid and place two initial tiles.
2. **Sliding Mechanism:** Implement the logic to slide tiles in the chosen direction.
3. **Merging Mechanism:** Ensure tiles merge correctly when they collide.
4. **Game End Conditions:** Determine the win and lose conditions.
   
### Prerequisites
- Java Development Kit (JDK)
- Integrated Development Environment (IDE) such as VSCode

### Setup
1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   ```
2. **Navigate to the project directory:**
   ```sh
   cd project-2048
   ```
3. **Compile and run the game:**
   ```sh
   javac Main.java
   java Main
   ```

## Development

### Command-Line Interface
The project provides a command-line interface to interact with the game. Detailed documentation is provided in the appendices.

### Testing
Ensure thorough testing of the sliding and merging mechanisms, as well as the win and lose conditions.

### Edge Cases
Handle edge cases such as full grid without possible merges, and random placement of new tiles.
