package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;

public class MainPage extends BasePage {
    private HashMap<String, By> mapMainPage = new HashMap<>() {
        {
            put("Войти", By.className("css-sy2ljg"));
            put("Связаться с нами", By.className("css-ndfch2"));
            put("адреса банкоматов", By.className("css-uxn78q"));
            put("элементы контента", By.xpath("//*[contains(@id,'app-wrapper')]/main/div/div"));
            put("Карты", By.id("popover-trigger-4"));
            put("Частые вопросы", By.xpath("//*[contains(@href,'/retail/faq')]"));
            put("Demo", By.xpath("//*[contains(@href,'https://idemo.bspb.ru')]"));
            put("Войти в лк", By.id("login-button"));
            put("Войти синяя", By.id("login-otp-button"));
            put("Имя пользователя", By.id("representee-name"));
        }
    };

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkMap(String elem) {
        return mapMainPage.containsKey(elem);
    }

    public boolean checkExistMain(String elem) {
        return checkExist(mapMainPage.get(elem));
    }
}