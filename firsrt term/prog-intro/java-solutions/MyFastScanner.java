import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyFastScanner {
    private final InputStreamReader reader;
    private char[] Buff;
    private int curr = 0;
    private int len = 0;

    public MyFastScanner (InputStream file) throws IOException {
        this.reader = new InputStreamReader(file,
                StandardCharsets.UTF_8);
        Buff_extend();
    }

    public MyFastScanner (String line) throws IOException {
        this.reader = new InputStreamReader(new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        Buff_extend();
    }

    private boolean Buff_extend() throws IOException {
        if (reader.ready()) {
            this.Buff = new char[100];
            this.len = reader.read(this.Buff);
            return true;
        } else {
            return false;
        }
    }

    public int nextChar() throws IOException {
        if (this.curr == 100) {
            this.curr = 0;
            if (!Buff_extend())
                return -1;
        }
        return this.Buff[this.curr++];
    }

    public String next() throws IOException {
        StringBuilder string = new StringBuilder();
        char k;
        int sym = 0;
        while (hasNext()) {
            k = (char)nextChar();
            if (Character.isWhitespace(k)) {
                if (sym == 1) break;
            } else {
                string.append(k);
                sym = 1;
            }
        }
        if (string.length() > 0) {
            return string.toString();
        } else {
            return "";
        }
    }

    public String nextLine() throws IOException {
        StringBuilder line = new StringBuilder();
        char k = '1';
        int ch = 0;
        while (k != '\n' && hasNext()) {
            k = (char)nextChar();
            if (k != '\n')
                line.append(k);
            if (!Character.isWhitespace(k))
                ch = 1;
        }
        if (ch == 1) {
            return line.toString();
        } else {
            return "";
        }
    }

    public boolean hasNext() throws IOException {
        if (this.curr < this.len) {
            return true;
        } else {
            return reader.ready();
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}