package MCR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;





public class NBTTagCompound
  extends NBTBase
{
  private Map tagMap = new HashMap<>();





  
  void writeTagContents(DataOutput dataoutput) throws IOException {
    for (Iterator<NBTBase> iterator = this.tagMap.values().iterator(); iterator.hasNext(); NBTBase.writeTag(nbtbase, dataoutput))
    {
      NBTBase nbtbase = iterator.next();
    }
    
    dataoutput.writeByte(0);
  }


  
  void readTagContents(DataInput datainput) throws IOException {
    this.tagMap.clear();
    NBTBase nbtbase;
    for (; (nbtbase = NBTBase.readTag(datainput)).getType() != 0; this.tagMap.put(nbtbase.getKey(), nbtbase));
  }


  
  public Collection func_28110_c() { return this.tagMap.values(); }



  
  public byte getType() { return 10; }



  
  public void setTag(String s, NBTBase nbtbase) { this.tagMap.put(s, nbtbase.setKey(s)); }



  
  public void setByte(String s, byte byte0) { this.tagMap.put(s, (new NBTTagByte(byte0)).setKey(s)); }



  
  public void setShort(String s, short word0) { this.tagMap.put(s, (new NBTTagShort(word0)).setKey(s)); }



  
  public void setInteger(String s, int i) { this.tagMap.put(s, (new NBTTagInt(i)).setKey(s)); }



  
  public void setLong(String s, long l) { this.tagMap.put(s, (new NBTTagLong(l)).setKey(s)); }



  
  public void setFloat(String s, float f) { this.tagMap.put(s, (new NBTTagFloat(f)).setKey(s)); }



  
  public void setDouble(String s, double d) { this.tagMap.put(s, (new NBTTagDouble(d)).setKey(s)); }



  
  public void setString(String s, String s1) { this.tagMap.put(s, (new NBTTagString(s1)).setKey(s)); }



  
  public void setByteArray(String s, byte[] abyte0) { this.tagMap.put(s, (new NBTTagByteArray(abyte0)).setKey(s)); }



  
  public void setCompoundTag(String s, NBTTagCompound nbttagcompound) { this.tagMap.put(s, nbttagcompound.setKey(s)); }



  
  public void setBoolean(String s, boolean flag) { setByte(s, (byte)(flag ? 1 : 0)); }



  
  public boolean hasKey(String s) { return this.tagMap.containsKey(s); }


  
  public byte getByte(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return 0;
    }
    
    return ((NBTTagByte)this.tagMap.get(s)).byteValue;
  }


  
  public short getShort(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return 0;
    }
    
    return ((NBTTagShort)this.tagMap.get(s)).shortValue;
  }


  
  public int getInteger(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return 0;
    }
    
    return ((NBTTagInt)this.tagMap.get(s)).intValue;
  }


  
  public long getLong(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return 0L;
    }
    
    return ((NBTTagLong)this.tagMap.get(s)).longValue;
  }


  
  public float getFloat(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return 0.0F;
    }
    
    return ((NBTTagFloat)this.tagMap.get(s)).floatValue;
  }


  
  public double getDouble(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return 0.0D;
    }
    
    return ((NBTTagDouble)this.tagMap.get(s)).doubleValue;
  }


  
  public String getString(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return "";
    }
    
    return ((NBTTagString)this.tagMap.get(s)).stringValue;
  }


  
  public byte[] getByteArray(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return new byte[0];
    }
    
    return ((NBTTagByteArray)this.tagMap.get(s)).byteArray;
  }


  
  public NBTTagCompound getCompoundTag(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return new NBTTagCompound();
    }
    
    return (NBTTagCompound)this.tagMap.get(s);
  }


  
  public NBTTagList getTagList(String s) {
    if (!this.tagMap.containsKey(s))
    {
      return new NBTTagList();
    }
    
    return (NBTTagList)this.tagMap.get(s);
  }



  
  public boolean getBoolean(String s) { return (getByte(s) != 0); }



  
  public String toString() { return "" + this.tagMap.size() + " entries"; }
}
