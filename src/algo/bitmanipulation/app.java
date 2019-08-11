package algo.bitmanipulation;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public static void main(String[] args) {
        byte binary = 0b0010;

        System.out.print(PairWiseSwap(25));

    }


    public static boolean getBit(int num, int i) {
        // 1<<i to find the bit you are checking for.
        // & is to mask the num
        // when we AND these two, if the desire digit is 1. then it should not be 0
        return ((num & (1 << i)) != 0);
    }

    public static int setBit(int num, int i) {
        // OR operation. if it was 1 then we ignore it, if not we will take 1 from the mask.
        return num | (1 << i);
    }

    /**
     * @param a
     * @param b
     * @return number of bits needed to flip from a to b
     */
    public static int bitConversion(int a, int b) {
        //0010 -> 0011
        // c = 0001
        // c >> 1 = 0000
        //c&1 is to check if its even or odd (by checking the first digit.
        int count = 0;
        for (int c = a ^ b; c != 0; c = c >> 1) {
            count = count + (c & 1);
        }
        return count;
    }

    public static void nextNumberBruteForce(int a) {
//        int ones = 0;
//        for(int i = a; i!=0; i=i>>1){
//            ones = ones + (i&1);
//        }
//
//        System.out.println(ones);
    }

    public static String BinaryToString(double i) {
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        while (i > 0) {
            if (sb.length() >= 32) return "ERROR";
            double r = i * 2; // we will shift i in binary form to the left
            if (r >= 1) {  // if after shift, it is bigger than 1, then there must had a 1 in binary form before shift.
                sb.append(1);
                i = r - 1;
            } else {
                sb.append(0);
                i = r;
            }
        }
        return sb.toString();
    }
    /** a Program to swap odd and even bits in a integer
     *  1b011001 --> 1b100110
     * */
    public static int PairWiseSwap(int a){
        int evenMask = 0xAA;
        int oddMask  = 0x55;
        int evenResult = (a & evenMask) >>>1;
        int oddResult = (a & oddMask) << 1;
        return evenResult | oddResult;
    }


}
