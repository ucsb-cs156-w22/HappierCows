# Game Play
(Please edit it this as more features are added so developers can have a sense of the game play and rules behind it)

## Basics
- Admins can view all users that login to the website under the admin/users page. 
- Admins can create, view, edit, and destroy commons
    - As of right now individual Commons represent different lectures in Proffessor Mattanjah's class
    - Commons initail params (name, user startig balance, cow price, milk price and start date, end date, cowsPerUserThreshold)
    - Commons additional variables (userCount)
    - New Commons added to to Commons table 
- Users can login to the application 
    - This creates a new User in the User table 
- Users can join commons
    - When they do this creates a new UserCommons in the UserCommons commons table
        - UserCommons can be thought of as a Users Farms in a Commons, A User can only have one UserCommons/Farm per Commons but they may have multiple UserCommons in multiple Commons
        - UserCommons variables (totalWealth, cowCount, averageCowHealth)
- Users can visit commons they are part of
    - Each user starts the game with their indvidual wealth equal to the commons total wealth
    - Users can buy and sell cows as much as their indvidual wealth allows
        - When cows are bought they have 100 health and this is added to the users averageCowHealth
        - Cows are sold for user averageCowHealth * cowPrice
    - Users cows will be milked daily and they will recieve profit based on how many cows they have and how healthy they are

## Scheduled Events
- At certain times during the day each active commons will fire off certain events that will help to facilite game play
    - Every day at 11:40 every users cows will be milked and they will recieve a profit added to their individual total wealth
        - Individual wealth += milkPrice * cows * cowHealth
    - Every 30 minutes the health of all cows in a commons will be increased or decreased depending on if the commons cows threshold is passed
        - For each user averageCowHeath += (.01(userCount*cowsPerUserThreshold)-totalCows))
    - Every day at 11:30 cows will be killed if a users averageCowHealth is less than .2
        - For each user if cowsHealth < .2 then 1 in 100 chance a cow in that users commons will die
