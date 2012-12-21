package user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class PathSymlink {

    public static void main(String[] args)
            throws IOException {
        Path sl = Paths.get("/mnt/istore/projects/jazz/bas/layer-5/bas-vfs/.111");
        System.out.println(Files.isSymbolicLink(sl));

        BasicFileAttributeView view = Files.getFileAttributeView(sl, BasicFileAttributeView.class,
                LinkOption.NOFOLLOW_LINKS);
        BasicFileAttributes attrs = view.readAttributes();
        System.out.println(attrs.isSymbolicLink());
    }

}
