package ru.spbau.bachelors2015.veselov.elements;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.WebElement;

public class Button {
    private final @NotNull WebElement button;

    public Button(final @NotNull WebElement button) {
        this.button = button;
    }

    public void click() {
        button.click();
    }
}
