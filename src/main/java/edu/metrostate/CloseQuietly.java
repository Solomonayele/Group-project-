package edu.metrostate;

import java.io.Closeable;

public class CloseQuietly {

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception ex) {
            // close quietly
        }
    }

    public static void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception ex) {
            // close quietly
        }
    }
}