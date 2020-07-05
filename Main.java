import Anvil.CompressedStreamTools;
import Anvil.NBTTagCompound;
import Anvil.NBTTagList;
import MCR.RegionFileCache;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class Main
{
  public static void main(String[] args) throws IOException { readSignsAnvil(); }

  public static void readSignsAnvil() throws IOException {
    File folder = new File("region");
    File[] listOfFiles = folder.listFiles();
    File output = new File("output.txt");
    BufferedWriter writer = new BufferedWriter(new FileWriter(output));
    
    for (int f = 0; f < listOfFiles.length; f++) {
      
      for (int x = 0; x < 32; x++) {
        
        for (int z = 0; z < 32; z++) {
          
          DataInputStream dataInputStream = RegionFileCache.getChunkInputStream(listOfFiles[f], x, z);
          
          if (dataInputStream != null) {
            
            NBTTagCompound nbttagcompund = new NBTTagCompound();
            nbttagcompund = CompressedStreamTools.read(dataInputStream);
            
            NBTTagCompound nbttagcompund2 = new NBTTagCompound();
            nbttagcompund2 = nbttagcompund.getCompoundTag("Level");

            
            NBTTagList nbttaglist = nbttagcompund2.getTagList("Entities", 10);
            
            for (int i = 0; i < nbttaglist.tagCount(); i++) {
              
              NBTTagCompound entity = nbttaglist.getCompoundTagAt(i);
              
              if (entity.hasKey("Item") && entity.hasKey("Facing")) {
                String text1 = entity.getTag("Pos").toString();
                text1 = text1.replaceAll("0:", "");
                text1 = text1.replaceAll("1:", "");
                text1 = text1.replaceAll("2:", "");
                text1 = text1.replaceAll("d", "");

                
                text1 = text1.substring(1, text1.lastIndexOf(","));
                text1 = text1.replaceAll(",", ", ");
                writer.write("Chunk [" + x + ", " + z + "]\t(" + text1 + ")\t");
                String text2 = String.valueOf(entity.getCompoundTag("Item").getTag("id"));
                text2 = text2.replaceAll("\"", "");
                writer.write("Item: " + text2);
                JSONObject json1 = null;
                JSONObject json2 = null;
                JSONObject json3 = null;
                JSONObject json4 = null;                
                JSONObject[] objects = { json1, json2, json3, json4 };  
                writer.newLine();
              } 
            } 
          } 
        } 
      } 
    } 
    writer.close();
  }
}
