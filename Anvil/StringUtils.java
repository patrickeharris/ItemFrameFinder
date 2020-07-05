package Anvil;

import java.util.regex.Pattern;

public class StringUtils
{
  private static final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

  
  private static final String __OBFID = "CL_00001501";


  
  public static String ticksToElapsedTime(int p_76337_0_) {
    int j = p_76337_0_ / 20;
    int k = j / 60;
    j %= 60;
    return (j < 10) ? (String.valueOf(k) + ":0" + j) : (String.valueOf(k) + ":" + j);
  }


  
  public static String stripControlCodes(String p_76338_0_) { return patternControlCode.matcher(p_76338_0_).replaceAll(""); }






  
  public static boolean isNullOrEmpty(String p_151246_0_) { return (p_151246_0_ == null || "".equals(p_151246_0_)); }
}
