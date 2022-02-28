# Notes on game development 2/28/22

## Topics to cover
* Show what's implmented and what is being implmented this quarter
* Need to add scheduled actions and report system
* Discuss scheduled actions and overall game mechanics

## Comments 
* buy cow: average cow health = ((number of cows before buy) * (average cow health before buy) +  (commons health))/(number of cows after buy)
* sell cow: sell price = (the user's commons average cow health) * some varible linked to commons health * (cows market price)
* change start date to end date (more useful) 
  * use end date to invalidate commons and not run anymore scripts on it
* cow commons threshold should be some # per user
  * some # should be editable, cowThresholdperPlayer 
* cowHealthDeathThreshold variable to set when cows die

## Todos
### Before Spring
* ensure students finish hooku=ing up play page and adding commons manage page
* change start date to end date (more useful) 
  * use end date to invalidate commons and not run anymore scripts on it
* add schuduled action as outlined above and in gamePlay.md 
* setup report table
### Final Goal
* cow commons threshold should some # per user
  * some # should be editable, cowThresholdperPlayer 
* cowHealthDeathThreshold variable set per commons and used to determine if a random common in your commons should die
  * later implmentations will use the threshold on each cow and if the cows health is under it then it has a random chance of dying
