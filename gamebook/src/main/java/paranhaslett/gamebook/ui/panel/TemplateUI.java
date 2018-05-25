package paranhaslett.gamebook.ui.panel;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Page;
import paranhaslett.gamebook.model.Section;
import paranhaslett.gamebook.model.fragment.GoTo;
import paranhaslett.gamebook.model.fragment.Set;
import paranhaslett.gamebook.model.fragment.Text;
import paranhaslett.gamebook.model.fragment.branch.Chance;
import paranhaslett.gamebook.model.fragment.branch.Choice;
import paranhaslett.gamebook.model.fragment.branch.If;
import paranhaslett.gamebook.model.libraryitem.Book;
import paranhaslett.gamebook.model.libraryitem.Series;
import paranhaslett.gamebook.model.libraryitem.Template;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

public class TemplateUI extends PanelUI {
    private static final long serialVersionUID = 5259672660377230929L;
    private static PanelUI panelUI;
    private final JTextField textField;
    private Template model;

    /**
     * Create the panel.
     */
    private TemplateUI() {
        JLabel lblNewLabel = new JLabel("Title");

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> populateModel());

        final JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(
                new String[]{"Template", "Series", "Book", "Page", "Section", "Text", "GoTo", "Set", "Chance", "Choice", "If", "Var"}));

        JButton btnAddPage = new JButton("Add");
        btnAddPage.setIcon(null);
        btnAddPage.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            Item item = null;
            if (selected != null) {
                if (selected.equals("Book")) {
                    item = new Book();
                }
                if (selected.equals("Series")) {
                    item = new Series();
                }
                if (selected.equals("Template")) {
                    item = new Template();
                }
                if (selected.equals("Page")) {
                    item = new Page();
                }
                if (selected.equals("Section")) {
                    item = new Section();
                }
                if (selected.equals("Text")) {
                    item = new Text();
                }
                if (selected.equals("GoTo")) {
                    item = new GoTo();
                }
                if (selected.equals("Set")) {
                    item = new Set();
                }
                if (selected.equals("Chance")) {
                    item = new Chance();
                }
                if (selected.equals("Choice")) {
                    item = new Choice();
                }
                if (selected.equals("If")) {
                    item = new If();
                }
                //if (selected.equals("Var")){
                //	item = new Var();
                //}
                if (item != null) {
                    item.changeMainLabel("< New >");
                    model.add(item);
                }
            }


        });

        JLabel lblGameBook = new JLabel("Template");
        lblGameBook.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGameBook.setIcon(new ImageIcon(TemplateUI.class
                .getResource("/icons/tree/template.png")));

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
                                                                lblGameBook)
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addComponent(
                                                                                lblNewLabel)
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                textField,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                400,
                                                                                                Short.MAX_VALUE)
                                                                                        .addComponent(
                                                                                                btnUpdate)
                                                                                        .addComponent(
                                                                                                comboBox,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                142,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                btnAddPage))))
                                        .addContainerGap(16,
                                                GroupLayout.PREFERRED_SIZE)));
        groupLayout
                .setVerticalGroup(groupLayout
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                                groupLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblGameBook)
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                Alignment.BASELINE)
                                                        .addComponent(
                                                                lblNewLabel)
                                                        .addComponent(
                                                                textField,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addComponent(btnUpdate)
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addComponent(comboBox,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addComponent(btnAddPage)
                                        .addContainerGap(161, Short.MAX_VALUE)));
        setLayout(groupLayout);

    }

    public static PanelUI getPanelUI() {
        if (panelUI == null) {
            panelUI = new TemplateUI();
        }
        return panelUI;
    }

    public void setup(String title) {
        textField.setText(title);
    }

    @Override
    public void populatePanel(Item modelItem) {
        textField.setText(((Template) modelItem).title);
        model = (Template) modelItem;

    }

    @Override
    public void populateModel() {
        model.title = textField.getText();
        Editor.getEd().update();
    }
}
