package interviews.paypal;

import javafx.util.Pair;

import java.util.*;

public class PayPal {

    //given a paragraph, and a rank, return the string with given rank

    public String findStringByRank(String article, int rank){

        String[] arr = article.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for(String s:arr){
            if(map.containsKey(s)){
                map.put(s,map.get(s)+1);
            }else{
                map.put(s,1);
            }
        }
        List<Pair> list = new ArrayList<>();
        for(Map.Entry<String, Integer> e : map.entrySet()){
            list.add(new Pair(e.getKey(), e.getValue()));
        }

        Collections.sort(list, (o1, o2) -> (int) o2.getValue()-(int)o1.getValue());

        return (String) list.get(rank-1).getKey();
    }
}
