package org.example.io;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class GraphLoader {
    public static <T> T readFromJson(String filename, Class<T> classType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            T data = objectMapper.readValue(new File(filename), classType);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
