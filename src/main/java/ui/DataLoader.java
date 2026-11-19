package ui;
public class DataLoader {
 public static int[] parseInput(String input) {
  if(input==null || input.trim().isEmpty()) throw new IllegalArgumentException("Please enter integers.");
  String[] parts=input.trim().split("[,，\\s]+",-1);
  if(parts.length>50) throw new IllegalArgumentException("Enter at most 50 integers.");
  int[] data=new int[parts.length];
  try {for(int i=0;i<parts.length;i++) data[i]=Integer.parseInt(parts[i]);}
  catch(NumberFormatException e){throw new IllegalArgumentException("Use comma or space separated 32-bit integers.");}
  return data;
 }
}
