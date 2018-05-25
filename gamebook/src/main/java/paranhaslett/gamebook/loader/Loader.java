package paranhaslett.gamebook.loader;

import paranhaslett.gamebook.model.Item;

import java.io.File;
import java.util.List;

public interface Loader {

    void load(File file, Item item);

    void save(File file, Item item);

    //-----------------------------

    String getText(String key);

    void setText(String key, String value);

    List<Loader> getChildren(String... childrenKeys);

    Loader getChild(String childKey);

    Loader create(String key);

    String getName();
}
