package paranhaslett.gamebook.loadable;

import paranhaslett.gamebook.loader.Loader;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.fragment.Set;

public class SetIO implements Loadable {

    @Override
    public void load(Loader ff, Item item) {
        Set set = (Set) item;
        set.var = ff.getText("var");
        set.value = ff.getText("value");
        set.text = ff.getText("text");
    }

    @Override
    public void save(Loader ff, Item item) {
        Set set = (Set) item;
        ff.setText("var", set.var);
        ff.setText("value", set.value);
        ff.setText("text", set.text);
    }

}
