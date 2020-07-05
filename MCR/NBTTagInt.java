package MCR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;





public class NBTTagInt
  extends NBTBase
{
  public int intValue;
  
  public NBTTagInt() {}
  
  public NBTTagInt(int i) { this.intValue = i; }




  
  void writeTagContents(DataOutput dataoutput) throws IOException { dataoutput.writeInt(this.intValue); }




  
  void readTagContents(DataInput datainput) throws IOException { this.intValue = datainput.readInt(); }



  
  public byte getType() { return 3; }



  
  public String toString() { return "" + this.intValue; }
}
