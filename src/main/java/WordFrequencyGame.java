import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String LINE_BREAK_DELIMITER = "\n";
    public static final String SPACE = " ";

    public String getResult(String inputStr) {

        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(SPACE_DELIMITER);

                List<Input> inputList = new ArrayList<>();
                for (String s : words) {
                    Input input = new Input(s, 1);
                    inputList.add(input);
                }

                //get the wordFrequencyMap for the next step of sizing the same word
                Map<String, List<Input>> wordFrequencyMap = getListMap(inputList);

                List<Input> wordFrequencyList = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : wordFrequencyMap.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    wordFrequencyList.add(input);
                }
                inputList = wordFrequencyList;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK_DELIMITER);
                for (Input w : inputList) {
                    String s = w.getValue() + SPACE + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }
}
