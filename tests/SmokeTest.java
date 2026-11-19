import algorithms.*;
import ui.DataLoader;
import java.util.*;
public class SmokeTest {
 public static void main(String[] args) {
  if(!Arrays.equals(DataLoader.parseInput("-3, 2，2 0"),new int[]{-3,2,2,0}))throw new AssertionError("Parsing");
  for(String bad:new String[]{"", "abc", "1,", "2147483648"}) {
   try {DataLoader.parseInput(bad);throw new AssertionError("Accepted "+bad);}catch(IllegalArgumentException expected){}
  }
  SortingAlgorithm[] algorithms={new BubbleSort(),new InsertionSort(),new SelectionSort(),new MergeSort(),new QuickSort(),new HeapVisualizer()};
  for(SortingAlgorithm alg:algorithms)for(int[] input:new int[][]{{},{5},{3,-2,3,0,Integer.MAX_VALUE,Integer.MIN_VALUE},{9,8,7,6}}){
   int[] expected=input.clone();Arrays.sort(expected);
   var steps=alg.sort(input.clone());
   if(expected.length>0 && (steps.isEmpty() || !Arrays.equals(steps.get(steps.size()-1).getArrayState(),expected)))throw new AssertionError(alg.getClass().getName());
  }
  System.out.println("PASS: input validation and all six sorting algorithms");
 }
}
