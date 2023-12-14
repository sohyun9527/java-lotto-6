package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String REQUEST_MONEY = "구입금액을 입력해 주세요.";
    private static final String REQUEST_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public String readPurchaseMoney() {
        System.out.println(REQUEST_MONEY);
        String input = getUserInput();
        validateEmptyLine(input);

        return input;
    }

    public String readWinningNumber() {
        System.out.println(REQUEST_WINNING_NUMBER);
        String input = getUserInput();
        validateEmptyLine(input);

        return input;
    }

    public String readBonusNumber() {
        System.out.println(REQUEST_BONUS_NUMBER);
        String input = getUserInput();
        validateEmptyLine(input);

        return input;
    }

    private void validateEmptyLine(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 아무것도 입력하지 않았습니다.");
        }
    }

    private String getUserInput() {
        return Console.readLine();
    }
}
