public class test {
    public static void smoosh(int[] ints) {
        // Fill in your solution here.  (Ours is twelve lines long, not counting
        // blank lines or lines already present in this file.)
        int curr = 0;
        int next = 1;
        while ((curr < ints.length) && (next < ints.length)) {
          while (next < ints.length && ints[curr] == ints[next]) {
            next++;
          }
          if (((curr + 1) < ints.length) && next < ints.length) {
            ints[curr+1] = ints[next];
            curr++;
          }
          next++;
        }
        for (int i = curr + 1; i < ints.length; i ++) {
          ints[i] = -1;
        }
      }
    
    private static String stringInts(int[] ints) {
        String s = "[  ";
        for (int i = 0; i < ints.length; i++) {
          s = s + Integer.toString(ints[i]) + "  ";
        }
        return s + "]";
      }

      public static void main(String[] args) {
        String result;
        int[] test1 = {6, 6, 6, 6, 6, 3, 6, 3, 6, 3, 3, 3, 3, 3, 3};
        System.out.println("smooshing " + stringInts(test1) + ":");
        smoosh(test1);
        result = stringInts(test1);
        System.out.println(result);
      }
}
