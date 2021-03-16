package hu.nive.ujratervezes.zarovizsga.digitscounter;

import java.util.HashSet;
import java.util.Set;

public class DigitsCounter {
    public int getCountOfDigits(String word) {
        if(isEmpty(word)) {
            return 0;
        }
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i<word.length(); i++) {
            int number = word.charAt(i) - '0';
            if(Character.isDigit(word.charAt(i))) {
                result.add(number);
            }
        }
        return result.size();
    }

    private boolean isEmpty(String word) {
        return word == null || word.trim().equals("");
    }

}
