package paranhaslett.gamebook.ui.panel;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.fragment.Text;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TextUI extends PanelUI {
    private static final long serialVersionUID = -1739742038591872687L;
    private static PanelUI panelUI;
    private final JTextArea txtDesc;
    private Text model;

    private TextUI() {

        JScrollPane scrollPane = new JScrollPane();

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> populateModel());
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
                Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPane,
                                                GroupLayout.DEFAULT_SIZE, 430,
                                                Short.MAX_VALUE)
                                        .addComponent(btnUpdate))
                        .addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
                Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
                                237, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addContainerGap(23, Short.MAX_VALUE)));

        txtDesc = new JTextArea();
        scrollPane.setViewportView(txtDesc);
        setLayout(groupLayout);
    }

    public static PanelUI getPanelUI() {
        if (panelUI == null) {
            panelUI = new TextUI();
        }
        return panelUI;
    }

    @Override
    public void populatePanel(Item modelItem) {
        model = (Text) modelItem;
        txtDesc.setText(((Text) modelItem).text);

    }

    @Override
    public void populateModel() {
        model.text = txtDesc.getText();
        Editor.getEd().update();

    }
}
