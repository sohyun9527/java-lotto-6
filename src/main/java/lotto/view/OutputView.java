package lotto.view;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Prize;

public class OutputView {
    private static final String PURCHASE_COUNT = "%d개를 구매했습니다.";
    private static final String PRIZE_RESULT = "당첨 통계";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String RESULT_DETAIL = "%d개 일치 (%s원) - %d개";
    private static final String BONUS_DETAIL = "%d개 일치, 보너스 볼 일치 (%s원) - %d개";
    private static final String PROFIT_RESULT = "총 수익률은 %.1f%%입니다.";


    public void printPrizeResult(EnumMap<Prize, Integer> prizeResult) {
        printResultTitle();
        for (Map.Entry<Prize, Integer> pair : prizeResult.entrySet()) {
            String message = String.format(RESULT_DETAIL, pair.getKey().getMatchCount(),
                    moneyFormatter(pair.getKey().getPrizeMoney()), pair.getValue());
            if (pair.getKey() == Prize.SECOND) {
                message = String.format(BONUS_DETAIL, pair.getKey().getMatchCount(),
                        moneyFormatter(pair.getKey().getPrizeMoney()), pair.getValue());
            }
            if (pair.getKey() != Prize.NONE) {
                System.out.println(message);
            }
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.printf((PROFIT_RESULT) + LINE_SEPARATOR, profitRate);
    }

    private String moneyFormatter(long prizeMoney) {
        NumberFormat format = NumberFormat.getInstance(Locale.KOREA);
        return format.format(prizeMoney);
    }

    public void printResultTitle() {
        System.out.println(PRIZE_RESULT);
        System.out.println("---");
    }

    public void printPurchaseResult(List<Lotto> tickets, int count) {
        printPurchaseCount(count);
        for (Lotto lotto : tickets) {
            printLottoNumbers(lotto.getNumbers());
        }
        System.out.println(LINE_SEPARATOR);
    }

    public void printLottoNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
        System.out.println(numbers);
    }

    public void printPurchaseCount(int count) {
        System.out.printf(LINE_SEPARATOR + (PURCHASE_COUNT) + LINE_SEPARATOR, count);
    }
}
