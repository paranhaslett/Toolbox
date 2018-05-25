package paranhaslett.gamebook.model;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.loadable.SectionIO;
import paranhaslett.gamebook.model.fragment.GoTo;
import paranhaslett.gamebook.model.fragment.Text;
import paranhaslett.gamebook.ui.panel.PanelUI;
import paranhaslett.gamebook.ui.panel.SectionUI;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class Section implements ModelContainer {

    public static final Loadable loadable = new SectionIO();
    private static long sectionNum = 1;
    private static Icon icon;
    public final ArrayList<Fragment> fragments = new ArrayList<>();
    private final PanelUI panel = SectionUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String title;
    public String id;
    public GoTo goToId;

    @Override
    public boolean isDropOn(Item mi) {
        return (mi instanceof Fragment || mi instanceof Section);
    }

    @Override
    public void setup() {
        id = "" + sectionNum;
        title = "< New >";

        Text desc = new Text();
        desc.setup();
        fragments.add(desc);
        GoTo sectionGoTo = new GoTo();
        sectionGoTo.setup();
        goToId = sectionGoTo;
        TreePath path = ed.tree.getSelectLoc();
        ed.tree.addToPath(path, this);
        path = ed.tree.getSelectLoc();
        ed.tree.addToPath(path, desc);
        ed.tree.addToPath(path, sectionGoTo);
        sectionNum++;
    }

    public ArrayList<Fragment> getFragments() {
        return fragments;
    }

    public String toString() {
        if (title != null) {
            return title;
        }
        return "" + id;
    }

    @Override
    public void add(Item added) {
        if (added instanceof Fragment) {
            if (added instanceof GoTo) {
                goToId = (GoTo) added;
            } else {
                fragments.add((Fragment) added);
            }
            ed.tree.addToSel(added);
            // change to fragment controller
        }
    }

    @Override
    public void update() {
        ed.editorUI.updatePanel(panel, this);

    }

    @Override
    public void changeMainLabel(String newLabel) {
        title = newLabel;
        ed.editorUI.updatePanel(panel, this);

    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/section.png");
        }
        return icon;
    }
}
