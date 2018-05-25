package paranhaslett.gamebook.loadable;

import paranhaslett.gamebook.loader.Loader;
import paranhaslett.gamebook.model.Fragment;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Section;
import paranhaslett.gamebook.model.fragment.GoTo;
import paranhaslett.gamebook.model.fragment.Set;
import paranhaslett.gamebook.model.fragment.Text;
import paranhaslett.gamebook.model.fragment.branch.Chance;
import paranhaslett.gamebook.model.fragment.branch.Choice;
import paranhaslett.gamebook.model.fragment.branch.If;

import java.util.List;

public class SectionIO implements Loadable {

    @Override
    public void load(Loader ff, Item item) {
        Section section = (Section) item;
        // Mandatory section_id
        section.id = ff.getText("id");
        section.title = ff.getText("title");

        List<Loader> fragmentElements = ff.getChildren(
                "text",
                "set",
                "choice",
                "chance",
                "if");

        boolean hasDesc = false;
        for (Loader fragmentElement : fragmentElements) {
            Fragment frag = null;
            if (fragmentElement.getName().equals("text")) {
                frag = new Text();
                Text.loadable.load(fragmentElement, frag);
                hasDesc = true;
            }
            if (fragmentElement.getName().equals("set")) {
                frag = new Set();
                Set.loadable.load(fragmentElement, frag);
            }
            if (fragmentElement.getName().equals("choice")) {
                frag = new Choice();
                Choice.loadable.load(fragmentElement, frag);
            }
            if (fragmentElement.getName().equals("chance")) {
                frag = new Chance();
                Chance.loadable.load(fragmentElement, frag);
            }
            if (fragmentElement.getName().equals("if")) {
                frag = new If();
                If.loadable.load(fragmentElement, frag);
            }

            if (frag != null) {
                section.fragments.add(frag);
            }
        }

        if (!hasDesc) {
            Text desc = new Text();
            desc.setup();
            section.fragments.add(desc);
        }

        Loader gotoElement = ff.getChild("goto");
        GoTo sectionGoTo = new GoTo();
        if (gotoElement != null) {
            GoTo.loadable.load(gotoElement, sectionGoTo);
        } else {
            sectionGoTo.setup();
        }
        section.goToId = sectionGoTo;

    }

    @Override
    public void save(Loader ff, Item item) {
        Section section = (Section) item;
        ff.setText("id", "" + section.id);
        if (section.title != null) {
            ff.setText("title", section.title);
        }
        if (section.fragments.size() > 0) {

            for (Fragment fragment : section.fragments) {
                Loader fragElement;
                if (fragment instanceof Text) {
                    fragElement = ff.create("text");
                    Text.loadable.save(fragElement, fragment);
                }
                if (fragment instanceof Set) {
                    fragElement = ff.create("set");
                    Set.loadable.save(fragElement, fragment);
                }
                if (fragment instanceof Choice) {
                    fragElement = ff.create("choice");
                    Choice.loadable.save(fragElement, fragment);
                }
                if (fragment instanceof Chance) {
                    fragElement = ff.create("chance");
                    Chance.loadable.save(fragElement, fragment);
                }
                if (fragment instanceof If) {
                    fragElement = ff.create("choice");
                    If.loadable.save(fragElement, fragment);
                }
            }
        }

        if (section.goToId != null) {
            Loader gotoElement = ff.create("goto");
            GoTo.loadable.save(gotoElement, section.goToId);
        }


    }

}
