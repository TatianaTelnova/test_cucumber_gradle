package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.MainPage;
import org.junit.Assert;

public class StepDef extends BaseStepDef {
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
    }

    @Then("{string} присутствует на странице")
    public void checkResult(String elem) {
        Assert.assertTrue(new MainPage(driver).checkExistMain(elem));
    }
}
