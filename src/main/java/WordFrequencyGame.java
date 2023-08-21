import java.util.*;
import java.util.stream.Collectors;

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

                List<Input> inputList = getInputList(words);


                //get the wordFrequencyMap for the next step of sizing the same word
                Map<String, List<Input>> wordFrequencyMap = getListMap(inputList);

                List<Input> wordFrequencyList = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : wordFrequencyMap.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    wordFrequencyList.add(input);
                }
                inputList = wordFrequencyList;

                inputList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                StringJoiner stringJoiner = new StringJoiner(LINE_BREAK_DELIMITER);
                for (Input w : inputList) {
                    String s = w.getValue() + SPACE + w.getWordCount();
                    stringJoiner.add(s);
                }
                return stringJoiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<Input> getInputList(String[] words) {
        return Arrays.stream(words)
                .map(word -> new Input(word, 1))
                .collect(Collectors.toList());
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
