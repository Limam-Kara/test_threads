package paralisme_test;

class ReaderWriter {
    private int readers = 0;
    private boolean writing = false;

    public synchronized void startRead() {
        while (writing) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readers++;
    }

    public synchronized void endRead() {
        readers--;
        if (readers == 0) {
            notify();
        }
    }

    public synchronized void startWrite() {
        while (readers > 0 || writing) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writing = true;
    }

    public synchronized void endWrite() {
        writing = false;
        notify();
    }
}

public class ReaderWriterTest {
    public static void main(String[] args) {
        ReaderWriter readerWriter = new ReaderWriter();

        // Create and start multiple reader threads
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    readerWriter.startRead();
                    System.out.println("Reader is reading.");
                    readerWriter.endRead();

                    try {
                        Thread.sleep(1000); // Simulating some reading time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        // Create and start multiple writer threads
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    readerWriter.startWrite();
                    System.out.println("Writer is writing.");
                    readerWriter.endWrite();

                    try {
                        Thread.sleep(2000); // Simulating some writing time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
