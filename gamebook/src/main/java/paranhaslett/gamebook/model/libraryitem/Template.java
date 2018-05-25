package paranhaslett.gamebook.model.libraryitem;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.loadable.TemplateIO;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.ui.panel.PanelUI;
import paranhaslett.gamebook.ui.panel.TemplateUI;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class Template implements Item {
    public static final Loadable loadable = new TemplateIO();
    private static Icon icon;
    public final ArrayList<Item> items = new ArrayList<>();
    private final PanelUI panel = TemplateUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String title;

    public String toString() {
        return title;
    }

    public void update() {
        ed.editorUI.updatePanel(panel, this);
    }

    public boolean isDropOn(Item mi) {
        return true;
    }

    @Override
    public void add(Item to) {
        if (to instanceof Book) {
            items.add(to);
            ed.tree.addToSel(to);
            to.update();

        }

    }

    @Override
    public void setup() {
        title = "New";
        TreePath path = ed.tree.getSelectLoc();
        ed.tree.addToPath(path, this);

    }

    @Override
    public void changeMainLabel(String newLabel) {
        title = newLabel;
        ed.editorUI.updatePanel(panel, this);

    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/template.png");
        }
        return icon;
    }
}
