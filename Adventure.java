import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Adventure{
  
  public static void main(String[] args) throws InterruptedException {
    
    //World world = new World ("readWorld.txt");
    
    World world = new World();
    
    String s = "\t\t~~~~~ Hades Challenge: Ezio ~~~~~\n";
      s += "Hades is stirring up trouble for Zeus again. This time, he's stolen the Golden Chalice and hiding it in his home, the Underworld.\n"; 
      s += "Hercules is fighting the Lockness Monster, and the Gods of Mount Olympus have called in Ezio Auditore based" ;
      s += " on his success in fighting against the Templar rule.\n";
      s += "Help navigate Ezio from Mount Olympus to the Underworld and retrieve the Golden Chalice. \n";
      s += "Take the Golden Chalice to Florence and drop it there to save Italy.  \n";
      
    System.out.println(s);
    System.out.println("Thor has wanderlust and will ignore you.");
    System.out.println("Batman is still mad about that time.");
    System.out.println();
    
    Thread.sleep(3000);
    System.out.print("You lose health with every choice you make. Play wisely. \n");
    System.out.println("You are in " + world.getOlympus());
    
    
    Thread.sleep(3000);
   
    // creates objects (Players, Things)
    Player human = new Human(world, "Ezio", world.getOlympus(), 100, new java.util.ArrayList<Thing>(), 
                             world.getGoal());
    Player thor = new Computer (world, "Thor", world.getAzgard(), 89, new java.util.ArrayList<Thing>(), 
                             world.getGoal());
    Player batman = new Computer (world, "Batman", world.getGotham(), 46, new java.util.ArrayList<Thing>(), 
                             world.getGoal());
    Player hades = new Computer (world, "Hades", world.getUnderworld(), 99, new java.util.ArrayList<Thing>(), 
                             world.getGoal());
    
    System.out.println("Your health is " + human.getHealth());
    
    //Thing grenade = new Potion("grenade",world.getGotham(), -25);
    Thing mjolnir = new Item("Mjolnir",world.getAzgard(),200);
    Thing poison = new Potion("potion", world.getUnderworld(), -100);
    Thing shawarma = new Potion("shawarma", world.getGotham(), 6);
    
    String [] potionNames = new String [] {"grenade","Barbican", "Pocky", "sushi", "rodent"};
    int [] potionValues = new int [] {-25, 8, 21, 14, -5};
    
    Thing [] addThingstoRoom = new Potion [5];
    
    for (int i = 0; i < addThingstoRoom.length; i++){
      addThingstoRoom[i] = new Potion(potionNames[i],world.getOlympus(), potionValues[i]);
      world.getRoom(world.getOlympus()).addThing(addThingstoRoom[i]);
    }
    
    
    //batman.addThing(grenade);
    //System.out.println(batman.getThings());
    
    //puts Player and Thing objects in Rooms in the World
    world.getRoom(world.getOlympus()).addPlayer(human);
    world.getRoom(world.getAzgard()).addPlayer(thor);
    world.getRoom(world.getGotham()).addPlayer(batman);
    world.getRoom(world.getUnderworld()).addPlayer(hades);
   
    world.getRoom(world.getAzgard()).addThing(mjolnir);
    //world.getRoom(world.getGotham()).addThing(grenade);
    world.getRoom(world.getUnderworld()).addThing(poison);
    world.getRoom(world.getGotham()).addThing(shawarma);
    
    while(true){
      human.play();   
//      if (world.getRoom(human).toString().equals("Azgard")){
        thor.play();
//      }
//      if (world.getRoom(human).toString().equals("Gotham City")){
//        batman.play();
//      }
//      if (world.getRoom(human).toString().equals("Underworld")){       
//        hades.play();
    }  
  }
}
