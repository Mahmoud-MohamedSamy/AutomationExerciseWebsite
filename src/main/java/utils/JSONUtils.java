package utils;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class for reading and writing JSON files using org.json.
 */
public class JSONUtils {

    /**
     * Reads a JSON file and returns it as a JSONObject.
     *
     * @param filePath The path to the JSON file.
     * @return JSONObject containing the parsed data.
     * @throws IOException if file read fails.
     */
    public static JSONObject readJsonFile(String filePath) throws IOException {
        // Read file content as a String
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        // Convert string to JSONObject
        return new JSONObject(content);
    }

    /**
     * Writes a JSONObject to a file.
     *
     * @param filePath The path where the JSON should be written.
     * @param data     The JSONObject to write.
     * @throws IOException if file write fails.
     */
    public static void writeJsonFile(String filePath, JSONObject data) throws IOException {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString(4)); // Pretty print with 4-space indentation
        }
    }
}
