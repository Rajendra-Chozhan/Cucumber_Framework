package utilities;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FolderNameExtract {
    public static String foldername() {
        String directoryPath = "C:\\Users\\ADMIN\\eclipse-workspace\\Cucumber_Framework\\ExtentReports\\SparkReport";  // Change path accordingly

        String LatestFolder = "";
        try (Stream<Path> stream = Files.list(Paths.get(directoryPath))) {
            Optional<Path> latestFolder = stream
                    .filter(Files::isDirectory)  // Only directories
                    .max(Comparator.comparingLong(p -> {
                        try {
                            return Files.readAttributes(p, BasicFileAttributes.class).lastModifiedTime().toMillis();
                        } catch (IOException e) {
                            return 0;  // If error, assume old timestamp
                        }
                    }));

            if (latestFolder.isPresent()) {
                LatestFolder = String.valueOf(latestFolder.get().getFileName());
                System.out.println("Latest Folder: " + latestFolder.get().getFileName());
            } else {
                System.out.println("No folders found in the directory.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return LatestFolder;
    }
}
