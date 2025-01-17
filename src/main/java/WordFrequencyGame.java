import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String LINE_BREAK_DELIMITER = "\n";
    public static final String SPACE = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String ONE_LITERAL = " 1";

    public String getResult(String inputStr) {
        String[] inputStringSplit = inputStr.split(SPACE_DELIMITER);
        if (inputStringSplit.length == 1) {
            return inputStr + ONE_LITERAL;
        }
        try {
            List<Input> inputList = getInputList(inputStringSplit);
            Map<String, List<Input>> wordFrequencyMap = getListMap(inputList);
            inputList = getWordFrequencyList(wordFrequencyMap);
            inputList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());
            return buildOutput(inputList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }

    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        return inputList.stream()
                .collect(Collectors.groupingBy(
                        Input::getWord,
                        Collectors.mapping(input -> input, Collectors.toList())
                ));
    }

    private String buildOutput(List<Input> inputList) {
        return inputList.stream()
                .map(word -> word.getWord() + SPACE + word.getWordCount())
                .collect(Collectors.joining(LINE_BREAK_DELIMITER));
    }

    private List<Input> getWordFrequencyList(Map<String, List<Input>> wordFrequencyMap) {
        return wordFrequencyMap.entrySet()
                .stream()
                .map(entry -> new Input(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private List<Input> getInputList(String[] words) {
        return Arrays.stream(words)
                .map(word -> new Input(word, 1))
                .collect(Collectors.toList());
    }
}
