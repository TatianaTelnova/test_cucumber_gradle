package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MainPage;
import org.assertj.core.api.Assertions;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class StepDef extends BaseStepDef {
    private final Set<String> windowHandles = new HashSet<>();

    @Before
    public void driverSetUp() {
        setUp();
    }

    @After
    public void driverTearDown() {
        tearDown();
    }

    @Given("открыта главная страница")
    public void openMainPage() {
        driver.get("https://www.bspb.ru/");
        windowHandles.add(driver.getWindowHandle());
    }

    @When("перехожу на открытую вкладку")
    public void goToTab() {
        wait.until(numberOfWindowsToBe(windowHandles.size() + 1));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandles.contains(windowHandle)) {
                windowHandles.add(windowHandle);
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @When("кликаю {string}")
    public void clickEveryElem(String elem) {
        new MainPage(driver).clickMain(elem);
    }

    @Then("текст внутри {string} равен {string}")
    public void userName(String elem, String username) {
        Assertions.assertThat(new MainPage(driver).getTextMain(elem)).isEqualToIgnoringWhitespace(username);
    }

    @Then("тест {int}")
    public void checkResult(int num) {
        switch (num) {
            case 1:
                Assertions.assertThat(7).isEqualTo(7);
                break;
            case 2:
                Assertions.assertThat("Что-то что").isEqualTo("что-то что");
                break;
            case 3:
                Assertions.assertThat("Что-то что").isEqualToIgnoringWhitespace("       Что-то что");
                break;
            case 4:
                driver.get("https://www.bspb.ru/");
                Assertions.assertThat(new MainPage(driver).checkExistMain("asdfghjk")).isTrue();
            default:
                break;
        }
    }
}
