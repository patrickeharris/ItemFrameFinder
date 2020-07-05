package MCR;

import java.io.ByteArrayOutputStream;



class RegionFileChunkBuffer
  extends ByteArrayOutputStream
{
  private int field_22283_b;
  private int field_22285_c;
  final RegionFile field_22284_a;
  
  public RegionFileChunkBuffer(RegionFile regionfile, int i, int j) {
    super(8096);
    this.field_22284_a = regionfile;
    this.field_22283_b = i;
    this.field_22285_c = j;
  }


  
  public void close() { this.field_22284_a.write(this.field_22283_b, this.field_22285_c, this.buf, this.count); }
}
