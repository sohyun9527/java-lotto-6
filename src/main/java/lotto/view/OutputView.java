package lotto.view;

import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;

public class OutputView {
    private static final String PURCHASE_COUNT = "%d개를 구매했습니다.";
    private static final String PRIZE_RESULT = "당첨 통계";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String RESULT_DETAIL = "%d개 일치 (%s원) - %d개";
    private static final String BONUS_DETAIL = "%d개 일치, 보너스 볼 일치 (%s원) - %개";
    private static final String PROFIT_RESULT = "총 수익률은 %%입니다.";

    public void printPurchaseResult(List<Lotto> tickets, int count) {
        printPurchaseCount(count);
        for (Lotto lotto : tickets) {
            printLottoNumbers(lotto.getNumbers());
        }
    }

    public void printLottoNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
        System.out.println(numbers);
    }

    public void printPurchaseCount(int count) {
        System.out.printf((PURCHASE_COUNT) + LINE_SEPARATOR, count);
    }
}
