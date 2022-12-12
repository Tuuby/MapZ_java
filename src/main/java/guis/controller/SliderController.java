package guis.controller;

import guis.UpdateMapProperty;
import guis.view.SliderView;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SliderController {

    private final SliderView view;

    private final UpdateMapProperty updater;

    public SliderController(SliderView view, UpdateMapProperty updater) {
        this.updater = updater;
        this.view = view;
        init();
    }

    private void init() {
        JTextField textField = view.getTextField();
        JSlider slider = view.getSlider();
        JLabel errorLabel = view.getErrorLabel();

        // gets trigger when Enter key is pressed
        textField.addActionListener(e -> {
            String text = textField.getText();
            try {
                short value = Short.parseShort(text);
                if(value <= slider.getMaximum() && value >= slider.getMinimum()) {
                    slider.setValue(value);
                    updater.update(value);
                    errorLabel.setVisible(false);
                } else {
                    errorLabel.setVisible(true);
                }
            } catch (NumberFormatException numberFormatException) {
                errorLabel.setVisible(true);
            }
        });

        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            final Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        slider.addChangeListener(e -> {
            short value = (short) slider.getValue();
            textField.setText(String.valueOf(value));
            updater.update(value);
            errorLabel.setVisible(false);
        });
    }
}
