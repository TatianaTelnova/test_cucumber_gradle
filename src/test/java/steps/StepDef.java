package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Attachment;
import org.example.AtmPage;
import org.example.FaqPage;
import org.example.MainPage;
import org.assertj.core.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class StepDef extends BaseStepDef {
    private int elemCount;
    private final Set<String> windowHandles = new HashSet<>();

    private HashMap<String, Integer> paramMap = new HashMap<>();

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

    @Given("открыта страница с частыми вопросами")
    public void openFaqPage() {
        driver.get("https://www.bspb.ru/retail/faq");
    }

    @Given("открыта страница с адресами банкоматов")
    public void openAtmPage() {
        driver.get("https://www.bspb.ru/map?is=bankomats");
    }

    @When("ждем")
    public void waitWait() {
        System.out.println("Wait");
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

    @When("кликаю на {string}, выбираю {string}")
    public void goToPage(String nav, String elem) {
        new MainPage(driver).clickGoToFaq(nav, elem);
    }

    @When("кликаю {string}")
    public void clickEveryElem(String elem) {
        AtmPage ap = new AtmPage(driver);
        FaqPage fp = new FaqPage(driver);
        MainPage mp = new MainPage(driver);
        if (ap.checkMap(elem)) {
            ap.clickAtm(elem);
        } else if (fp.checkMap(elem)) {
            fp.clickFaq(elem);
        } else if (mp.checkMap(elem)) {
            mp.clickMain(elem);
        }
    }

    @When("считаю {string}")
    public void countEveryElems(String elems) {
        AtmPage ap = new AtmPage(driver);
        FaqPage fp = new FaqPage(driver);
        MainPage mp = new MainPage(driver);
        if (ap.checkMap(elems)) {
            elemCount = ap.countAtm(elems);
        } else if (fp.checkMap(elems)) {
            elemCount = fp.countFaq(elems);
        } else if (mp.checkMap(elems)) {
            elemCount = mp.countMain(elems);
        }
    }

    @When("сохраняю в {string}")
    public void storeAs(String key) {
        paramMap.put(key, elemCount);
    }

    @When("считаю {string} когда их меньше чем в {string}")
    public void resultMustBeLessThanInVal(String elem, String val) {
        elemCount = new FaqPage(driver).countFaqWithFilter(elem, paramMap.get(val));
    }

    @Then("результат больше {int}")
    public void resultMustBeGreaterThanNumber(int number) {
        Assertions.assertThat(elemCount).isGreaterThan(number);
    }

    @Then("результат равен {int}")
    public void resultEqualNumber(int number) {
        Assertions.assertThat(number).isEqualTo(elemCount);
    }

    @Then("{string} присутствует на странице")
    public void checkResult(String elem) {
        Assertions.assertThat(new MainPage(driver).checkExistMain(elem)).isTrue();
    }

    @Then("текст внутри {string} равен {string}")
    public void userName(String elem, String username) {
        Assertions.assertThat(new MainPage(driver).getTextMain(elem)).isEqualToIgnoringWhitespace(username);
    }

    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/resources", resourceName));
    }

    @Then("тест {int}")
    public void checkResult(int num) throws IOException {
        switch (num) {
            case 1:
                Assertions.assertThat(7).isEqualTo(7);
                getBytes("cat.txt");
                getBytes("parrot.jpg");
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
