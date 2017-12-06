package Utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {


    public static List<Integer> removeZeros(List<Integer> list){

        List<Integer> result = new ArrayList<>();

        int countZeros = 0;
        for(int i=0; i<list.size(); i++) {

            if(list.get(i) != 0) {
                if(list.get(i) < 10 && list.get(i) > 0){
                    result.add(list.get(i));
                } else {
                    String temp = Integer.toString(list.get(i));
                    String vTemp = "";
                    for(int j = 0; j < temp.length(); j++) {
                        char t = temp.charAt(j);
                        if (t == '0') {
                            countZeros++;
                        } else {
                            vTemp = vTemp + t;
                        }
                    }
                    result.add(Integer.parseInt(vTemp));
                }
            }else {
                countZeros ++;
            }
        }

        for(int k = 0; k < countZeros; k++) {
            result.add(0);
        }

        return result;
    }

    public static List<Integer> removeZerosLambda(List<Integer> list) {
        List<Integer> zeroList = new ArrayList<>();
        list.stream().filter(n -> n.toString().contains("0"))
                .forEach(n -> n.toString().chars().filter(c -> c == '0')
                    .forEach(i -> zeroList.add(Character.getNumericValue(i))));
        List<Integer> numberList = list.stream().filter(n -> n != 0)
                .map(n -> n.toString().replaceAll("0", ""))
                .map(n -> Integer.parseInt(n)).collect(Collectors.toList());
        numberList.addAll(zeroList);
        return numberList;
    }



}
