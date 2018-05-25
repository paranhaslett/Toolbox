package paranhaslett.gamebook.ui.panel;

import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.fragment.GoTo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

public class GoToUI extends PanelUI {
    private static PanelUI panelUI;
    private final JTextField textGoTo;
    private final JLabel lblHeading;
    private final JTextField textField;
    private GoTo model;

    private GoToUI() {
        lblHeading = new JLabel("Go To");
        lblHeading.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblHeading.setIcon(new ImageIcon(GoToUI.class
                .getResource("/icons/tree/goto.png")));

        textGoTo = new JTextField();
        textGoTo.setColumns(10);

        JLabel lblGoTo = new JLabel("Go To:");
        lblGoTo.setLabelFor(textGoTo);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> populateModel());

        textField = new JTextField();
        textField.setColumns(10);

        JLabel lblDescription = new JLabel("Description:");
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
                                                                        .addComponent(lblGoTo)
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                btnUpdate)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                textGoTo,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                lblDescription)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                textField,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                243,
                                                                                                                Short.MAX_VALUE)))))
                                        .addContainerGap()));
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
                                                        .addComponent(lblGoTo)
                                                        .addComponent(
                                                                textGoTo,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(
                                                                lblDescription)
                                                        .addComponent(
                                                                textField,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addComponent(btnUpdate)
                                        .addContainerGap(218, Short.MAX_VALUE)));
        setLayout(groupLayout);
    }

    public static PanelUI getPanelUI() {
        if (panelUI == null) {
            panelUI = new GoToUI();
        }
        return panelUI;
    }

    @Override
    public void populatePanel(Item modelItem) {
        model = (GoTo) modelItem;
        textGoTo.setText(model.to);
        textField.setText(model.text);
        lblHeading.setText("Go To " + model.to);

    }

    @Override
    public void populateModel() {
        if (textField.getText() == null || textField.getText().equals("")) {
            textField.setText("Go to section " + textGoTo.getText());
        }
        model.text = textField.getText();
        model.to = textGoTo.getText();
        lblHeading.setText("Go To " + model.to);
    }
}
