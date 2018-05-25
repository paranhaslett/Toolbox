package paranhaslett.gamebook.ui.tree;

import paranhaslett.gamebook.model.Item;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeRendererUI extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = -4221259711269183060L;

    public TreeRendererUI() {
        setTextSelectionColor(Color.white);
        setBackgroundSelectionColor(Color.blue);
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        Object nodeInfo = node.getUserObject();
        if (nodeInfo instanceof Item) {
            Item item = (Item) nodeInfo;
            setIcon(item.icon());
        }

        return this;
    }

    public ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
