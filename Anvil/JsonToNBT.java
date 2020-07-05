package Anvil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;





public class JsonToNBT
{
  private static final String __OBFID = "CL_00001232";
  
  public static NBTBase func_150315_a(String p_150315_0_) throws NBTException {
    p_150315_0_ = p_150315_0_.trim();
    int i = func_150310_b(p_150315_0_);
    
    if (i != 1)
    {
      throw new NBTException("Encountered multiple top tags, only one expected");
    }

    
    Any any = null;
    
    if (p_150315_0_.startsWith("{")) {
      
      any = func_150316_a("tag", p_150315_0_);
    }
    else {
      
      any = func_150316_a(func_150313_b(p_150315_0_, false), func_150311_c(p_150315_0_, false));
    } 
    
    return any.func_150489_a();
  }


  
  static int func_150310_b(String p_150310_0_) throws NBTException {
    int i = 0;
    boolean flag = false;
    Stack<Character> stack = new Stack<>();
    
    for (int j = 0; j < p_150310_0_.length(); j++) {
      
      char c0 = p_150310_0_.charAt(j);
      
      if (c0 == '"') {
        
        if (j > 0 && p_150310_0_.charAt(j - 1) == '\\')
        {
          if (!flag)
          {
            throw new NBTException("Illegal use of \\\": " + p_150310_0_);
          }
        }
        else
        {
          flag = !flag;
        }
      
      } else if (!flag) {
        
        if (c0 != '{' && c0 != '[') {
          
          if (c0 == '}' && (stack.isEmpty() || ((Character)stack.pop()).charValue() != '{'))
          {
            throw new NBTException("Unbalanced curly brackets {}: " + p_150310_0_);
          }
          
          if (c0 == ']' && (stack.isEmpty() || ((Character)stack.pop()).charValue() != '['))
          {
            throw new NBTException("Unbalanced square brackets []: " + p_150310_0_);
          }
        }
        else {
          
          if (stack.isEmpty())
          {
            i++;
          }
          
          stack.push(Character.valueOf(c0));
        } 
      } 
    } 
    
    if (flag)
    {
      throw new NBTException("Unbalanced quotation: " + p_150310_0_);
    }
    if (!stack.isEmpty())
    {
      throw new NBTException("Unbalanced brackets: " + p_150310_0_);
    }
    if (i == 0 && !p_150310_0_.isEmpty())
    {
      return 1;
    }

    
    return i;
  }


  
  static Any func_150316_a(String p_150316_0_, String p_150316_1_) throws NBTException {
    p_150316_1_ = p_150316_1_.trim();
    func_150310_b(p_150316_1_);




    
    if (p_150316_1_.startsWith("{")) {
      
      if (!p_150316_1_.endsWith("}"))
      {
        throw new NBTException("Unable to locate ending bracket for: " + p_150316_1_);
      }

      
      p_150316_1_ = p_150316_1_.substring(1, p_150316_1_.length() - 1);
      Compound compound = new Compound(p_150316_0_);
      
      while (p_150316_1_.length() > 0) {
        
        String s2 = func_150314_a(p_150316_1_, false);
        
        if (s2.length() > 0) {
          
          String s3 = func_150313_b(s2, false);
          String s4 = func_150311_c(s2, false);
          compound.field_150491_b.add(func_150316_a(s3, s4));
          
          if (p_150316_1_.length() < s2.length() + 1) {
            break;
          }

          
          char c0 = p_150316_1_.charAt(s2.length());
          
          if (c0 != ',' && c0 != '{' && c0 != '}' && c0 != '[' && c0 != ']')
          {
            throw new NBTException("Unexpected token '" + c0 + "' at: " + p_150316_1_.substring(s2.length()));
          }
          
          p_150316_1_ = p_150316_1_.substring(s2.length() + 1);
        } 
      } 
      
      return compound;
    } 
    
    if (p_150316_1_.startsWith("[") && !p_150316_1_.matches("\\[[-\\d|,\\s]+\\]")) {
      
      if (!p_150316_1_.endsWith("]"))
      {
        throw new NBTException("Unable to locate ending bracket for: " + p_150316_1_);
      }

      
      p_150316_1_ = p_150316_1_.substring(1, p_150316_1_.length() - 1);
      List list = new List(p_150316_0_);
      
      while (p_150316_1_.length() > 0) {
        
        String s2 = func_150314_a(p_150316_1_, true);
        
        if (s2.length() > 0) {
          
          String s3 = func_150313_b(s2, true);
          String s4 = func_150311_c(s2, true);
          list.field_150492_b.add(func_150316_a(s3, s4));
          
          if (p_150316_1_.length() < s2.length() + 1) {
            break;
          }

          
          char c0 = p_150316_1_.charAt(s2.length());
          
          if (c0 != ',' && c0 != '{' && c0 != '}' && c0 != '[' && c0 != ']')
          {
            throw new NBTException("Unexpected token '" + c0 + "' at: " + p_150316_1_.substring(s2.length()));
          }
          
          p_150316_1_ = p_150316_1_.substring(s2.length() + 1);
        } 
      } 




      
      return list;
    } 


    
    return new Primitive(p_150316_0_, p_150316_1_);
  }


  
  private static String func_150314_a(String p_150314_0_, boolean p_150314_1_) throws NBTException {
    int i = func_150312_a(p_150314_0_, ':');
    
    if (i < 0 && !p_150314_1_)
    {
      throw new NBTException("Unable to locate name/value separator for string: " + p_150314_0_);
    }

    
    int j = func_150312_a(p_150314_0_, ',');
    
    if (j >= 0 && j < i && !p_150314_1_)
    {
      throw new NBTException("Name error at: " + p_150314_0_);
    }

    
    if (p_150314_1_ && (i < 0 || i > j))
    {
      i = -1;
    }
    
    Stack<Character> stack = new Stack<>();
    int k = i + 1;
    boolean flag1 = false;
    boolean flag2 = false;
    boolean flag3 = false;
    
    for (int l = 0; k < p_150314_0_.length(); k++) {
      
      char c0 = p_150314_0_.charAt(k);
      
      if (c0 == '"') {
        
        if (k > 0 && p_150314_0_.charAt(k - 1) == '\\')
        {
          if (!flag1)
          {
            throw new NBTException("Illegal use of \\\": " + p_150314_0_);
          }
        }
        else
        {
          flag1 = !flag1;
          
          if (flag1 && !flag3)
          {
            flag2 = true;
          }
          
          if (!flag1)
          {
            l = k;
          }
        }
      
      } else if (!flag1) {
        
        if (c0 != '{' && c0 != '[') {
          
          if (c0 == '}' && (stack.isEmpty() || ((Character)stack.pop()).charValue() != '{'))
          {
            throw new NBTException("Unbalanced curly brackets {}: " + p_150314_0_);
          }
          
          if (c0 == ']' && (stack.isEmpty() || ((Character)stack.pop()).charValue() != '['))
          {
            throw new NBTException("Unbalanced square brackets []: " + p_150314_0_);
          }
          
          if (c0 == ',' && stack.isEmpty())
          {
            return p_150314_0_.substring(0, k);
          }
        }
        else {
          
          stack.push(Character.valueOf(c0));
        } 
      } 
      
      if (!Character.isWhitespace(c0)) {
        
        if (!flag1 && flag2 && l != k)
        {
          return p_150314_0_.substring(0, l + 1);
        }
        
        flag3 = true;
      } 
    } 
    
    return p_150314_0_.substring(0, k);
  }



  
  private static String func_150313_b(String p_150313_0_, boolean p_150313_1_) throws NBTException {
    if (p_150313_1_) {
      
      p_150313_0_ = p_150313_0_.trim();
      
      if (p_150313_0_.startsWith("{") || p_150313_0_.startsWith("["))
      {
        return "";
      }
    } 
    
    int i = p_150313_0_.indexOf(':');
    
    if (i < 0) {
      
      if (p_150313_1_)
      {
        return "";
      }

      
      throw new NBTException("Unable to locate name/value separator for string: " + p_150313_0_);
    } 


    
    return p_150313_0_.substring(0, i).trim();
  }


  
  private static String func_150311_c(String p_150311_0_, boolean p_150311_1_) throws NBTException {
    if (p_150311_1_) {
      
      p_150311_0_ = p_150311_0_.trim();
      
      if (p_150311_0_.startsWith("{") || p_150311_0_.startsWith("["))
      {
        return p_150311_0_;
      }
    } 
    
    int i = p_150311_0_.indexOf(':');
    
    if (i < 0) {
      
      if (p_150311_1_)
      {
        return p_150311_0_;
      }

      
      throw new NBTException("Unable to locate name/value separator for string: " + p_150311_0_);
    } 


    
    return p_150311_0_.substring(i + 1).trim();
  }


  
  private static int func_150312_a(String p_150312_0_, char p_150312_1_) {
    int i = 0;
    
    for (boolean flag = false; i < p_150312_0_.length(); i++) {
      
      char c1 = p_150312_0_.charAt(i);
      
      if (c1 == '"') {
        
        if (i <= 0 || p_150312_0_.charAt(i - 1) != '\\')
        {
          flag = !flag;
        }
      }
      else if (!flag) {
        
        if (c1 == p_150312_1_)
        {
          return i;
        }
        
        if (c1 == '{' || c1 == '[')
        {
          return -1;
        }
      } 
    } 
    
    return -1;
  }
  
