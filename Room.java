import java.util.List;
import java.util.Iterator;

public class Room{
  protected String         description;
  protected Location       location;
  protected List<Location> adjacent;
  protected List<Player>   people;
  protected List<Thing>    things;
 
  
  public Room(String description, Location location, List<Location> adjacent, 
              List<Player> people, List<Thing> things)
  {
    this.description = description;
    this.location = location;
    this.adjacent = adjacent;
    this.people = people;
    this.things = things;
  }
  
  /* getters */

  public Location       getLocation(){ return location; }
  public List<Location> getAdjacentRooms(){ return adjacent; }
  public List<Player>   getPlayers(){ return people; }
  public List<Thing>    getThings(){ return things; }
  
  
  public String look(){
    // return a string describing the room 
    // (what is in it, what exits you have, etc)
    
    String roomStuff = ("You are in: "+ location + "\nYou can go to rooms: " + adjacent +
                        "\nThe people in this room are: " + people + "\nThe items in the room are: " + things).toString();
    return roomStuff;
  }
  
  
  public void addPlayer(Player p){
    this.people.add(p);
  }
  
  public void removePlayer(Player p){
    this.people.remove(p);
  }
  
  /** add a thing t to the current room */
  public void addThing(Thing t){
    this.things.add(t);
  }
  
  /** remove a thing t to the current room */
  public void removeThing(Thing t){
    this.things.remove(t);
  }
  
//  public boolean checkThing(Thing t){
//    while (listOfThings.hasNext()){
//      t.equals(o);
//    }
//    return false;
//  }
  
  /** Getter for a room's thing */
 //public Thing  getThing(int index){ return this.things.get(index); }
  
  
  @Override
  public String toString(){
    return description;
  }
}