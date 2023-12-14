package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final int MAX_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateDuplicate(numbers);
        validateOverRange(numbers);
    }

    public int matchCount(Lotto otherLotto) {
        int count = 0;
        for (Integer number : numbers) {
            if (otherLotto.isContain(number)) {
                count++;
            }
        }
        return count;
    }

    public boolean isContain(int number) {
        return numbers.contains(number);
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 번호가 존재합니다.");
        }
    }

    private void validateOverRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < START_NUMBER || number > END_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 1 ~ 45 사이의 수가 아닙니다.");
            }
        }
    }

    private static void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 6개의 수만 가능합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
