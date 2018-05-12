package ru.spbau.bachelors2015.veselov;

import com.sun.istack.internal.NotNull;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.spbau.bachelors2015.veselov.pages.CreateUserDialog;
import ru.spbau.bachelors2015.veselov.pages.LoginPage;
import ru.spbau.bachelors2015.veselov.pages.UserEntry;
import ru.spbau.bachelors2015.veselov.pages.UsersPage;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNot.not;

public class LoginFieldTest {
    private static final @NotNull WebDriver driver = new ChromeDriver();

    private static final @NotNull String rootUrl = "http://localhost:8080";

    @Rule
    public final @NotNull NoNewUsersRule noNewUsersRule = new NoNewUsersRule(driver, rootUrl);

    @BeforeClass
    public static void loginIntoYoutrack() {
        final @NotNull String userName = "root";
        final @NotNull String userPassword = "root";

        LoginPage loginPage = new LoginPage(driver, rootUrl);
        loginPage.getLoginField().insertText(userName);
        loginPage.getPasswordField().insertText(userPassword);
        loginPage.getLoginButton().click();
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }

    @Test
    public void correctLogin() {
        String login = generateCorrectLogin();

        List<String> pastLogins = getUsersLogins();
        assertThat(pastLogins, not(hasItem(login)));

        createUserWithLogin(login);

        List<String> newLogins = getUsersLogins();
        assertThat(newLogins, hasSize(pastLogins.size() + 1));
        assertThat(newLogins, hasItem(login));
    }

    @Test
    public void doubleRegistration() {
        String login = generateCorrectLogin();

        List<String> pastLogins = getUsersLogins();
        assertThat(pastLogins, not(hasItem(login)));

        createUserWithLogin(login);

        List<String> intermediateLogins = getUsersLogins();
        assertThat(intermediateLogins, hasSize(pastLogins.size() + 1));
        assertThat(intermediateLogins, hasItem(login));

        createUserWithLogin(login);

        List<String> newLogins = getUsersLogins();
        assertThat(newLogins, hasSize(pastLogins.size() + 1));
        assertThat(newLogins, hasItem(login));
    }

    @Test
    public void emptyLogin() {
        negativeTest("");
    }

    @Test
    public void loginWithSpace() {
        negativeTest("a ");
    }

    private void negativeTest(final @NotNull String login) {
        List<String> pastLogins = getUsersLogins();
        assertThat(pastLogins, not(hasItem(login)));

        createUserWithLogin(login);

        List<String> newLogins = getUsersLogins();
        assertThat(newLogins, hasSize(pastLogins.size()));
        assertThat(newLogins, not(hasItem(login)));
    }

    private @NotNull List<String> getUsersLogins() {
        return new UsersPage(driver, rootUrl).getUsersEntries()
                                             .stream()
                                             .map(UserEntry::getLogin)
                                             .collect(Collectors.toList());
    }

    private void createUserWithLogin(final @NotNull String login) {
        createUser(login, "0");
    }

    private void createUser(final @NotNull String login, final @NotNull String password) {
        UsersPage usersPage = new UsersPage(driver, rootUrl);
        CreateUserDialog dialog = usersPage.clickCreateUserButton();

        dialog.getLoginField().insertText(login);
        dialog.getPasswordField().insertText(password);
        dialog.getPasswordConfirmationField().insertText(password);
        dialog.getOkButton().click();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private @NotNull String generateCorrectLogin() {
        Random random = new Random();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            builder.append((char) ('a' + random.nextInt(26)));
        }

        return builder.toString();
    }
}
