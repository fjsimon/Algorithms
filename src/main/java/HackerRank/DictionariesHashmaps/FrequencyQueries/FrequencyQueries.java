package HackerRank.DictionariesHashmaps.FrequencyQueries;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrequencyQueries {

    static List<Integer> freqQuery(List<int[]> queries) {
        final Map<Integer, Integer> valueToFreq = new HashMap<>();
        final Map<Integer, Integer> freqToOccurrence = new HashMap<>();
        final List<Integer> frequencies = new ArrayList<>();

        int key, value;
        Integer oldFreq, newFreq;
        Integer oldOccurrence, newOccurrence;
        for (int[] query : queries) {
            key = query[0];
            value = query[1];
            if (key == 3) {
                frequencies.add(freqToOccurrence.get(value) == null ? 0 : 1);
            } else {
                oldFreq = valueToFreq.containsKey(value) ? valueToFreq.get(value) : 0;
                oldOccurrence = freqToOccurrence.containsKey(oldFreq) ? freqToOccurrence.get(oldFreq) : 0;
                if (key == 1) {
                    newFreq = oldFreq + 1;
                } else {
                    newFreq = oldFreq - 1;
                }
                newOccurrence = freqToOccurrence.containsKey(newFreq) ? freqToOccurrence.get(newFreq) : 0;
                if (newFreq < 1) {
                    valueToFreq.remove(value);
                } else {
                    valueToFreq.put(value, newFreq);
                }

                if ((oldOccurrence - 1) < 1) {
                    freqToOccurrence.remove(oldFreq);
                } else {
                    freqToOccurrence.put(oldFreq, oldOccurrence - 1);
                }
                freqToOccurrence.put(newFreq, newOccurrence + 1);
            }
        }
        return frequencies;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }

            List<Integer> ans = freqQuery(queries);
            for (int i = 0; i < ans.size(); i++) {
                bufferedWriter.write(String.valueOf(ans.get(i)));
                if (i != ans.size() - 1) {
                    bufferedWriter.write("\n");
                }
            }
            bufferedWriter.newLine();
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

//    TIME - OUT issues
//    static List<Integer> freqQuery(List<List<Integer>> queries) {
//        List<Integer> result = new ArrayList<>();
//        Map<Integer, Integer> map = new HashMap<>();
//
//        for(List<Integer> list : queries) {
//            int operation = list.get(0);
//            int value = list.get(1);
//            if(operation == 1) {
//                if(map.containsKey(value)) {
//                    int count = map.get(value) + 1;
//                    map.put(value,  count);
//                } else {
//                    map.put(value, 1);
//                }
//            } else if(operation == 2) {
//                if(map.containsKey(value)) {
//                    int count = map.get(value) - 1;
//                    if(count == 0) {
//                        map.remove(value);
//                    } else {
//                        map.put(value, count);
//                    }
//                }
//            } else if(operation == 3) {
//                if(map.containsValue(value)) {
//                    result.add(1);
//                }else{
//                    result.add(0);
//                }
//            }
//        }
//        return result;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int q = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<List<Integer>> queries = new ArrayList<>();
//
//        for (int i = 0; i < q; i++) {
//            String[] queriesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
//
//            List<Integer> queriesRowItems = new ArrayList<>();
//
//            for (int j = 0; j < 2; j++) {
//                int queriesItem = Integer.parseInt(queriesRowTempItems[j]);
//                queriesRowItems.add(queriesItem);
//            }
//
//            queries.add(queriesRowItems);
//        }
//
//        List<Integer> ans = freqQuery(queries);
//
//        for (int i = 0; i < ans.size(); i++) {
//            bufferedWriter.write(String.valueOf(ans.get(i)));
//            if (i != ans.size() - 1) {
//                bufferedWriter.write("\n");
//            }
//        }
//
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }

}
