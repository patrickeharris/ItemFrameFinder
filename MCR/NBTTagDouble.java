package MCR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;






public class NBTTagDouble
  extends NBTBase
{
  public double doubleValue;
  
  public NBTTagDouble() {}
  
  public NBTTagDouble(double d) { this.doubleValue = d; }




  
  void writeTagContents(DataOutput dataoutput) throws IOException { dataoutput.writeDouble(this.doubleValue); }




  
  void readTagContents(DataInput datainput) throws IOException { this.doubleValue = datainput.readDouble(); }



  
  public byte getType() { return 6; }



  
  public String toString() { return "" + this.doubleValue; }
}
