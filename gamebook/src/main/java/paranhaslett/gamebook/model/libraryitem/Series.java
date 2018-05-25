package paranhaslett.gamebook.model.libraryitem;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.loadable.SeriesIO;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.ui.panel.PanelUI;
import paranhaslett.gamebook.ui.panel.SeriesUI;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class Series implements Item {
    public static final Loadable loadable = new SeriesIO();
    private static Icon icon;
    public final ArrayList<Book> books = new ArrayList<>();
    private final PanelUI panel = SeriesUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String title;

    public String toString() {
        return title;
    }

    public void update() {
        ed.editorUI.updatePanel(panel, this);
    }

    public boolean isDropOn(Item mi) {
        return (mi instanceof Book);
    }

    @Override
    public void setup() {
        title = "New";
        TreePath path = ed.tree.getSelectLoc();
        ed.tree.addToPath(path, this);
    }

    @Override
    public void add(Item added) {
        if (added instanceof Book) {
            books.add((Book) added);
            ed.tree.addToSel(added);
            added.update();

        }

    }

    @Override
    public void changeMainLabel(String newLabel) {
        title = newLabel;
        ed.editorUI.updatePanel(panel, this);

    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/series.png");
        }
        return icon;
    }


}
