package MCR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;






public class NBTTagByteArray
  extends NBTBase
{
  public byte[] byteArray;
  
  public NBTTagByteArray() {}
  
  public NBTTagByteArray(byte[] abyte0) { this.byteArray = abyte0; }



  
  void writeTagContents(DataOutput dataoutput) throws IOException {
    dataoutput.writeInt(this.byteArray.length);
    dataoutput.write(this.byteArray);
  }


  
  void readTagContents(DataInput datainput) throws IOException {
    int i = datainput.readInt();
    this.byteArray = new byte[i];
    datainput.readFully(this.byteArray);
  }


  
  public byte getType() { return 7; }



  
  public String toString() { return "[" + this.byteArray.length + " bytes]"; }
}
