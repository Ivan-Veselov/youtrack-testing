package ru.spbau.bachelors2015.veselov.pages;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.bachelors2015.veselov.Utils;
import ru.spbau.bachelors2015.veselov.elements.Button;
import ru.spbau.bachelors2015.veselov.elements.TextBox;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CreateUserDialog {
    private final @NotNull WebDriver driver;

    private final @NotNull TextBox loginField;

    private final @NotNull TextBox passwordField;

    private final @NotNull TextBox passwordConfirmationField;

    private final @NotNull Button okButton;

    public CreateUserDialog(final @NotNull WebDriver driver) {
        this.driver = driver;

        loginField = new TextBox(Utils.getElementById(driver, "id_l.U.cr.login"));

        passwordField = new TextBox(Utils.getElementById(driver, "id_l.U.cr.password"));

        passwordConfirmationField =
            new TextBox(Utils.getElementById(driver, "id_l.U.cr.confirmPassword"));

        okButton = new Button(Utils.getElementById(driver, "id_l.U.cr.createUserOk"));
    }

    public @NotNull TextBox getLoginField() {
        return loginField;
    }

    public @NotNull TextBox getPasswordField() {
        return passwordField;
    }

    public @NotNull TextBox getPasswordConfirmationField() {
        return passwordConfirmationField;
    }

    public @NotNull Button getOkButton() {
        return okButton;
    }
}
