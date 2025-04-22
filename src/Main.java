import java.io.File;

public class Main {
    public static void main(String[] args) {
        DirLister dirLister = new DirLister();
        File dir = FilePicker.getDir();
        if (dir != null) {
            dirLister.setFirstDir(dir);
        }
    }
}
