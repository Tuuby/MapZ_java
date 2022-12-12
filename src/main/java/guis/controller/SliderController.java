package guis.controller;

import guis.UpdateMapProperty;
import guis.view.SliderView;

import javax.swing.*;

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

        // gets trigger when Enter key is pressed
        textField.addActionListener(e -> {
            String text = textField.getText();
            try {
                short value = Short.parseShort(text);
                if(value <= slider.getMaximum() && value >= slider.getMinimum()) {
                    slider.setValue(value);
                    updater.update(value);
                }
            } catch (NumberFormatException numberFormatException) {
                // TODO: error handling
            }
        });

        slider.addChangeListener(e -> {
            short value = (short) slider.getValue();
            textField.setText(String.valueOf(value));
            updater.update(value);
        });
    }
}
