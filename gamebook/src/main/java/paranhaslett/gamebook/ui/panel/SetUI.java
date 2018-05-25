package paranhaslett.gamebook.ui.panel;

import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.fragment.Set;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

public class SetUI extends PanelUI {
    private static PanelUI panelUI;
    private final JTextField textVariable;
    private final JLabel lblHeading;
    private final JTextField textValue;
    private final JTextField textText;
    private Set model;

    private SetUI() {
        lblHeading = new JLabel("Set");
        lblHeading.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblHeading.setIcon(new ImageIcon(SetUI.class
                .getResource("/icons/tree/set.png")));

        textVariable = new JTextField();
        textVariable.setColumns(10);

        JLabel lblVar = new JLabel("Variable:");
        lblVar.setLabelFor(textVariable);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> populateModel());

        textValue = new JTextField();
        textValue.setColumns(10);

        JLabel lblValue = new JLabel("Value:");

        JLabel lblText = new JLabel("");

        textText = new JTextField();
        textText.setColumns(10);

        JLabel lblText_1 = new JLabel("Text:");
        lblText_1.setLabelFor(textText);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout
                .setHorizontalGroup(groupLayout
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                                groupLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addComponent(
                                                                lblHeading)
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.TRAILING)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createParallelGroup(
                                                                                                                Alignment.LEADING)
                                                                                                        .addComponent(
                                                                                                                lblVar)
                                                                                                        .addComponent(
                                                                                                                lblText))
                                                                                        .addComponent(
                                                                                                lblText_1))
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING,
                                                                                                false)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                textVariable,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                lblValue)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                textValue,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                        .addComponent(
                                                                                                textText)
                                                                                        .addComponent(
                                                                                                btnUpdate))))
                                        .addContainerGap(184, Short.MAX_VALUE)));
        groupLayout
                .setVerticalGroup(groupLayout
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                                groupLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblHeading)
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                Alignment.BASELINE)
                                                        .addComponent(lblVar)
                                                        .addComponent(
                                                                textVariable,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblValue)
                                                        .addComponent(
                                                                textValue,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                Alignment.BASELINE)
                                                        .addComponent(
                                                                textText,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblText_1))
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addComponent(lblText)
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addComponent(btnUpdate)
                                        .addContainerGap(186, Short.MAX_VALUE)));
        setLayout(groupLayout);
    }

    public static PanelUI getPanelUI() {
        if (panelUI == null) {
            panelUI = new SetUI();
        }
        return panelUI;
    }

    @Override
    public void populatePanel(Item modelItem) {
        model = (Set) modelItem;
        textVariable.setText(model.var);
        textValue.setText(model.value);
        textText.setText(model.text);
        lblHeading.setText("Set " + model.var);

    }

    @Override
    public void populateModel() {
        if (textText.getText() == null || textText.getText().equals("")) {
            textText.setText("Set " + textVariable.getText() + " to "
                    + textValue.getText());
        }
        model.var = textVariable.getText();
        model.value = textValue.getText();
        model.text = textText.getText();
        lblHeading.setText("Go To " + model.var);

    }
}
