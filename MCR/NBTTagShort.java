package MCR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;





public class NBTTagShort
  extends NBTBase
{
  public short shortValue;
  
  public NBTTagShort() {}
  
  public NBTTagShort(short word0) { this.shortValue = word0; }




  
  void writeTagContents(DataOutput dataoutput) throws IOException { dataoutput.writeShort(this.shortValue); }




  
  void readTagContents(DataInput datainput) throws IOException { this.shortValue = datainput.readShort(); }



  
  public byte getType() { return 2; }



  
  public String toString() { return "" + this.shortValue; }
}
