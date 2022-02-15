Nikhil Khanna
nkhanna3@u.rochester.edu


This project is a Java implementation of the web-based game 2048. It implements awt and swing for the purposes of displaying and playing the game. The “Game” class serves as a vehicle to instantiate and play the game, as it includes all of the GUI functionality and keybindings. The “Board” class serves as the mechanism for the game board and its movements and does not involve any graphics. The game can be played with either arrow keys or WASD, and Q and R serve the purposes of quitting and restarting the game, respectively. Movement on the back-end was done via for loops that altered the board, and merging was done by combining tiles in certain instances(I used part of Lab III for these aspects of the game).