package MCR;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;









public class RegionFileCache
{
  public static synchronized RegionFile func_22193_a(File file, int i, int j) {
    File file2 = file;
    Reference<RegionFile> reference = (Reference<RegionFile>)cache.get(file2);
    if (reference != null) {
      
      RegionFile regionfile = reference.get();
      if (regionfile != null)
      {
        return regionfile;
      }
    } 
    
    if (cache.size() >= 256)
    {
      func_22192_a();
    }
    RegionFile regionfile1 = new RegionFile(file2);
    cache.put(file2, new SoftReference<>(regionfile1));
    return regionfile1;
  }

  
  public static synchronized void func_22192_a() {
    Iterator<Reference> iterator = cache.values().iterator();

    
    while (iterator.hasNext()) {


      
      Reference<RegionFile> reference = iterator.next();
      
      try {
        RegionFile regionfile = reference.get();
        if (regionfile != null)
        {
          regionfile.func_22196_b();
        }
      }
      catch (IOException ioexception) {
        
        ioexception.printStackTrace();
      } 
    } 
    cache.clear();
  }

  
  public static int getSizeDelta(File file, int i, int j) {
    RegionFile regionfile = func_22193_a(file, i, j);
    return regionfile.func_22209_a();
  }

  
  public static DataInputStream getChunkInputStream(File file, int i, int j) {
    RegionFile regionfile = func_22193_a(file, i, j);
    return regionfile.getChunkDataInputStream(i & 0x1F, j & 0x1F);
  }

  
  public static DataOutputStream getChunkOutputStream(File file, int i, int j) {
    RegionFile regionfile = func_22193_a(file, i, j);
    return regionfile.getChunkDataOutputStream(i & 0x1F, j & 0x1F);
  }
  
  private static final Map cache = new HashMap<>();
}
