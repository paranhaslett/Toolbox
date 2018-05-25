package paranhaslett.gamebook.ui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileChooserUI extends JFileChooser {
    private static final long serialVersionUID = 6827459707236057162L;

    public FileChooserUI() {

        FileFilter xmlFilter = new GameBookFilter();
        addChoosableFileFilter(xmlFilter);
        FileFilter emaFilter = new EmaFilter();
        addChoosableFileFilter(emaFilter);
        setFileFilter(xmlFilter);
    }

    public File saveGameBook(EditorUI editorUI) {
        setApproveButtonText("Save");
        int rVal = showSaveDialog(editorUI);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            return getSelectedFile();
        }
        return null;
    }

    public File loadGameBook(EditorUI editorUI) {
        int rVal = showOpenDialog(editorUI);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            return getSelectedFile();
        }
        return null;
    }

    public class GameBookFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            String fn = f.getName();
            return f.isDirectory() || fn.endsWith(".gbf") || fn.endsWith(".xml");
        }

        @Override
        public String getDescription() {
            return "Game books (*.gbf, *.xml)";
        }

    }

    public class EmaFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            String fn = f.getName();
            return f.isDirectory() || (fn.startsWith("gbf") && fn.endsWith(".txt"));
        }

        @Override
        public String getDescription() {
            return "Ema Wiki (gbf*.txt)";
        }

    }

}
