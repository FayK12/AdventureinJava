import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;



/** A human (user) player in the game */

public class Human extends Player{
  
  private static boolean verbose = false; // set true for debugging
  // set false for submitted code
  
  /** Creates a player in the game
    * 
    * @param w is the world that the player lives in
    * @param name is the name of the player
    * @param location is where in the world the player is
    * @param health is the health of the player (which may or may not be relevant in your game)
    * @param things is a list of Thing objects that the player initially possesses
    * @param goal is the Thing that the human player is trying to retrieve in the game
    */
  public Human(World w, String name, Location location, int health,
               List<Thing>  things, Thing goal){
    super(w, name, location, health, things, goal);
  }

 //public Item[] item = new Item();
  
  /** Plays a turn for this player
    * 
    * For computer players, player will have the AI for that player.
    * For human player, query user for input and then react 
    * appropriately for the input.
    */
  Scanner in = new Scanner(System.in);
  
  @Override
  public void play() throws InterruptedException {
    checkGoal();
    //System.out.println();
    if(verbose){System.err.println("The location " + w.getRoom(getLocation()) + " has " + w.getRoom(getLocation()).getPlayers() + " players");}
    if(verbose){System.err.println("The location " + w.getRoom(getLocation()) + " has "+ w.getRoom(getLocation()).getThings() + " things");}
    System.out.println();
    System.out.print("What would you like to do? [type h <enter> for help]  \n");
    String action = in.nextLine();
    
    if( action.toLowerCase().trim().equals("h") ){
      help();
      reduceHealth();
    }else if(action.toLowerCase().trim().equals("l") ){
      look();
      reduceHealth();
    }else if(action.toLowerCase().trim().equals("exit")){
      System.out.print("Coward. Goodbye.");
      in.close();     
      Thread.sleep(1000);
      System.exit(0);
    }else if(action.toLowerCase().trim().equals("take")){
      take();
      reduceHealth();
    }else if(action.toLowerCase().trim().equals("drop") ){
      drop();
      reduceHealth();
    }
    else if(action.toLowerCase().trim().equals("talk") ){
      talk();
      reduceHealth();
    }
    else if(action.toLowerCase().trim().equals("wave") ){
      wave();
      reduceHealth();
    }
    else if(action.toLowerCase().trim().equals("use") ){
      use();
      reduceHealth();
    }
    else if(action.toLowerCase().trim().equals("i")){
      inventory (); 
      reduceHealth();
    } else if(action.trim().charAt(0) == 'g'){
      switch(action.trim().charAt(action.trim().length()-1) ){
        case 'e' : 
          if(verbose){System.err.println(this.getName() + " was in " + w.getRoom(getLocation()) + " coordinates, " + this.getLocation());}
          try{
            this.w.getRoom(this.getLocation()).removePlayer(this);
            this.setLocation( this.getLocation().east() );
            this.w.getRoom(this.getLocation()).addPlayer(this);
            
          }
          catch(RuntimeException e){            
            
            System.out.println("Gandalf has blocked you from leaving the world. YOU. SHALL NOT. PAAASS");
            this.setLocation(w.getOlympus());
            this.w.getRoom(this.getLocation()).addPlayer(this);
            System.out.println("Player was re-set to start in " + this.getLocation());
            return ;
          }
          
          if(verbose){System.err.println(this.getName() + " now in " + w.getRoom(getLocation()) + " coordinates, " + this.getLocation());}
          reduceHealth();
          break;
          
        case 'w' :      
          if(verbose){System.err.println(this.getName() + " was in " + w.getRoom(getLocation()) + " coordinates " + this.getLocation());}
          
          try {
            this.w.getRoom(this.getLocation()).removePlayer(this);
            this.setLocation( this.getLocation().west());
            this.w.getRoom(this.getLocation()).addPlayer(this);
          }
          catch(RuntimeException e){            

            System.out.println("Gandalf has blocked you from leaving the world. YOU. SHALL NOT. PAAASS");
            this.setLocation(w.getOlympus());
            this.w.getRoom(this.getLocation()).addPlayer(this);
            System.out.println("Player is in " + this.getLocation());
            return ;
          }
          
          if(verbose){System.err.println(this.getName() + " now in " + w.getRoom(getLocation()) + " coordinates " + this.getLocation());}
          reduceHealth();
          break;
          
        case 'n' :      
          
          if(verbose){System.err.println(this.getName() + " was in " + w.getRoom(getLocation()) + " coordinates " + this.getLocation());}
          try{
            this.w.getRoom(this.getLocation()).removePlayer(this);
            this.setLocation( this.getLocation().north() );
            this.w.getRoom(this.getLocation()).addPlayer(this);
          }
          
          catch(RuntimeException e){            

            System.out.println("Gandalf has blocked you from leaving the world. YOU. SHALL NOT. PAAASS");
            this.setLocation(w.getOlympus());
            this.w.getRoom(this.getLocation()).addPlayer(this);
            System.out.println("Player is in " + this.getLocation());
            return ;
          }
          
          if(verbose){System.err.println(this.getName() + " now in " + w.getRoom(getLocation()) + " coordinates " + this.getLocation());}
          reduceHealth();
          break;
          
       case 's' :      
         if(verbose){System.err.println(this.getName() + " was in " + w.getRoom(getLocation()) + " coordinates " + this.getLocation());}
         
         try{
           this.w.getRoom(this.getLocation()).removePlayer(this);
           this.setLocation( this.getLocation().south() );
           this.w.getRoom(this.getLocation()).addPlayer(this);
         }
         catch(RuntimeException e){            
           
           System.out.println("Gandalf has blocked you from leaving the world. YOU. SHALL NOT. PAAASS");
           this.setLocation(w.getOlympus());
           this.w.getRoom(this.getLocation()).addPlayer(this);
           System.out.println("Player is in " + this.getLocation());
           return ;
         }
          if(verbose){System.err.println(this.getName() + " now in " + w.getRoom(this.getLocation()) + " coordinates " + this.getLocation());}
          reduceHealth();
          break;
          
        default:
          System.out.println("That's an invalid direction");
      }
      
    }
  }
  
