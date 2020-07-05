package MCR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;





public class NBTTagString
  extends NBTBase
{
  public String stringValue;
  
  public NBTTagString() {}
  
  public NBTTagString(String s) {
    this.stringValue = s;
    if (s == null)
    {
      throw new IllegalArgumentException("Empty string not allowed");
    }
  }






  
  void writeTagContents(DataOutput dataoutput) throws IOException { dataoutput.writeUTF(this.stringValue); }




  
  void readTagContents(DataInput datainput) throws IOException { this.stringValue = datainput.readUTF(); }



  
  public byte getType() { return 8; }



  
  public String toString() { return "" + this.stringValue; }
}
