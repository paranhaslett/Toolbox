package paranhaslett.gamebook.model.fragment;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.GoToIO;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.model.Fragment;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.ui.panel.GoToUI;
import paranhaslett.gamebook.ui.panel.PanelUI;

import javax.swing.*;

public class GoTo implements Fragment {
    public static final Loadable loadable = new GoToIO();
    private static Icon icon;
    private final PanelUI panel = GoToUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String to;
    public String text;

    public void update(Item item) {
        if (item instanceof GoTo) {
            ed.editorUI.updatePanel(panel, item);
        }
    }

    @Override
    public void setup() {

    }

    public String toString() {
        return to;
    }

    @Override
    public void add(Item to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeMainLabel(String newLabel) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDropOn(Item item) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/goto.png");
        }
        return icon;
    }
}
