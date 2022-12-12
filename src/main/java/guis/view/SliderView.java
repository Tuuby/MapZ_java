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
    private final JLabel label;
    private final JTextField textField;
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
        label = new JLabel(name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(font);
        label.setBorder(new EmptyBorder(0, 0, 0, 10));
        topPanel.add(label, BorderLayout.LINE_START);

        // textfield
        textField = new JTextField();
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setFont(font);
        textField.setMaximumSize(new Dimension(1000, 25));
        topPanel.add(textField, BorderLayout.CENTER);

        // slider
        slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 100); // value set by DSL
        slider.setAlignmentX(Component.CENTER_ALIGNMENT);
        slider.setMajorTickSpacing(85);
        slider.setMinorTickSpacing(32);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        panel.add(topPanel);
        panel.add(slider);
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public JTextField getTextField() {
        return this.textField;
    }

    public JSlider getSlider() {
        return this.slider;
    }
}