  public void inventory(){
    System.out.println("Your inventory belt has " + this.getThings());
   
  }
  
  public void take(){
    if (w.getRoom(this.getLocation()).getThings().size()==0){
      System.out.println("There's nothing in the room to take.");
    }
    else {
      Item item2= new Item();
      System.out.println("What do you want to take ?");
      System.out.println(w.getRoom(this.getLocation()).getThings());
      String choice = in.nextLine();
      item2.name = choice.trim();
      //item2.location = this.getLocation();
      grab(item2);
    }
  }
  
  public void checkGoal(){
    //System.out.println(w.getRoom(getLocation()).getThings());
    //System.out.println(w.getRoom(getLocation()).toString());
    //System.out.println(goal);
    if ((w.getRoom(getLocation()).toString() == "Florence") && (w.getRoom(getLocation()).getThings().contains
                                                                  (goal))) {
      System.out.println("Chalice is in the room. Congratulations. Zeus is proud. Catch you on Skype.");
      System.exit(0);
    }
    
  }
  
  public void grab(Item pickItem){
    boolean founditem = false;
    
    for (Thing t: w.getRoom(this.getLocation()).getThings()){
      if(pickItem.name.equals(t.name)){
        w.getRoom(this.getLocation()).removeThing(t);
        this.addThing(t);
        founditem = true;
        break;
      }
    }
    if (founditem == false){
      System.out.println("The item " + pickItem + " isn't in the room to pick.");
    }
    inventory();
  }
    
//      Iterator<Thing> itr = w.getRoom(this.getLocation()).getThings().iterator();
//
//      while (itr.hasNext()){
//         if(pickItem.name.equals(t.name)){
//          w.getRoom(this.getLocation()).removeThing(pickItem);
//          this.addThing(pickItem);
//          break;
//        }
    
    // } 
//    }
//    catch (Exception e){
//      System.out.println("There is no item here by that name");
//    }
   
  
  public void drop(){
    //w.getRoom(getLocation()).addThing(things.size());
    if (this.getThings().size()==0){
      System.out.println("Your inventory has nothing you can drop");
    }
    else{
      Item item2= new Item();
      System.out.println(this.getThings());
      System.out.println("What do you want to drop ?");
      String choice = in.nextLine();
      item2.name = choice.trim();
      //item2.location = this.getLocation();
      release(item2);
    }
  }
  
  public void release(Item dropItem){
    boolean founditem = false;
    
    for (Thing t: this.getThings()){
      if(dropItem.name.equals(t.name)){
        this.removeThing(t);
        w.getRoom(this.getLocation()).addThing(t);
        inventory();
        founditem = true;
        break;
      }
    }
    if (founditem == false) {
      System.out.println("The item " + dropItem + " isn't in your inventory to drop");
    }
  }

