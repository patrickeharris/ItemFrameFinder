package MCR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;






public class NBTTagFloat
  extends NBTBase
{
  public float floatValue;
  
  public NBTTagFloat() {}
  
  public NBTTagFloat(float f) { this.floatValue = f; }




  
  void writeTagContents(DataOutput dataoutput) throws IOException { dataoutput.writeFloat(this.floatValue); }




  
  void readTagContents(DataInput datainput) throws IOException { this.floatValue = datainput.readFloat(); }



  
  public byte getType() { return 5; }



  
  public String toString() { return "" + this.floatValue; }
}
