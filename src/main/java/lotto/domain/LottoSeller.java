package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.Lotto;

public class LottoSeller {
    private static final int LOTTO_PRICE = 1_000;
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;
    private final int money;

    public LottoSeller(String money) {
        this.money = getValidMoney(money);
    }

    // 숫자로만 이루어져있는지, 1000원으로 나누어 떨어지는지
    private int getValidMoney(String input) {
        validateOnlyDigit(input);
        int money = Integer.parseInt(input);
        validateHasRemain(money);

        return money;
    }

    public List<Lotto> generateLottoTickets() {
        int count = money / LOTTO_PRICE;
        List<Lotto> tickets = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<Integer> oneTicket = generateLotto();
            tickets.add(new Lotto(oneTicket));
        }

        return Collections.unmodifiableList(tickets);
    }

    public List<Integer> generateLotto() {
        return Randoms.pickUniqueNumbersInRange(START_NUMBER, END_NUMBER, NUMBER_COUNT);
    }


    private void validateOnlyDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자만 입력해주세요.");
        }
    }

    private void validateHasRemain(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력해주세요.");
        }
    }

}
