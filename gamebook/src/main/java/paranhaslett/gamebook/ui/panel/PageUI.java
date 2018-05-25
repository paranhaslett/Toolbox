package paranhaslett.gamebook.ui.panel;

import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Page;
import paranhaslett.gamebook.model.Section;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

public class PageUI extends PanelUI {
    private static final long serialVersionUID = -6099292917735976714L;
    private static PanelUI panelUI;
    private final JTextField textNumber;
    private final JLabel lblHeading;
    private Page model;

    /**
     * Create the panel.
     */
    private PageUI() {

        JButton btnAddSection = new JButton("Add Section");
        btnAddSection.setIcon(new ImageIcon(PageUI.class
                .getResource("/icons/tree/section.png")));
        btnAddSection.addActionListener(e -> {
            // Controller controller = Editor.getEd().getController("Page");
            Section section = new Section();
            section.setup();
        });

        lblHeading = new JLabel("Page");
        lblHeading.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblHeading.setIcon(new ImageIcon(PageUI.class
                .getResource("/icons/tree/page.png")));

        textNumber = new JTextField();
        textNumber.setColumns(10);

        JLabel lblNumber = new JLabel("Number:");

        JButton btnUpdate = new JButton("Update");
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
                                                                        .addComponent(
                                                                                lblNumber)
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                btnUpdate)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                btnAddSection))
                                                                                        .addComponent(
                                                                                                textNumber,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))))
                                        .addContainerGap(213, Short.MAX_VALUE)));
        groupLayout
                .setVerticalGroup(groupLayout
                        .createParallelGroup(Alignment.TRAILING)
                        .addGroup(
                                Alignment.LEADING,
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
                                                        .addComponent(lblNumber)
                                                        .addComponent(
                                                                textNumber,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                Alignment.BASELINE)
                                                        .addComponent(btnUpdate)
                                                        .addComponent(
                                                                btnAddSection))
                                        .addContainerGap(216, Short.MAX_VALUE)));
        setLayout(groupLayout);

    }

    public static PanelUI getPanelUI() {
        if (panelUI == null) {
            panelUI = new PageUI();
        }
        return panelUI;
    }

    @Override
    public void populatePanel(Item modelItem) {
        model = (Page) modelItem;
        lblHeading.setText("Page " + model.id);
        textNumber.setText(model.id);

    }

    @Override
    public void populateModel() {
        model.id = textNumber.getText();
    }
}
