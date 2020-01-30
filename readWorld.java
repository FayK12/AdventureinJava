import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class readWorld {

 
  public static void main(String[] args) { 
    
    String fileName = "readWorld.txt";  // name of file to read
  
    String line = null;   // string to hold lines read from file
    
    
    try{
      FileReader file = new FileReader( fileName );
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
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
       }
      reader.close();    
      
            
    }catch(java.io.FileNotFoundException e){
      // might be thrown by FileReader constructor
      System.err.println("File " + fileName + " was not found");
    }catch(java.io.IOException e){
      // might be thrown when reading data
      System.err.println("IOException thrown : " + e);
    } 
    
    
    
  }

  
}