# SnakesvsBlocks
A JavaFX application for the game - Snakes vs Block.
The objective of this game is to create a chain of balls that has enough power to break through numbered blocks. 
If your chain hits a block that it can't break, you die. 
Rules for the gameplay are mentioned below - 
  1) The snake is a collection of n balls arranged linearly. (n >= 0) 
  2) Snake can move in 3 directions: forward, left, and right. 
  3) The snake always moves forward until it encounters a block. 
  4) Blocks and Walls appear randomly during the gameplay. 
  5) Each block has a value, which represents the points made by the player when the snake eats it. After eating the block the length of the snake decreases by the value of the block 
  6) Blocks may occur independently, or in a chain. Atleast one of the block in a chain should have a value less than the length of the snake. 
  7) Walls may occur in different lengths, but should not divide the screen in two halves at any time. That is, length of the wall should be strictly less than the length of screen. 
  8) Snake should not be able to move across the wall. 
  9) A snake may encounter 4 types of tokens during the gameplay. 
    
   a) Ball: A ball has a value written over it. The length of the snake increases by the value of the ball if it eats the ball. 
   b) Destroy Blocks: Destroy all the blocks present on the screen. Value of each block destroyed is added to the score of the player. 
   c) Shield: A shield lets the player eat any block without decreasing the snake's length. Again, the value of the block eaten by snake is added to the total score of the player. The shield remains with snake for 5 seconds only. 
   d) Magnet: A magnet allows the snake to collect coins which are within a certain distance from the head of the snake. The distance should be chosen by the programmer but should not equal the width of the screen. 
  10) The frequency of ball is greater than the frequency of other 3 tokens. 
  11) Speed of snake in forward direction increases as its length increases 
  12) The game ends when the snake is not able to eat a block completely. 


Note:  
    1) The game is endless and only ends when snake dies. 
    2) The frequency of tokens, bricks, walls is open to programmer's implementation, but should make the game challenging for the player.
