package ru.spbau.bachelors2015.veselov.pages;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.bachelors2015.veselov.elements.Button;

import java.util.List;
import java.util.stream.Collectors;

public class UsersPage {
    private final @NotNull WebDriver driver;

    private final @NotNull Button createUserButton;

    public UsersPage(final @NotNull WebDriver driver, final @NotNull String rootUrl) {
        this.driver = driver;

        String url = rootUrl + "/users";
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(url));

        createUserButton = new Button(driver.findElement(By.id("id_l.U.createNewUser")));
    }

    public @NotNull CreateUserDialog clickCreateUserButton() {
        createUserButton.click();
        return new CreateUserDialog(driver);
    }

    public @NotNull List<UserEntry> getUsersEntries() {
        return driver.findElements(
            By.cssSelector("table[class='table users-table'] tbody tr")
        ).stream().map(tableRow -> {
            WebElement loginWebElement = tableRow.findElement(By.cssSelector("td:first-child a"));

            Button button;
            try {
                WebElement deleteButtonWebElement = tableRow.findElement(
                    By.cssSelector("td:last-child a[cn='l.U.usersList.deleteUser']")
                );

                button = new Button(deleteButtonWebElement);
            } catch (NoSuchElementException e) {
                button = null;
            }

            return new UserEntry(loginWebElement.getText(), button);
        }).collect(Collectors.toList());
    }
}
