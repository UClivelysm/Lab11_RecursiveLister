import java.io.File;

public class DirLister {
    private int fileCount = 0;
    private int dirCount  = 0;
    private boolean skipHidden = false;
    private final DirListerFrame frame;      // reference to GUI

    public DirLister(DirListerFrame frame) {
        this.frame = frame;
    }

    public void enableSkipHidden()  { skipHidden = true; }
    public void disableSkipHidden() { skipHidden = false; }

    public void setFirstDir(File dir) {
        fileCount = dirCount = 0;
        report("Starting directory scan of: " + dir.getPath());
        readDir(dir);
        report("Finished scanning directories.");
    }

    private void readDir(File dir) {
        report("Reading directory: " + dir.getPath());

        File[] entries = dir.listFiles();
        if (entries == null) {
            report("Could not list contents of: " + dir.getPath());
            return;
        }

        for (File entry : entries) {
            if (skipHidden && entry.getName().startsWith(".")) continue;

            if (entry.isFile()) {
                fileCount++;
                report("File: " + entry.getName());
//                report(FilePicker.arbStreamFileRead(entry));
            } else if (entry.isDirectory()) {
                dirCount++;
                report("Found subdirectory: " + entry.getName());
                readDir(entry);                       // recurse
            }
        }
    }

    private void report(String text) {
        frame.updateValues(text, fileCount, dirCount); // single gateway to GUI
    }
}
