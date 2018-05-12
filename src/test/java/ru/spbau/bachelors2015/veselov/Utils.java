package ru.spbau.bachelors2015.veselov;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
    public static @NotNull WebElement getElementById(
        final @NotNull WebDriver driver,
        final @NotNull String id
    ) {
        By byId = By.id(id);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byId));

        return driver.findElement(byId);
    }
}
