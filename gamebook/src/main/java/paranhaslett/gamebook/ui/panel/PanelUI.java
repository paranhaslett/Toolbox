package paranhaslett.gamebook.ui.panel;

import paranhaslett.gamebook.model.Item;

import javax.swing.*;

public abstract class PanelUI extends JPanel {

    public abstract void populatePanel(Item modelItem);

    public abstract void populateModel();
}
