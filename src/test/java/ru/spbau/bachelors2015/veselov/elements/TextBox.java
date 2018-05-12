package ru.spbau.bachelors2015.veselov.elements;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.WebElement;

public class TextBox {
    private final @NotNull WebElement textBox;

    public TextBox(final @NotNull WebElement textBox) {
        this.textBox = textBox;
    }

    public void insertText(final @NotNull String text) {
        textBox.sendKeys(text);
    }
}
