package Anvil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class Schematic
{
  public byte[] blocks;
  public byte[] data;
  public short width;
  public short length;
  public short height;
  public NBTTagList tileEntities;
  
  public Schematic(int width, int height, int length) {
    this.width = (short)width;
    this.length = (short)length;
    this.height = (short)height;
    this.blocks = new byte[length * width * height];
    this.data = new byte[length * width * height];
    this.tileEntities = new NBTTagList();
  }


  
  public byte getBlockID(int x, int y, int z) { return this.blocks[(y * this.length + z) * this.width + x]; }



  
  public byte getBlockData(int x, int y, int z) { return this.data[(y * this.length + z) * this.width + x]; }



  
  public void setBlock(int x, int y, int z, byte blockID) { this.blocks[(y * this.length + z) * this.width + x] = blockID; }


  
  public void setBlock(int x, int y, int z, byte blockID, byte blockData) {
    setBlock(x, y, z, blockID);
    this.data[(y * this.length + z) * this.width + x] = blockData;
  }

  
  public Schematic rotate() {
    Schematic newSchematic = new Schematic(this.length, this.height, this.width);
    
    for (int y = 0; y < this.height; y++) {
      
      for (int x = 0; x < this.width; x++) {
        
        for (int z = 0; z < this.length; z++)
        {
          newSchematic.setBlock(z, y, x, getBlockID(x, y, z), getBlockData(x, y, z));
        }
      } 
    } 
    
    return newSchematic;
  }

  
  public Schematic mirrorWidth() {
    Schematic newSchematic = new Schematic(this.width, this.height, this.length);
    for (int y = 0; y < this.height; y++) {
      
      for (int x = this.width - 1; x >= 0; x--) {
        
        for (int z = 0; z < this.length; z++)
        {
          newSchematic.setBlock(this.width - x - 1, y, z, getBlockID(x, y, z), getBlockData(x, y, z));
        }
      } 
    } 
    return newSchematic;
  }

  
  public Schematic mirrorLength() {
    Schematic newSchematic = new Schematic(this.width, this.height, this.length);
    for (int y = 0; y < this.height; y++) {
      
      for (int x = 0; x < this.width; x++) {
        
        for (int z = this.length - 1; z >= 0; z--)
        {
          newSchematic.setBlock(x, y, this.length - z - 1, getBlockID(x, y, z), getBlockData(x, y, z));
        }
      } 
    } 
    return newSchematic;
  }

  
  public void writeSchematic(String fileName) throws IOException {
    NBTTagCompound schematic = new NBTTagCompound();
    NBTTagCompound base = new NBTTagCompound();
    
    base.setTag("Schematic", schematic);
    
    File file = new File(String.valueOf(fileName) + ".schematic");
    FileOutputStream fos = new FileOutputStream(file);
    
    schematic.setShort("Width", this.width);
    schematic.setShort("Height", this.height);
    schematic.setShort("Length", this.length);
    schematic.setString("Materials", "Alpha");
    schematic.setByteArray("Blocks", this.blocks);
    schematic.setByteArray("Data", this.data);
    schematic.setTag("Entities", new NBTTagList());
    schematic.setTag("TileEntities", this.tileEntities);
    
    CompressedStreamTools.writeCompressed(schematic, fos);
    fos.close();
  }
}
