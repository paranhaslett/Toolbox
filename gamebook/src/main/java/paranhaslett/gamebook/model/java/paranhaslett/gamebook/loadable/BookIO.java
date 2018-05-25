package paranhaslett.gamebook.model.java.paranhaslett.gamebook.loadable;

import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.loader.Loader;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Page;
import paranhaslett.gamebook.model.Section;
import paranhaslett.gamebook.model.libraryitem.Book;

public class BookIO implements Loadable {
    
    @Override
    public void load(Loader loader, Item item) {
        Book gameBook = (Book) item;
        gameBook.title = loader.getText("title");// Mandatory name
        for (Loader pageFf : loader.getChildren("page")) {
            Page page = new Page();
            Page.loadable.load(pageFf, page);
            gameBook.pages.add(page);
        }
        for (Loader sectionElement : loader.getChildren("section")) {
            Section section = new Section();
            Section.loadable.load(sectionElement, section);
            gameBook.freeSections.add(section);
        }
    }

    @Override
    public void save(Loader loader, Item item) {
        Book gameBook = (Book) item;
        if (gameBook.title != null) {
            loader.setText("title", gameBook.title);
        }
        for (Page page : gameBook.pages) {
            Loader pageLoader = loader.create("page");
            Page.loadable.save(pageLoader, page);
        }
        for (Section section : gameBook.freeSections) {
            Loader sectionLoader = loader.create("section");
            Section.loadable.save(sectionLoader, section);
        }
    }
}
