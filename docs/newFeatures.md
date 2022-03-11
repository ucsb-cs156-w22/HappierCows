# New Features and MVP requirements

## MVP requirements
This is a list of tasks that need to be done to finish the MVP of HappierCows.
  - [ ] Pick and choose best HappierCows code from W22 dev teams projects
    - https://github.com/ucsb-cs156-w22/team04-w22-5pm-HappyCows
    - https://github.com/ucsb-cs156-w22/team04-w22-6pm-HappyCows
    - https://github.com/ucsb-cs156-w22/team04-w22-7pm-HappyCows
  - [ ] Make sure create commons initializes database correctly and add all initial commons parameters to the form(cowPerUserThreshold and endDate)
  - [ ] Make sure when user joins a commons userCommons and Commons are updated correctly
  - [ ] Add jobs backend calls and test them
    - Branch already started on this jobsBackend
  - [ ] Add job to generate daily report for commons and create commons report table 
    - Report averageCowHealth and cowCount
  - [ ] Setup very basic page to display reports accessible from manage commons page
  - [ ] Make sure full or mostly full coverage on all frontend and backend testing including mutation testing

## Tentative Features
These features can be implemented right away but please read the descriptions for each and consider the side effects/problems the feature produces.
  - [ ] Be able to unjoin a commons
    - If someone accidentally joins a commons then they should be able to unjoin
    - At the start of the game this action would do nothing to the commons. But if a User plays in a commons and then leaves this could affect the commons health and how the game was played. Is this a problem or would it be so rare and so minimal that we can add this feature without it harming game play? 
  - [ ] Sell cows for combination of users average cow health and commons average cow health
    - The formula could be something like this where user cow health is weighted 2 to 1 over commons cow health
      - ((2 * userAverageCowhealth +  CommonsAverageCowHealth)/3 ) * CowPrice
	 - Will not want to add this if just switching to individual cows because individual cows with just sell for their health times cowPrice
  - [ ] Buy cows at average commons health instead of 100
    - If we are using avergeCowHealth for each user then the problem should not occur
    - If there are a ton of cows then it will take a long time to get all the cows health and take an average
    - Test how long this takes on large data set before merging this feature to main
    - Possible solutions:
      - Scale down the game (to do this make the initial total wealth lower or raise cow price or lower milk price) so that each user has less cows
      - Keep an average cow health for each user on their UserCommons and use that to calculate average cow health

## New Features
This is a list of new features and the order I think they should be implemented in. Some features can be split up into many issues and some may be whole epics. Feel free to add or edit the list as developers see fit. 
### Report system
Here are some ideas for the report system. Before we implement these features it might be a good idea to run them by Mattanjah to see if he has any additions or things he thinks are not that useful. 
  - [ ] Add user profit table and database
    - To start it could just have game day, profit and total wealth
    - Create database and backend entities and controllers
    - Add report to the profits table on the Play page
  - [ ] Add job to create daily report for each user
  - [ ] Add user reports to report table page and make commons report table better
    - Have report page have selection on the table to choose which report to display common report or user reports for that commons
  - [ ] Add columns to profit table for cows died, cows sold, cows bought, average cow health, cow count
  - [ ] Add graph to report page above table
    - If on Commons graph shows averageCowHealth and cowCount over time
    - If on User reports instead of a graph it could be a leader board of users with highest cowCount or totalWealth or averageCowHealth

### Represent Individual Cows
This is how the original game was implemented but it caused problems with the efficiency of the scheduled jobs. For this reason we should run performance tests on this feature with large test databases(largest reasonable size for real world classes). If problems occur a few possible solutions:
  - Scale down the game (to do this make the initial total wealth lower or raise cow price or lower milk price) so that each user has less cows
  - Only update cows when user goes to play page
    - For each user keep track of last update and average cow health
	 - Jobs now adds row to table of health update values
	 - Update all cows before kill and report
	
  Implementing the Feature:
  - [ ] Create Cow table and create a new cow every time someone buys one
  - [ ] Have sell button delete cows and sell cow for its individual health time cowPrice
  - [ ] Have job update cow health for each individual cow 
  - [ ] Change kill job to only happen once a day and maybe tweak killing rate to make sure not too many cows are dying
  - [ ] Add cows table to Play Page to display each cow so user can see individual health and can choose which one to sell
	  - Add more personalization with individual cow names
	    - This feature is great if users have small numbers of cows but it can be distracting to the learning element of the game if there are too many cows per user   
	    - Possibly auto generate names
  - [ ] Buy cows at full health for more money or buy a cow that was sold at a lower health for cheaper
  
 ### Game Testing and tweaking game variables
 This section goes into how students could test the game by playing it and simulating days in quicker trial runs. They could work closely with Mattanjah to try and make the game play better and improve the learning experience. 
  - [ ] Run small samples of the game and simulate days in shorter periods of times by using swagger to fire off jobs
    - Tests should look for bugs in code and game play
    - Test what happens when scheduled jobs overlap
      - What happens if we run kill cows at the same time or close to update health
	   - What happens if someone buys a cow during update health or when report is being generated
  - [ ] For game play specifically test how different commons initial variables (user starting balance, cow price, milk price, cowsPerUserThreshold) affect game play and database size
    - This is not something we will set but it is something we can document in the docs or readme on how admins can run the game 
    - We will also work with Mattanjah as he will be testing this as well with his own class and we can relay our findings to him
  - [ ] Use statistical modeling and live testing to fine tune variables in scheduled jobs. Variables listed below:
    - The health threshold for a cow possibly dying is .2 right now but there may be a better number depending on cow population size maybe
    - The health change factor is .01. Could be linked too cow population size 
    - The sell price has a depreciation factor or it may have a weighted factor that depends on the user and commons average cow health
