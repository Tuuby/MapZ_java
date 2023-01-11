package guis.controller;

import guis.UpdateMapProperty;
import guis.view.SeedView;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeedController {

    private final SeedView view;
    private final UpdateMapProperty updater;

    public SeedController(SeedView view, UpdateMapProperty updater) {
        this.updater = updater;
        this.view = view;
        init();
    }

    private void init() {
        JTextField textField = view.getTextField();
        JButton randomSeedButton = view.getRandomSeedButton();
        JButton button = view.getButton();
        JLabel errorLabel = view.getErrorLabel();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            final Pattern regEx = Pattern.compile("-?\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
                errorLabel.setVisible(false);
            }
        });

        button.addActionListener(e -> {
            String text = textField.getText();
            if(! text.isBlank()) {
                try {
                    long value = Long.parseLong(text);
                    updater.update(value);
                } catch (NumberFormatException numberFormatException) {
                    errorLabel.setVisible(true);
                }
            }

        });

        randomSeedButton.addActionListener(e -> {
            long value = new Random().nextLong();
            textField.setText(String.valueOf(value));
            errorLabel.setVisible(false);
        });
    }
}
