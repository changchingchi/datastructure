package algo.Sweepline;

import sun.jvm.hotspot.utilities.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public static void main(String[] args) {
        List<Interval> testcase = new ArrayList<>();
        testcase.add(new Interval(0,4));
        testcase.add(new Interval(0,3));

        testcase.add(new Interval(1,3));
        testcase.add(new Interval(5,6));
//        System.out.println(countOfAirplanes(testcase));
        int[][] a = new int[2][2];
        a[0] = new int[]{1,3};
        a[1] = new int[]{1,2};
        Arrays.sort(a, (o1, o2)-> {
           if(o1[0]==o2[0]){
               return o1[1]-o2[1];
           }
            return o1[0]-o2[0];
        });
        for(int[] i : a){

            System.out.println(i[0]+":"+i[1]);
        }

    }



    //掃描線

    //Given an list interval, which are taking off and landing time of the flight.
    // How many airplanes are there at most at the same time in the sky?

    public static int countOfAirplanes(List<Interval> airplanes) {

        //sweepline 思路 : 把起飛的時刻跟降落的時刻分開
        //起飛為1, 降落為0
        //然後依照時間軸排序(或題意） 計算 1跟0的最大值
        List<int[]> array = new ArrayList<>();
        for(Interval interval : airplanes){
            array.add(new int[]{(int) interval.getLowEndpoint(),1});
            array.add(new int[]{(int) interval.getHighEndpoint(),0});
        }

        //comparator 依照時間排序 如果時間依樣 land先
        array.sort(((o1, o2) -> {
            if(o1[0]==o2[0]){
                return o1[1]-o2[1];
            }else{
                return o1[0]-o2[0];
            }
        }));
        int max = 0; int count = 0;

        for(int[] set : array){
            if(set[1]==1){
                count++;
            }else{
                count--;
            }
            max = Math.max(max, count);
        }
        return max;
    }

}
