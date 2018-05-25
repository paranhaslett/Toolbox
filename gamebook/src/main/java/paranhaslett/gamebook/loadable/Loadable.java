package paranhaslett.gamebook.loadable;

import paranhaslett.gamebook.loader.EmaLoader;
import paranhaslett.gamebook.loader.Loader;
import paranhaslett.gamebook.loader.XMLLoader;
import paranhaslett.gamebook.model.Item;

public interface Loadable {
    XMLLoader xmlLoader = new XMLLoader();
    EmaLoader emaLoader = new EmaLoader();

    void load(Loader ff, Item item);

    void save(Loader ff, Item item);


}
