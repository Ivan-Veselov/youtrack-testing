package ru.spbau.bachelors2015.veselov.pages;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.bachelors2015.veselov.Utils;
import ru.spbau.bachelors2015.veselov.elements.Button;
import ru.spbau.bachelors2015.veselov.elements.TextBox;

public class LoginPage {
    private final @NotNull TextBox loginField;

    private final @NotNull TextBox passwordField;

    private final @NotNull Button loginButton;

    public LoginPage(final @NotNull WebDriver driver, final @NotNull String rootUrl) {
        String url = rootUrl + "/login";
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(url));

        loginField = new TextBox(driver.findElement(By.id("id_l.L.login")));

        passwordField = new TextBox(driver.findElement(By.id("id_l.L.password")));

        loginButton = new Button(driver.findElement(By.id("id_l.L.loginButton")));
    }

    public @NotNull TextBox getLoginField() {
        return loginField;
    }

    public @NotNull TextBox getPasswordField() {
        return passwordField;
    }

    public @NotNull Button getLoginButton() {
        return loginButton;
    }
}
