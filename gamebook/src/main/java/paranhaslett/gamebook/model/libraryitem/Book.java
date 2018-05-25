package paranhaslett.gamebook.model.libraryitem;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.BookIO;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Page;
import paranhaslett.gamebook.model.Section;
import paranhaslett.gamebook.ui.panel.GameBookUI;
import paranhaslett.gamebook.ui.panel.PageUI;
import paranhaslett.gamebook.ui.panel.PanelUI;
import paranhaslett.gamebook.ui.panel.SectionUI;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class Book implements Item {

    public static final Loadable loadable = new BookIO();
    private static Icon icon;
    public final ArrayList<Page> pages = new ArrayList<>();
    public final ArrayList<Section> freeSections = new ArrayList<>();
    private final PanelUI panel = GameBookUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String title;

    @Override
    public void add(Item added) {
        if (added instanceof Page) {
            pages.add((Page) added);
            ed.tree.addToSel(added);
            ed.editorUI.updatePanel(PageUI.getPanelUI(), added);
        }
        if (added instanceof Section) {
            freeSections.add((Section) added);
            ed.tree.addToSel(added);
            ed.editorUI.updatePanel(SectionUI.getPanelUI(), added);
        }
    }

    @Override
    public void changeMainLabel(String newLabel) {
        title = newLabel;
        ed.editorUI.updatePanel(panel, this);
    }

    @Override
    public boolean isDropOn(Item mi) {
        return (mi instanceof Page || mi instanceof Section);
    }

    @Override
    public void setup() {
        //TODO: Get setup from current Book template copy it across
        title = "New";
        TreePath path = ed.tree.getSelectLoc();
        ed.tree.addToPath(path, this);
    }

    public String toString() {
        //TODO ensure this is unique (add a number perhaps
        return title;
    }

    @Override
    public void update() {
        ed.editorUI.updatePanel(panel, this);
    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/book.png");
        }
        return icon;
    }
}
