package MCR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;






public class NBTTagList
  extends NBTBase
{
  private List tagList = new ArrayList();
  
  private byte tagType;

  
  void writeTagContents(DataOutput dataoutput) throws IOException {
    if (this.tagList.size() > 0) {
      
      this.tagType = ((NBTBase)this.tagList.get(0)).getType();
    } else {
      
      this.tagType = 1;
    } 
    dataoutput.writeByte(this.tagType);
    dataoutput.writeInt(this.tagList.size());
    for (int i = 0; i < this.tagList.size(); i++)
    {
      ((NBTBase)this.tagList.get(i)).writeTagContents(dataoutput);
    }
  }



  
  void readTagContents(DataInput datainput) throws IOException {
    this.tagType = datainput.readByte();
    int i = datainput.readInt();
    this.tagList = new ArrayList();
    for (int j = 0; j < i; j++) {
      
      NBTBase nbtbase = NBTBase.createTagOfType(this.tagType);
      nbtbase.readTagContents(datainput);
      this.tagList.add(nbtbase);
    } 
  }



  
  public byte getType() { return 9; }



  
  public String toString() { return "" + this.tagList.size() + " entries of type " + NBTBase.getTagName(this.tagType); }


  
  public void setTag(NBTBase nbtbase) {
    this.tagType = nbtbase.getType();
    this.tagList.add(nbtbase);
  }


  
  public NBTBase tagAt(int i) { return this.tagList.get(i); }



  
  public int tagCount() { return this.tagList.size(); }
}
