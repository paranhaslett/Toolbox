package paranhaslett.gamebook.model;

import paranhaslett.gamebook.Editor;
import paranhaslett.gamebook.loadable.LibraryIO;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.loader.Loader;
import paranhaslett.gamebook.model.libraryitem.Book;
import paranhaslett.gamebook.model.libraryitem.Series;
import paranhaslett.gamebook.model.libraryitem.Template;
import paranhaslett.gamebook.ui.panel.LibraryUI;
import paranhaslett.gamebook.ui.panel.PanelUI;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Library implements Item {
    public static final Loadable loadable = new LibraryIO();
    private static final PanelUI panel = LibraryUI.getPanelUI();
    private static Icon icon;
    public final ArrayList<Item> items = new ArrayList<>();
    private Editor ed = Editor.getEd();

    @Override
    public void add(Item item) {
        if (isDropOn(item)) {
            items.add(item);
        }
    }

    public void saveLibrary() {
        File file = new File("./library-save.xml");
        Loader loader = Editor.getEd().getLoader();
        if (loader != null) {
            loader.save(file, Editor.getEd().library);
        } else {
            throw new RuntimeException(
                    "Bad Programmer: File chooser selected wrong file type");
        }
    }

    public void setup(Editor editor) {
        ed = editor;
        setup();
    }

    public void setup() {
        File file = new File("./library.xml");
        Loader loader = Editor.getEd().getLoader();
        if (loader != null) {
            ed.library = new Library();
            loader.load(file, ed.library);
        }
        ed.library.update();
        ed.setupLibraryTree();
    }

    @Override
    public String toString() {
        return "Game Book Library";
    }

    @Override
    public void update() {

        ed.editorUI.updatePanel(panel, this);
        //ed.setupLibraryTree();
    }

    @Override
    public void changeMainLabel(String newLabel) {
        // The library label cannot be changed

    }

    @Override
    public boolean isDropOn(Item item) {
        return (item instanceof Book || item instanceof Series || item instanceof Template);
    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/library.png");
        }
        return icon;
    }


}
