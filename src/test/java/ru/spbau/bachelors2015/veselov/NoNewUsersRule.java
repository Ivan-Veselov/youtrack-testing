package ru.spbau.bachelors2015.veselov;

import com.sun.istack.internal.NotNull;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import ru.spbau.bachelors2015.veselov.elements.Button;
import ru.spbau.bachelors2015.veselov.pages.UsersPage;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class UnableToDeleteUserException extends RuntimeException {}

public class NoNewUsersRule implements TestRule {
    private final @NotNull WebDriver driver;

    private final @NotNull String rootUrl;

    public NoNewUsersRule(final @NotNull WebDriver driver, final @NotNull String rootUrl) {
        this.driver = driver;
        this.rootUrl = rootUrl;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                UsersPage usersPage;
                Set<String> logins = new HashSet<>();

                usersPage = new UsersPage(driver, rootUrl);
                usersPage.getUsersEntries().forEach(entry -> logins.add(entry.getLogin()));

                try {
                    base.evaluate();
                } finally {
                    usersPage = new UsersPage(driver, rootUrl);
                    usersPage.getUsersEntries().forEach(entry -> {
                        if (!logins.contains(entry.getLogin())) {
                            Optional<Button> button = entry.getDeleteButton();

                            if (!button.isPresent()) {
                                throw new UnableToDeleteUserException();
                            }

                            button.get().click();
                            driver.switchTo().alert().accept();
                        }
                    });
                }
            }
        };
    }
}
