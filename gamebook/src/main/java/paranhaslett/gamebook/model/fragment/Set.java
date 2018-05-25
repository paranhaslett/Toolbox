package paranhaslett.gamebook.model.fragment;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.loadable.SetIO;
import paranhaslett.gamebook.model.Fragment;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.ui.panel.PanelUI;
import paranhaslett.gamebook.ui.panel.SetUI;

import javax.swing.*;

public class Set implements Fragment {
    public static final Loadable loadable = new SetIO();
    private static Icon icon;
    private final PanelUI panel = SetUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String var;
    public String value;
    public String text;

    @Override
    public boolean isDropOn(Item mi) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setup() {

    }

    public String toString() {
        return text;
    }

    @Override
    public void add(Item to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        ed.editorUI.updatePanel(panel, this);

    }

    @Override
    public void changeMainLabel(String newLabel) {
        // TODO Auto-generated method stub

    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/set.png");
        }
        return icon;
    }
}
