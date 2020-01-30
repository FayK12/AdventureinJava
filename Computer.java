import java.util.List;
import java.util.Random;

public class Computer extends Player {
  
  /* inherited attributes
 protected World        w;    // world that player lives in
 protected String       name;
 protected Location     location;
 protected int          health = 100;
 protected List<Thing>  things;
 protected Thing        goal;
   * */

 protected Random random = new Random (); 
  
public Computer (World w, String name, Location location, int health, List<Thing>  things, Thing goal)
{
  super(w, name, location, health, things, goal);
  this.health = health;
}


@Override
public void interact (Player p){
  if (this.name.equals("Batman")){
    System.out.println("You got bat-slapped.");
    p.setHealth(p.getHealth()-20);
    System.out.println("You lost 20 health points.");
    System.out.println("Batman vanished.");
//    this.w.getRoom(this.getLocation()).removePlayer(this);
//    this.setLocation(w.getUnderworld());
//    this.w.getRoom(w.getUnderworld()).addPlayer(this);
    move();
  }
}

public void move (){
  int movement = random.nextInt(4);
  //System.out.println(movement);
  switch (movement){
    case 0: 
      try{
      this.w.getRoom(this.getLocation()).removePlayer(this);
      this.setLocation( this.getLocation().east() );
      this.w.getRoom(this.getLocation()).addPlayer(this);
      
    }
      catch(RuntimeException e){             
        System.out.println("Computer tried to go out of bounds.");
        this.setLocation(this.getLocation().west());
        this.w.getRoom(this.getLocation()).addPlayer(this);
        //System.out.println("Computer was reset to " + this.getLocation());
        return ;
      }
      break;
    case 1:
      try{
      this.w.getRoom(this.getLocation()).removePlayer(this);
      this.setLocation( this.getLocation().west() );
      this.w.getRoom(this.getLocation()).addPlayer(this);
      
    }
      catch(RuntimeException e){             
        System.out.println("Computer tried to go out of bounds.");
        this.setLocation(this.getLocation().east());
        this.w.getRoom(this.getLocation()).addPlayer(this);
        //System.out.println("Computer was reset to " + this.getLocation());
        return ;
      }
      
      break;
    case 2:
      try{
      this.w.getRoom(this.getLocation()).removePlayer(this);
      this.setLocation( this.getLocation().north() );
      this.w.getRoom(this.getLocation()).addPlayer(this);
      
    }
      catch(RuntimeException e){             
        System.out.println("Computer tried to go out of bounds.");
        this.setLocation(this.getLocation().south());
        this.w.getRoom(this.getLocation()).addPlayer(this);
        //System.out.println("Computer was reset to " + this.getLocation());
        return ;
      }
      break;
    case 3:
      try{
      this.w.getRoom(this.getLocation()).removePlayer(this);
      this.setLocation( this.getLocation().south() );
      this.w.getRoom(this.getLocation()).addPlayer(this);
      
    }
      catch(RuntimeException e){             
        System.out.println("Computer tried to go out of bounds.");
        this.setLocation(this.getLocation().north());
        this.w.getRoom(this.getLocation()).addPlayer(this);
        //System.out.println("Computer was reset to " + this.getLocation());
        return ;
      }
      break;
  }
}

public void play(){ 
  // allows for some interaction with a player
  move();
  //System.out.println(w.getRoom(getLocation()));
//   // System.out.println(w.getRoom(getLocation()).getPlayers());
//  
//  if (this.name.equals("Thor")){  
//  System.out.println("I am Thor, the storm-weather God of sky and thunder according to Norse mythology. Embrace me!");
//  }
//  else if (this.name.equals("Batman")){
//    System.out.println("I am Batman, the legendary Dark Knight.");
//  }
//  else if (this.name.equals("Hades")){
//    System.out.println("I am Hades, guardian of the Underworld. I kill you.");
//  }
//  else{
//    return;
//  }
 }




}
  