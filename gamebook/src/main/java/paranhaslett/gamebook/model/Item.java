package paranhaslett.gamebook.model;

import javax.swing.*;

public interface Item {

    void add(Item to);

    void update();

    void setup();

    void changeMainLabel(String newLabel);

    boolean isDropOn(Item item);

    Icon icon();

}
