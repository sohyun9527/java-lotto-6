package lotto.validation;

public class Validation {

    public static void validateOnlyDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요.");
        }
    }

    public static void validateContainSpace(String input) {
        if (input.contains(" ") || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 공백 혹은 비어있는 값이 존재합니다!");
        }
    }
}
