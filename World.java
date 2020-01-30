import java.io.*;
import java.util.ArrayList;

public class World{
  protected Room[][] rooms;
  protected Location olympus;
  protected Location underworld;
  protected Location gotham;
  protected Location mordor;
  protected Location florence;
  protected Location azgard;
  protected Thing    goal;
  protected Thing    shawarma;
  
  
  public World(){
    Room r1 = new Room("Mount Olympus", new Location(this,0,0), 
                      new java.util.ArrayList<Location>(),
                      new java.util.ArrayList<Player>(),
                      new java.util.ArrayList<Thing>());
    Room r2 = new Room("Gotham City", new Location(this,0,1), 
                      new java.util.ArrayList<Location>(),
                      new java.util.ArrayList<Player>(),
                      new java.util.ArrayList<Thing>());
    Room r3 = new Room("Azgard", new Location(this,0,2), 
                      new java.util.ArrayList<Location>(),
                      new java.util.ArrayList<Player>(),
                      new java.util.ArrayList<Thing>());
    Room r4 = new Room("Florence", new Location(this,1,2), 
                      new java.util.ArrayList<Location>(),
                      new java.util.ArrayList<Player>(),
                      new java.util.ArrayList<Thing>());
    Room r5 = new Room("Mordor", new Location(this,1,1), 
                      new java.util.ArrayList<Location>(),
                      new java.util.ArrayList<Player>(),
                      new java.util.ArrayList<Thing>());
    Room r6 = new Room("Underworld", new Location(this,1,0), 
                      new java.util.ArrayList<Location>(),
                      new java.util.ArrayList<Player>(),
                      new java.util.ArrayList<Thing>());
    
    r1.getAdjacentRooms().add(r2.getLocation());
    r1.getAdjacentRooms().add(r6.getLocation());
    r2.getAdjacentRooms().add(r1.getLocation());
    r2.getAdjacentRooms().add(r3.getLocation());
    //r2.getAdjacentRooms().add(r5.getLocation());
    r3.getAdjacentRooms().add(r2.getLocation());
    r3.getAdjacentRooms().add(r4.getLocation());
    //r4.getAdjacentRooms().add(r5.getLocation());
    r4.getAdjacentRooms().add(r3.getLocation());
    //r5.getAdjacentRooms().add(r4.getLocation());
    //r5.getAdjacentRooms().add(r6.getLocation());
    //r5.getAdjacentRooms().add(r2.getLocation());
    //r6.getAdjacentRooms().add(r5.getLocation());
    r6.getAdjacentRooms().add(r1.getLocation());
    
    rooms = new Room[2][3];
    rooms[0][0] = r1;
    rooms[0][1] = r2;
    rooms[0][2] = r3; 
    rooms[1][2] = r4; 
    //rooms[1][1] = r5; //blocked room
    rooms[1][0] = r6; //exit
    
    olympus = r1.getLocation();
    gotham = r2.getLocation();
    azgard = r3.getLocation();
    florence = r4.getLocation();
    mordor = r5.getLocation();
    underworld = r6.getLocation();
    goal = new Chalice("Chalice", underworld, 200);

    r6.addThing(goal);
    
  }
  
  public World(String worldFileName){
    // create world described in file worldFileName 
    //System.out.println("In world");
    
    String line = null;   // string to hold lines read from file
    
    try{
      
      FileReader file = new FileReader(worldFileName);
      BufferedReader reader = new BufferedReader(file);
      line = reader.readLine();
      int totalrooms = Integer.parseInt(line);
      while(line != null) {
        ArrayList<String> a1= new ArrayList<String>();
        ArrayList<Integer> a2 = new ArrayList<Integer>();
        ArrayList<Integer> a3 = new ArrayList<Integer>();
        line = reader.readLine();
        int roomNumber = Integer.parseInt(line);
        String roomName = reader.readLine();
        //System.out.println(Integer.toString(totalrooms) + " " + Integer.toString(roomNumber) + " " + roomName);
        String nextRoom = reader.readLine();     
        String [] adjacentRooms = nextRoom.split(",");
        for (int i =0; i < adjacentRooms.length; i++){
          a1.add(adjacentRooms[i].trim());
        }
        String playerInRoom = reader.readLine();  
        String [] playersInRoom = playerInRoom.split(",");
        for (int i =0; i < playersInRoom.length; i++){
          a2.add(Integer.parseInt(playersInRoom[i].trim()));
        }
        String thingInRoom = reader.readLine();  
        String [] thingsInRoom = thingInRoom.split(",");
        for (int i =0; i < thingsInRoom.length; i++){
          a3.add(Integer.parseInt(thingsInRoom[i].trim()));
        }
        //System.out.println(a1);
        //System.out.println(a2);
        //System.out.println(a3);
       }
      reader.close(); 
            
    }catch(java.io.FileNotFoundException e){
      // might be thrown by FileReader constructor
      System.err.println("File " + worldFileName  + " was not found");
    }catch(java.io.IOException e){
      // might be thrown when reading data
      System.err.println("IOException thrown : " + e);
    } 
    
  }
  
  public Location getOlympus(){
    return olympus;
  }
  
  public Location getGotham(){
    return gotham;
  }
  
  public Location getAzgard(){
    return azgard;
  }
  
  public Location getFlorence(){
    return florence;
  }
  
  public Location getMordor(){
    return mordor;
  }
  
  public Location getUnderworld(){
    return underworld;
  }
  
  public Thing getGoal(){ 
    return goal;}
  
  /** returns room of specified Player */
  public Room getRoom(Player p){
    int r = p.getLocation().getRow();
    int c = p.getLocation().getCol();
    return rooms[r][c];
  }
  /** returns room of specified location 
    * 
    * @return the room that this is at this location in this world. 
    *         Returns null if there is no such room.
    */
  public Room getRoom(Location location){
    
      return rooms[location.getRow()][location.getCol()];
    
  }
}