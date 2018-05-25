package paranhaslett.gamebook.ui.panel;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.libraryitem.Book;
import paranhaslett.gamebook.model.libraryitem.Series;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

public class SeriesUI extends PanelUI {
    private static PanelUI panelUI;
    private final JTextField textField;
    private Series model;

    /**
     * Create the panel.
     */
    private SeriesUI() {
        JLabel lblNewLabel = new JLabel("Title");

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> populateModel());

        JButton btnAddPage = new JButton("Add Book");
        btnAddPage.setIcon(new ImageIcon(SeriesUI.class.getResource("/icons/tree/book.png")));
        btnAddPage.addActionListener(e -> {
            Book book = new Book();
            book.setup();
            model.add(book);
        });

        JLabel lblGameBook = new JLabel("Series");
        lblGameBook.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGameBook.setIcon(new ImageIcon(SeriesUI.class.getResource("/icons/tree/series.png")));
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
                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(btnUpdate)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(btnAddPage)))))
                                .addContainerGap(16, GroupLayout.PREFERRED_SIZE))
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
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnUpdate)
                                        .addComponent(btnAddPage))
                                .addContainerGap(216, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

    }

    public static PanelUI getPanelUI() {
        if (panelUI == null) {
            panelUI = new SeriesUI();
        }
        return panelUI;
    }

    public void setup(String title) {
        textField.setText(title);
    }

    @Override
    public void populatePanel(Item modelItem) {
        textField.setText(((Series) modelItem).title);
        model = (Series) modelItem;

    }

    @Override
    public void populateModel() {
        model.title = textField.getText();
        Editor.getEd().update();
    }
}