  static abstract class Any
  {
    protected String field_150490_a;
    private static final String __OBFID = "CL_00001233";
    
    public abstract NBTBase func_150489_a();
  }
  
  static class Compound
    extends Any {
    protected ArrayList field_150491_b = new ArrayList();
    
    private static final String __OBFID = "CL_00001234";

    
    public Compound(String p_i45137_1_) { this.field_150490_a = p_i45137_1_; }


    
    public NBTBase func_150489_a() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      Iterator<JsonToNBT.Any> iterator = this.field_150491_b.iterator();
      
      while (iterator.hasNext()) {
        
        JsonToNBT.Any any = iterator.next();
        nbttagcompound.setTag(any.field_150490_a, any.func_150489_a());
      } 
      
      return nbttagcompound;
    }
  }
  
  static class List
    extends Any {
    protected ArrayList field_150492_b = new ArrayList();
    
    private static final String __OBFID = "CL_00001235";

    
    public List(String p_i45138_1_) { this.field_150490_a = p_i45138_1_; }


    
    public NBTBase func_150489_a() {
      NBTTagList nbttaglist = new NBTTagList();
      Iterator<JsonToNBT.Any> iterator = this.field_150492_b.iterator();
      
      while (iterator.hasNext()) {
        
        JsonToNBT.Any any = iterator.next();
        nbttaglist.appendTag(any.func_150489_a());
      } 
      
      return nbttaglist;
    }
  }
  
  static class Primitive
    extends Any
  {
    protected String field_150493_b;
    private static final String __OBFID = "CL_00001236";
    
    public Primitive(String p_i45139_1_, String p_i45139_2_) {
      this.field_150490_a = p_i45139_1_;
      this.field_150493_b = p_i45139_2_;
    }


    
    public NBTBase func_150489_a() {
      try {
        if (this.field_150493_b.matches("[-+]?[0-9]*\\.?[0-9]+[d|D]"))
        {
          return new NBTTagDouble(Double.parseDouble(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
        }
        if (this.field_150493_b.matches("[-+]?[0-9]*\\.?[0-9]+[f|F]"))
        {
          return new NBTTagFloat(Float.parseFloat(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
        }
        if (this.field_150493_b.matches("[-+]?[0-9]+[b|B]"))
        {
          return new NBTTagByte(Byte.parseByte(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
        }
        if (this.field_150493_b.matches("[-+]?[0-9]+[l|L]"))
        {
          return new NBTTagLong(Long.parseLong(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
        }
        if (this.field_150493_b.matches("[-+]?[0-9]+[s|S]"))
        {
          return new NBTTagShort(Short.parseShort(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
        }
        if (this.field_150493_b.matches("[-+]?[0-9]+"))
        {
          return new NBTTagInt(Integer.parseInt(this.field_150493_b.substring(0, this.field_150493_b.length())));
        }
        if (this.field_150493_b.matches("[-+]?[0-9]*\\.?[0-9]+"))
        {
          return new NBTTagDouble(Double.parseDouble(this.field_150493_b.substring(0, this.field_150493_b.length())));
        }
        if (!this.field_150493_b.equalsIgnoreCase("true") && !this.field_150493_b.equalsIgnoreCase("false")) {
          
          if (this.field_150493_b.startsWith("[") && this.field_150493_b.endsWith("]")) {
            
            if (this.field_150493_b.length() > 2) {
              
              String s = this.field_150493_b.substring(1, this.field_150493_b.length() - 1);
              String[] astring = s.split(",");

              
              try {
                if (astring.length <= 1)
                {
                  return new NBTTagIntArray(new int[] { Integer.parseInt(s.trim()) });
                }

                
                int[] aint = new int[astring.length];
                
                for (int i = 0; i < astring.length; i++)
                {
                  aint[i] = Integer.parseInt(astring[i].trim());
                }
                
                return new NBTTagIntArray(aint);
              
              }
              catch (NumberFormatException numberformatexception) {
                
                return new NBTTagString(this.field_150493_b);
              } 
            } 

            
            return new NBTTagIntArray();
          } 


          
          if (this.field_150493_b.startsWith("\"") && this.field_150493_b.endsWith("\"") && this.field_150493_b.length() > 2)
          {
            this.field_150493_b = this.field_150493_b.substring(1, this.field_150493_b.length() - 1);
          }
          
          this.field_150493_b = this.field_150493_b.replaceAll("\\\\\"", "\"");
          return new NBTTagString(this.field_150493_b);
        } 


        
        return new NBTTagByte((byte)(Boolean.parseBoolean(this.field_150493_b) ? 1 : 0));
      
      }
      catch (NumberFormatException numberformatexception1) {
        
        this.field_150493_b = this.field_150493_b.replaceAll("\\\\\"", "\"");
        return new NBTTagString(this.field_150493_b);
      } 
    }
  }
}
