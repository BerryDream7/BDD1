package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id='error-message']");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTansfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTansfer, cardInfo);
    }

    private void makeTransfer(String amountToTansfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTansfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expestedText) {
        errorMessage.shouldHave(exactText(expestedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
