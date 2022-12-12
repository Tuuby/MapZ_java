package guis.controller;

import guis.view.SliderView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SliderController {

    private final SliderView view;

    public SliderController(SliderView view) {
        this.view = view;
        init();
    }

    private void init() {
        JTextField textField = view.getTextField();
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("insert");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("remove");
            }

            // rarely called
            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("change");
            }
        });

        JSlider slider = view.getSlider();
        slider.addChangeListener(e -> System.out.println(slider.getValue()));
    }
}
