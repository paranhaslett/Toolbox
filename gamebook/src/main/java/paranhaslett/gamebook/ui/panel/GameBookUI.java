package paranhaslett.gamebook.ui.panel;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Page;
import paranhaslett.gamebook.model.Section;
import paranhaslett.gamebook.model.libraryitem.Book;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

public class GameBookUI extends PanelUI {
    private static PanelUI panelUI;
    private final JTextField textField;
    private Book model;

    /**
     * Create the panel.
     */
    private GameBookUI() {
        JLabel lblNewLabel = new JLabel("Title");

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> populateModel());

        JButton btnAddSection = new JButton("Add Section");
        btnAddSection.setIcon(new ImageIcon(GameBookUI.class
                .getResource("/icons/tree/section.png")));
        btnAddSection.addActionListener(e -> {
            // Controller controller = model.getController();
            Section section = new Section();
            section.setup();
        });

        JButton btnAddPage = new JButton("Add Page");
        btnAddPage.setIcon(new ImageIcon(GameBookUI.class
                .getResource("/icons/tree/page.png")));
        btnAddPage.addActionListener(e -> {
            Page page = new Page();
            page.setup();
            model.add(page);
        });

        JLabel lblGameBook = new JLabel("Game Book");
        lblGameBook.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGameBook.setIcon(new ImageIcon(GameBookUI.class
                .getResource("/icons/tree/book.png")));

        JScrollPane scrollPane = new JScrollPane();
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblGameBook)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblNewLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(btnUpdate)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(btnAddPage)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(btnAddSection))
                                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblGameBook)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnUpdate)
                                        .addComponent(btnAddPage)
                                        .addComponent(btnAddSection))
                                .addContainerGap())
        );
        setLayout(groupLayout);

    }

    public static PanelUI getPanelUI() {
        if (panelUI == null) {
            panelUI = new GameBookUI();
        }
        return panelUI;
    }

    public void setup(String title) {
        textField.setText(title);
    }

    @Override
    public void populatePanel(Item modelItem) {
        textField.setText(((Book) modelItem).title);
        model = (Book) modelItem;

    }

    @Override
    public void populateModel() {
        model.title = textField.getText();
        Editor.getEd().update();
    }
}
