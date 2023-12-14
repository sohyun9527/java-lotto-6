package lotto.controller;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoSeller;
import lotto.domain.Prize;
import lotto.domain.ProfitCalculator;
import lotto.domain.WinningNumbers;
import lotto.util.ReadUntilValid;
import lotto.validation.Validation;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Lotto> tickets = purchaseLottoTickets();
        Lotto winningLotto = getWinningLotto();
        WinningNumbers winningNumbers = generateWinningNumbers(winningLotto);

        showPrizeResult(tickets, winningNumbers);
    }

    private List<Lotto> purchaseLottoTickets() {
        return ReadUntilValid.readUntilValidInput(() -> {
            LottoSeller seller = new LottoSeller(inputView.readPurchaseMoney());
            List<Lotto> tickets = seller.generateLottoTickets();
            outputView.printPurchaseResult(tickets, tickets.size());
            return tickets;
        });
    }

    private Lotto getWinningLotto() {
        return ReadUntilValid.readUntilValidInput(() -> {
            String input = inputView.readWinningNumber();
            List<String> divide = List.of(input.split(",", -1));
            List<Integer> winningNumber = new ArrayList<>();
            for (String s : divide) {
                Validation.validateContainSpace(s);
                winningNumber.add(Integer.parseInt(s));
            }
            return new Lotto(winningNumber);
        });
    }

    private WinningNumbers generateWinningNumbers(Lotto winningLotto) {
        return ReadUntilValid.readUntilValidInput(() -> {
            String bonusInput = inputView.readBonusNumber();
            return new WinningNumbers(winningLotto, bonusInput);
        });
    }

    private void showPrizeResult(List<Lotto> tickets, WinningNumbers winningNumbers) {
        EnumMap<Prize, Integer> prizeResult = winningNumbers.checkPrizeResult(tickets);
        outputView.printPrizeResult(prizeResult);

        ProfitCalculator calculator = new ProfitCalculator(prizeResult);
        outputView.printProfitRate(calculator.calculateProfit());
    }
}
