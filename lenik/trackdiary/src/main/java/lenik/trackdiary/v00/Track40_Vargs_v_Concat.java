package lenik.trackdiary.v00;

import java.util.Random;

public class Track40_Vargs_v_Concat {

    static final int scale = 1000000; // 1M
    static final int argsMax = 5;
    static final int seed = 0; // for test consistency.

    volatile String stringPiece = "test string piece";

    Random random;
    long lastTime;

    void begin(String name) {
        System.out.print(name + ": ");
        random = new Random(seed);
        lastTime = System.currentTimeMillis();
    }

    int end() {
        int dur = (int) (System.currentTimeMillis() - lastTime);
        System.out.println(dur);
        return dur;
    }

    void print(String mesg) {
    }

    void concat(String mesg) {
        print(mesg);
    }

    void vargs(Object... args) {
        StringBuffer buf = new StringBuffer(100);
        for (int i = 0; i < args.length; i++)
            buf.append(args[i]);
        print(buf.toString());
    }

    void concatQuiet(String mesg) {
    }

    void vargsQuiet(Object... args) {
    }

    // void vargs_ArrayMesg(Object... args) {
    // }
    public void run() {

        begin("concat-2");
        for (int i = 0; i < scale; i++)
            concat(stringPiece + stringPiece);
        end();

        begin("vargs-2");
        for (int i = 0; i < scale; i++)
            vargs(stringPiece, stringPiece);
        end();

        begin("concat-3");
        for (int i = 0; i < scale; i++)
            concat(stringPiece + stringPiece + stringPiece);
        end();

        begin("vargs-3");
        for (int i = 0; i < scale; i++)
            vargs(stringPiece, stringPiece, stringPiece);
        end();

        begin("concat-5");
        for (int i = 0; i < scale; i++)
            concat(stringPiece + stringPiece + stringPiece + stringPiece + stringPiece);
        end();

        begin("vargs-5");
        for (int i = 0; i < scale; i++)
            vargs(stringPiece, stringPiece, stringPiece, stringPiece, stringPiece);
        end();

        begin("concat-10");
        for (int i = 0; i < scale; i++)
            concat(stringPiece + stringPiece + stringPiece + stringPiece + stringPiece + stringPiece + stringPiece
                    + stringPiece + stringPiece + stringPiece);
        end();

        begin("vargs-10");
        for (int i = 0; i < scale; i++)
            vargs(stringPiece, stringPiece, stringPiece, stringPiece, stringPiece, stringPiece, stringPiece,
                    stringPiece, stringPiece, stringPiece);
        end();

        begin("concat-x");
        for (int i = 0; i < scale; i++) {
            int argc = random.nextInt(argsMax) + 1;
            String mesg = "";
            for (int j = 0; j < argc; j++)
                mesg += stringPiece;
            concat(mesg);
        }
        end();

        begin("vargs-x");
        for (int i = 0; i < scale; i++) {
            int argc = random.nextInt(argsMax) + 1;
            String[] pieces = new String[argc];
            for (int j = 0; j < argc; j++)
                pieces[j] = stringPiece;
            vargs((Object[]) pieces);
        }
        end();

        begin("concatq-x");
        for (int i = 0; i < scale; i++) {
            int argc = random.nextInt(argsMax) + 1;
            String mesg = "";
            for (int j = 0; j < argc; j++)
                mesg += stringPiece;
            concatQuiet(mesg);
        }
        end();

        begin("vargsq-x");
        for (int i = 0; i < scale; i++) {
            int argc = random.nextInt(argsMax) + 1;
            String[] pieces = new String[argc];
            for (int j = 0; j < argc; j++)
                pieces[j] = stringPiece;
            vargsQuiet((Object[]) pieces);
        }
        end();

    }

    public static void main(String[] args) {
        new Track40_Vargs_v_Concat().run();
    }

}
