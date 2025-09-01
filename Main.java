import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String webpage = "https://www.geeksforgeeks.org/";
        String path = "bilal.html";

        // Download the webpage
        Geeks.downloadWebPage(webpage, path);

        // Open the downloaded file in the default browser
        try {
            File file = new File(path);
            if (file.exists()) {
                Desktop.getDesktop().browse(file.toURI());
                System.out.println("Opened " + path + " in your browser.");
            } else {
                System.out.println("File not found: " + path);
            }
        } catch (IOException e) {
            System.out.println("Error opening the file in browser.");
        }
    }
}
