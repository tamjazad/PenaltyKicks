# PenaltyKicks
command line based game simulating a standard association football penalty kick shootout. standard 1-player, computer vs. user.

Completely written in Java. The PenaltyShootout class includes the main method, the User/Computer classes mediate user and computer actions, respectively, and the Game class is where the main game is written.

The game includes a virtual coin toss to pick who will choose to kick first or second. 

Shooting/Diving Mechanics:

When the user shoots, he or she can choose to shoot in five areas of the goal. They are: top left, bottom left, center, top right, and bottom right. These choices are the same for the computer.  
When the user dives, he or she can choose to dive in 3 areas of the goal. They are: left, right, or the player has the option to stay central. These choices are the same for the computer.

Scoring/Saving Goals:

Whenever a player shoots, the player has a 20% chance of shooting the ball off target and missing the goal. When the player shoots off target, 50% of the time the player will hit the post and the other 50% of the time the player will miss the goal completely.

Say that the shot is on target.
If the player shot top right or bottom right, and the keeper dove right, the keeper has a 75% chance of saving the goal.
If the player shot top left or bottom left, and the keeper dove left, the keeper has a 75% chance of saving the goal.
If the player shot central, and the keeper stayed central, the keeper has a 95% chance of saving the goal.
If the keeper dives in the wrong direction from the shot, and the shot is on target, the shot will result in a goal.

Scorekeeping and Winning:

ArrayLists are used to keep track of goals and misses for the user and computer. As standard in the professional world of association football, the shootout is a best of 5 shots per team shootout. If the number of shots for each team exceeds 5, then the teams will take turns shooting until one team scores and another team misses in the same turn cycle.