  @Override
  public void interact(Thing p){
    System.out.println("Your health before using item was " + this.getHealth());
    this.removeThing(p);
    this.setHealth(this.getHealth()+p.value);
    System.out.println("Your health after using item is " + this.getHealth());
    checkDead();
    return;
  }
  
  
  @Override
  public void interact(Player p){
  
//    if (p.name.equals("Batman"))
//    {
      p.interact(this);
      checkDead();
//      System.out.println("You got bat-slapped.");
//      this.setHealth(this.getHealth()-20);
//      System.out.println("You lost 20 health points.");
//      checkDead();
      
    }
   
  
   public void reduceHealth(){
    this.setHealth(this.getHealth()-3);
    checkDead();
  }
  
   public void checkDead(){
     System.out.println("Your health from moving is: " + this.getHealth());
     if (this.getHealth()==5){
       System.out.println("You need to replenish your energy. Find food or use it if you have it");
     }
     else if (this.getHealth()<=0){
       //try{
       System.out.println("You are dead. Goodbye.");
       System.exit(0);
       //} catch (java.lang.InterruptedException) {
       //System.out.println("See you in the Underworld");
       }
     }
   
  
//  public void actions(){
//   System.out.println("What would you like to do (talk, use, wave)");
//    String option = in.nextLine();
//    switch (option) {
//      case "talk": talk(); 
//                   break;
//      //case "fight": fight();
//      case "use": use(); break;
//      case "wave": wave(); break;
//    }
//  }
  
   public void use(){
     if (this.getThings().size()==0){
       System.out.println("Your inventory has nothing you can use");
     }
     else{
       System.out.println("What do you want to use? ");
       System.out.println(this.getThings());
       String useItem = in.nextLine();
       useItem = useItem.trim();
       
       for (Thing t: this.getThings()){
         if (useItem.equals(t.name)){
           if (t instanceof Potion){
             interact(t);
             return;
           }
           else{
             System.out.println("The item " + useItem + " isn't useable");
             return;
           }
         }
       }
       System.out.println("The item " + useItem + " isn't in your inventory");
     }
     return;
   }
    
  
  public void talk(){
    System.out.println("Who would you like to speak to? ");
    System.out.println(w.getRoom(this.getLocation()).getPlayers());
    String talkee = in.nextLine();
    talkee = talkee.trim();
    if (talkee.equals(this.name)){
      System.out.println(this.name + " mumbled incoherently to himself");
      return;
    }
    for (Player p: w.getRoom(this.getLocation()).getPlayers()){
      if (talkee.equals(p.name)){
        System.out.println(this.name + ": Heyy " + p.name);
        interact(p);
        return;
      }
    }
    System.out.println("There's no person by the name " + talkee + "in the room to talk to");
    return;
  }
  
  
  public void wave() {
    System.out.println(w.getRoom(this.getLocation()).getPlayers());
    System.out.println("Who would you like to wave to?");
    String wavee = in.nextLine();
    wavee = wavee.trim();
    if (wavee.equals(this.name)){
      System.out.println(this.name + " slapped himself");
      this.setHealth(this.getHealth() - 5);
      System.out.println(this.name + " lost some health for slapping himself silly");
      return;
    }
    for (Player p: w.getRoom(this.getLocation()).getPlayers()){
      if (wavee.equals(p.name)){
        System.out.println(this.name + " waved to " + p.name);
        interact(p);
        return;
      }
    }
    System.out.println("There's no person by the name " + wavee + "in the room to wave to");  
  }
  
  
  public void look(){
    System.out.println(w.getRoom(getLocation()).look());
  }
  
  public void help(){
    String s = "Your options are:\n ";
    s += "'h' for help \n ";
    s += "'l' to look around the room \n ";
    s += "'g x' to go in direction x (x can be n,e,w,s) \n ";
    s += "'i' to list what you have \n ";
    s += "'talk' to talk to someone \n ";
    s += "'use' to use something in your inventory \n ";
    s += "'wave' to wave to someone \n ";
    s += "'take' to take item from a room \n ";
    s += "'drop' to drop item into a room \n ";
    //s += "'fight' to fight Hades \n ";
    //s += "'save' to save \n ";
    //s += "'load' to load a game \n ";
    s += "'exit' to exit \n ";
    System.out.println(s);
  }
  
}