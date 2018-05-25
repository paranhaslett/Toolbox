package paranhaslett.gamebook.model.fragment.branch;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.IfIO;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.model.Fragment;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.ModelContainer;
import paranhaslett.gamebook.ui.panel.IfUI;
import paranhaslett.gamebook.ui.panel.PanelUI;

import javax.swing.*;
import java.util.ArrayList;

public class If implements ModelContainer, Fragment, Item {
    public static final Loadable loadable = new IfIO();
    private static Icon icon;
    public final ArrayList<Fragment> trueBranch = new ArrayList<>();
    private final PanelUI panel = IfUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String lhs;
    public String op;
    public String rhs;
    public String text;

    @Override
    public boolean isDropOn(Item mi) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub
    }

    public String toString() {
        return text;
    }

    public ArrayList<Fragment> getFragments() {
        return trueBranch;
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
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/if.png");
        }
        return icon;
    }
}
