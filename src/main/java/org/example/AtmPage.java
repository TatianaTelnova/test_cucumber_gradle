package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class AtmPage extends BasePage {
    private final HashMap<String, By> mapAtmPage = new HashMap<>() {
        {
            put("Списком", By.className("css-1frjy8u"));
            put("адреса банкоматов", By.className("css-uxn78q"));
        }
    };

    public boolean checkMap(String elem) {
        return mapAtmPage.containsKey(elem);
    }

    public AtmPage(WebDriver driver) {
        super(driver);
    }

    public void clickAtm(String elem) {
        clickElem(mapAtmPage.get(elem));
    }

    public int countAtm(String elem) {
        return countElems(mapAtmPage.get(elem));
    }
}
