package paranhaslett.gamebook.ui.tree;

import paranhaslett.gamebook.model.Fragment;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Library;
import paranhaslett.gamebook.model.ModelContainer;
import paranhaslett.gamebook.model.Page;
import paranhaslett.gamebook.model.Section;
import paranhaslett.gamebook.model.libraryitem.Book;
import paranhaslett.gamebook.model.libraryitem.Series;
import paranhaslett.gamebook.model.libraryitem.Template;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class TreeUI extends JTree {
    private static final long serialVersionUID = -4252742793844024659L;
    private final TreeRendererUI treeRenderer = new TreeRendererUI();
    private Item selection;
    private TreePath selectedPath;

    public TreeUI() {
        setDropMode(DropMode.ON_OR_INSERT);
        setDragEnabled(true);
        setTransferHandler(new TreeTransferHandler());
        setCellRenderer(treeRenderer);
        addTreeSelectionListener(e -> {
            TreeNodeUI node = (TreeNodeUI) getLastSelectedPathComponent();
            if (node == null) {
                return;
            } // nothing is selected

            Object nodeInfo = node.getUserObject();
            if (nodeInfo instanceof Item) {
                selectedPath = new TreePath(node.getPath());
                setSelectionPath(selectedPath);
                selection = (Item) nodeInfo;
                selection.update();

            }
        });
        setEditable(true);
        new TreePopupUI(this);
    }

    public TreeRendererUI getTreeRenderer() {
        return treeRenderer;
    }

    public void setup(Library lib) {
        TreeNodeUI library = new TreeNodeUI(lib);

        for (Item item : lib.items) {
            TreeNodeUI itemNode = null;
            if (item instanceof Book) {
                itemNode = book((Book) item);
            }
            if (item instanceof Series) {
                itemNode = series((Series) item);
            }
            if (item instanceof Template) {
                itemNode = template((Template) item);
            }
            library.add(itemNode);
        }

        setModel(new DefaultTreeModel(library));
        selectedPath = new TreePath(library.getPath());
        setSelectionPath(selectedPath);
        selection = lib;
    }

    private TreeNodeUI series(Series series) {
        TreeNodeUI seriesNode = new TreeNodeUI(series);

        for (Item item : series.books) {
            TreeNodeUI itemNode = book((Book) item);
            seriesNode.add(itemNode);
        }
        setModel(new DefaultTreeModel(seriesNode));
        selectedPath = new TreePath(seriesNode.getPath());
        setSelectionPath(selectedPath);
        selection = series;
        return seriesNode;
    }

    private TreeNodeUI template(Template template) {
        TreeNodeUI templateNode = new TreeNodeUI(template);

        for (Item item : template.items) {
            TreeNodeUI newNode = null;
            if (item instanceof Book) {
                newNode = book((Book) item);
            }
            if (item instanceof Series) {
                newNode = series((Series) item);
            }
            if (item instanceof Template) {
                newNode = template((Template) item);
            }
            //TODO the others
            if (newNode != null) {
                templateNode.add(newNode);
            }
        }
        setModel(new DefaultTreeModel(templateNode));
        selectedPath = new TreePath(templateNode.getPath());
        setSelectionPath(selectedPath);
        selection = template;
        return templateNode;
    }

    private TreeNodeUI book(Book gameBook) {
        TreeNodeUI root = new TreeNodeUI(gameBook);
        for (Page page : gameBook.pages) {
            TreeNodeUI pageNode = new TreeNodeUI(page);
            for (Section section : page.sections) {
                TreeNodeUI sectionNode = fragments(section);
                if (section.goToId != null) {
                    TreeNodeUI gotoNode = new TreeNodeUI(section.goToId);
                    sectionNode.add(gotoNode);
                }
                pageNode.add(sectionNode);
            }
            root.add(pageNode);
        }
        for (Section section : gameBook.freeSections) {
            TreeNodeUI sectionNode = fragments(section);
            if (section.goToId != null) {
                TreeNodeUI gotoNode = new TreeNodeUI(section.goToId);
                sectionNode.add(gotoNode);
            }
            root.add(sectionNode);
        }
        setModel(new DefaultTreeModel(root));
        selectedPath = new TreePath(root.getPath());
        setSelectionPath(selectedPath);
        selection = gameBook;
        return root;
    }

    private TreeNodeUI fragments(ModelContainer section) {
        TreeNodeUI sectionNode = new TreeNodeUI(section);
        for (Fragment frag : section.getFragments()) {
            if (frag instanceof ModelContainer) {
                sectionNode.add(fragments((ModelContainer) frag));
            } else {
                TreeNodeUI fragNode = new TreeNodeUI(frag);
                sectionNode.add(fragNode);
            }
        }
        return sectionNode;
    }

    //public Item getSelection() {
    //	return selection;
    //}

    public void update() {
        TreeNodeUI node = (TreeNodeUI) selectedPath.getLastPathComponent();
        ((DefaultTreeModel) this.getModel()).nodeChanged(node);
    }

    public void addToSel(Item childValue) {
        TreeNodeUI parent = (TreeNodeUI) selectedPath.getLastPathComponent();
        TreeNodeUI child = new TreeNodeUI(childValue);
        int lastIndex = parent.getChildCount();
        ((DefaultTreeModel) getModel())
                .insertNodeInto(child, parent, lastIndex);
        this.selection = childValue;
        selectedPath = new TreePath(child.getPath());
        ((DefaultTreeModel) this.getModel())
                .nodeStructureChanged(parent);

    }


    public TreePath getSelectLoc() {
        return selectedPath;
    }

    public void addToPath(TreePath path, Item childValue) {
        TreeNodeUI parent = (TreeNodeUI) path.getLastPathComponent();
        TreeNodeUI child = new TreeNodeUI(childValue);
        int lastIndex = parent.getChildCount();
        ((DefaultTreeModel) getModel())
                .insertNodeInto(child, parent, lastIndex);
        this.selection = childValue;
        selectedPath = new TreePath(child.getPath());
        setSelectionPath(selectedPath);
        ((DefaultTreeModel) this.getModel())
                .nodeStructureChanged(parent);

    }

}
