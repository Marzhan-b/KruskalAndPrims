package org.example.io;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.MSTResult;
import java.io.File;
import java.io.IOException;
public class ResultsWriter {
    public static void writeToJson(String filePath, MSTResult result) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File outputFile = new File(filePath);
            outputFile.getParentFile().mkdirs();
            mapper.writeValue(outputFile, result);
            System.out.println("Results saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }
}
