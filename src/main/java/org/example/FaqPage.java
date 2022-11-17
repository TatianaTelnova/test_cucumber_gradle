package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.HashMap;

public class FaqPage extends BasePage {

    private final HashMap<String, By> mapFaqPage = new HashMap<>() {
        {
            put("первая тема", By.id("accordion-button-17"));
            put("темы", By.className("css-1sfe36n"));
            put("вопросы", By.className("css-1cw6qg0"));
        }
    };

    public FaqPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkMap(String elem) {
        return mapFaqPage.containsKey(elem);
    }

    public void clickFaq(String elem) {
        clickElem(mapFaqPage.get(elem));
    }

    public int countFaq(String elem) {
        return countElems(mapFaqPage.get(elem));
    }

    public int countFaqWithFilter(String elem, Integer val) {
        wait.until((ExpectedCondition<Boolean>) driver -> countElems(mapFaqPage.get(elem)) != val);
        return countElems(mapFaqPage.get(elem));
    }
}
