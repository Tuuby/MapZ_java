package guis.view;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * creates a panel with label, textfield and slider
 * used for manipulating drawing of noise map
 */
public class SliderView {
    private final JPanel panel;
    private final JTextField sliderTextField;

    private final JLabel errorLabel;
    private final JSlider slider;

    public SliderView(String name) {
        Font font = new Font("SansSerif", Font.PLAIN, 12);

        // panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setMaximumSize(new Dimension(1000, 25));

        // label
        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(font);
        label.setBorder(new EmptyBorder(0, 0, 0, 10));
        topPanel.add(label, BorderLayout.LINE_START);

        // textfield
        sliderTextField = new JTextField();
        sliderTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        sliderTextField.setFont(font);
        sliderTextField.setMaximumSize(new Dimension(1000, 25));
        topPanel.add(sliderTextField, BorderLayout.CENTER);

        errorLabel = new JLabel("Invalid value, expected [0, 255]");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setFont(font);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);

        // slider
        slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 100); // value set by DSL
        slider.setAlignmentX(Component.CENTER_ALIGNMENT);
        slider.setMajorTickSpacing(85);
        slider.setMinorTickSpacing(32);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        sliderTextField.setText(String.valueOf(slider.getValue()));

        panel.add(topPanel);
        panel.add(errorLabel);
        panel.add(slider);
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public JTextField getTextField() {
        return this.sliderTextField;
    }

    public JSlider getSlider() {
        return this.slider;
    }

    public JLabel getErrorLabel() { return this.errorLabel; }
}
