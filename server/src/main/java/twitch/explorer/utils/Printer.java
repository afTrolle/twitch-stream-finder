package twitch.explorer.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Printer {


    private static int numberOfStreamsIndex = 0;

    private static void print() {
        System.out.println("CurrentNumbers indexedStreams: " + numberOfStreamsIndex);

    }

    public static void setNumberOfStreamsIndex(int numberOfStreamsIndex) {
        Printer.numberOfStreamsIndex = numberOfStreamsIndex;
        print();
    }


}
