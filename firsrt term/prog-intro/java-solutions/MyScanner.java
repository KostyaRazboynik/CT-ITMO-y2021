import java.io.*;
import java.nio.charset.StandardCharsets;


public class MyScanner {
    Reader reader;

    public MyScanner(String file) throws IOException {
        this.reader = new FileReader(file, StandardCharsets.UTF_8);
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public int read() throws IOException {
        return this.reader.read();
    }
}
