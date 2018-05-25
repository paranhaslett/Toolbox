package paranhaslett.gamebook.model.fragment;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.loadable.TextIO;
import paranhaslett.gamebook.model.Fragment;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.ui.panel.PanelUI;
import paranhaslett.gamebook.ui.panel.TextUI;

import javax.swing.*;

public class Text implements Fragment {
    public static final Loadable loadable = new TextIO();
    private static Icon icon;
    private final PanelUI panel = TextUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String text;

    public String toString() {
        return "Text";
    }

    @Override
    public boolean isDropOn(Item mi) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setup() {

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
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/desc.png");
        }
        return icon;
    }

    @Override
    public void changeMainLabel(String newLabel) {
        // TODO Auto-generated method stub

    }

}
