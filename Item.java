public class Item extends Thing{
 
  
  public Item (){
  }
  
  public Item(String name, Location location)
  {
    this.name = name;
    this.location = location;
  }
  
  public Item(String name, Location location, int value)
  {
    super(name, location, value);
  }
  public Item(String name, Location location, int value, int e)
  {
    super(name,location,value,e);
  }
  
  @Override
 public boolean equals(Object o){
   if( o instanceof Item){
     return this.name.equals( ((Item)o).name )  
            && this.location.equals( ((Item)o).location ) 
            && this.value == ((Item)o).value
            && this.extraField == ((Item)o).extraField;
                                  
   }else{
     return false;
   }
 }
}