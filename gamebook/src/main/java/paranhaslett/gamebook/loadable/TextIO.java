package paranhaslett.gamebook.loadable;

import paranhaslett.gamebook.loader.Loader;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.fragment.Text;

public class TextIO implements Loadable {

    @Override
    public void load(Loader ff, Item item) {
        Text desc = (Text) item;
        desc.text = ff.getText(null);
    }

    @Override
    public void save(Loader ff, Item item) {
        Text desc = (Text) item;
        ff.setText(null, desc.text);
    }

}
