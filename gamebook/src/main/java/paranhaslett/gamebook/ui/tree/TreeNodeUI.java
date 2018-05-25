package paranhaslett.gamebook.ui.tree;

import paranhaslett.gamebook.model.Item;

import javax.swing.tree.DefaultMutableTreeNode;

class TreeNodeUI extends DefaultMutableTreeNode {
    private static final long serialVersionUID = 9020576980406733512L;
    private Item previousValue;

    public TreeNodeUI(Item item) {
        super(item, true);
        previousValue = item;
    }

    public void setUserObject(Object userObject) {
        if (userObject instanceof Item) {
            this.userObject = userObject;
            this.previousValue = (Item) userObject;
        } else if (userObject instanceof String) {
            previousValue.changeMainLabel((String) userObject);
            this.userObject = previousValue;
        }
    }

}
