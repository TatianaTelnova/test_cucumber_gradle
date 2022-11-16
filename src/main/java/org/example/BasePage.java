package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected boolean checkExist(By elem) {
        try {
            WebElement we = getElem(elem);
            int deltaY = we.getRect().y;
            Actions action = new Actions(driver);
            action.scrollByAmount(0, deltaY).perform();
            driver.findElement(elem);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void clickElem(By elem) {
        scroll(elem);
        driver.findElement(elem).click();
    }

    protected WebElement getElem(By elem) {
        wait.until(elementToBeClickable(elem));
        return driver.findElement(elem);
    }

    protected String getText(By elem) {
        return driver.findElement(elem).getAttribute("innerText");
    }

    private void scroll(By elem) {
        WebElement we = getElem(elem);
        int deltaY = we.getRect().y;
        Actions action = new Actions(driver);
        action.scrollByAmount(0, deltaY).perform();
    }
}


