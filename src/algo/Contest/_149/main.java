package algo.Contest._149;

public class main {

    public static void main(String[] args){
//        new Solutions().relativeSortArray(new int[]{2,3,1,3,2,4,6,7,9,2,19}, new int[]{2,1,4,3,9,6});

        new Solutions().dayOfYear("2003-03-01");
       MajorityChecker mg = new MajorityChecker(new int[]{1,1,2,2,1,1});
       System.out.println(mg.query(0,5,4));
        System.out.println(mg.query(0,3,3));
        System.out.println(mg.query(2,3,2));

//        System.out.println(new Solutions().numRollsToTarget(2,5,10));

    }
}
