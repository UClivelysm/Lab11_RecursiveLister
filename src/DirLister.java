import java.io.File;

public class DirLister {
    private int fileCount = 0;
    private int dirCount = 0;
    private boolean skipHidden = true; // New flag

    public void setFirstDir(File dir) {
        fileCount = 0;
        dirCount = 0;

        reportToGui("Starting directory scan of: " + dir.getPath(), dirCount, fileCount);
        readDir(dir);
        reportToGui("Finished scanning directories.", dirCount, fileCount);
    }

    private void readDir(File dir) {
        reportToGui("Reading directory: " + dir.getPath(), dirCount, fileCount);

        File[] entries = dir.listFiles();
        if (entries == null) {
            reportToGui("Could not list contents of: " + dir.getPath(), dirCount, fileCount);
            return;
        }

        for (File entry : entries) {
            // Skip hidden files/directories starting with "."
            if (skipHidden && entry.getName().startsWith(".")) {
                continue;
            }

            if (entry.isFile()) {
                fileCount++;
                String content = FilePicker.arbStreamFileRead(entry);
                reportToGui("File: " + entry.getName(), dirCount, fileCount);
                reportToGui(content, dirCount, fileCount);
            } else if (entry.isDirectory()) {
                dirCount++;
                reportToGui("Found subdirectory: " + entry.getName(), dirCount, fileCount);
                readDir(entry);  // recurse
            }
        }
    }

    private void reportToGui(String text, int dirCount, int fileCount) {
        System.out.println(text);
        System.out.println("Files: " + fileCount + ", Directories: " + dirCount);
    }

    // Setters to control hidden file skipping
    public void enableSkipHidden() {
        skipHidden = true;
    }

    public void disableSkipHidden() {
        skipHidden = false;
    }
}
