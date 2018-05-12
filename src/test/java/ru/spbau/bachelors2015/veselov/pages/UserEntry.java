package ru.spbau.bachelors2015.veselov.pages;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import ru.spbau.bachelors2015.veselov.elements.Button;

import java.util.Optional;

public class UserEntry {
    private final @NotNull String login;

    private final @Nullable Button deleteButton;

    public UserEntry(final @NotNull String login, final @Nullable Button deleteButton) {
        this.login = login;
        this.deleteButton = deleteButton;
    }

    public @NotNull String getLogin() {
        return login;
    }

    public @NotNull Optional<Button> getDeleteButton() {
        if (deleteButton != null) {
            return Optional.of(deleteButton);
        } else {
            return Optional.empty();
        }
    }
}
