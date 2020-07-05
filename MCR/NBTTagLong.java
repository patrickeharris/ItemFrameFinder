package MCR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;






public class NBTTagLong
  extends NBTBase
{
  public long longValue;
  
  public NBTTagLong() {}
  
  public NBTTagLong(long l) { this.longValue = l; }




  
  void writeTagContents(DataOutput dataoutput) throws IOException { dataoutput.writeLong(this.longValue); }




  
  void readTagContents(DataInput datainput) throws IOException { this.longValue = datainput.readLong(); }



  
  public byte getType() { return 4; }



  
  public String toString() { return "" + this.longValue; }
}
