package paranhaslett.gamebook.model;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.loadable.PageIO;
import paranhaslett.gamebook.ui.panel.PageUI;
import paranhaslett.gamebook.ui.panel.PanelUI;

import javax.swing.*;
import java.util.ArrayList;

public class Page implements Item {
    public static final Loadable loadable = new PageIO();
    private static Icon icon;
    public final ArrayList<Section> sections = new ArrayList<>();
    private final PanelUI panel = PageUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String id;
    private int pageNum = 1;

    public void update(Item item) {
        if (item instanceof Page) {
            ed.editorUI.updatePanel(panel, item);
        }
    }

    public boolean isDropOn(Item mi) {
        return (mi instanceof Section);
    }

    public String toString() {
        return id;
    }

    @Override
    public void add(Item to) {
        if (to instanceof Section) {
            sections.add((Section) to);
            ed.tree.addToSel(to);
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeMainLabel(String newLabel) {
        id = newLabel;
        ed.editorUI.updatePanel(panel, this);

    }

    @Override
    public void setup() {
        id = "" + pageNum;
        pageNum++;

    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/library.png");
        }
        return icon;
    }


}
