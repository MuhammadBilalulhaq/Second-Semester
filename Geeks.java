import java.io.*;
import java.net.URL;
import java.net.URI;
import java.net.MalformedURLException;

public class Geeks {

    // Method to download a webpage and save it to a file
    public static void downloadWebPage(String webpage, String path) {
        try {
            // Create a URI object and convert it to a URL
            URI uri = new URI(webpage);
            URL url = uri.toURL();

            // Open a stream to read the webpage content
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            // Use the given path for the output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            // Read each line and write to the file
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            // Close the streams
            reader.close();
            writer.close();

            System.out.println("Webpage downloaded successfully as: " + path);
        } catch (MalformedURLException | IllegalArgumentException e) {
            System.out.println("Error: The URL is invalid.");
        } catch (IOException e) {
            System.out.println("Error: Unable to download the webpage.");
        } catch (Exception e) {
            System.out.println("Error: Invalid URI syntax.");
        }
    }
}
