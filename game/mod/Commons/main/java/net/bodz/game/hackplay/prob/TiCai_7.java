package net.bodz.game.hackplay.prob;

import java.util.Random;

/**
 * 体彩 6+1
 */
public class TiCai_7 {

    Random rand   = new Random();

    /** 奖金池 */
    long   pool   = 10000000;
    /** 玩家收获 */
    long   player = 0;

    int    count;
    int    c7;                   // 6+1
    int    c6;
    int    c5;
    int    c4;
    int    c3;
    int    c2;

    /** 当前期号码 */
    int[]  number;

    public TiCai_7() {
        number = new int[7];
        for (int i = 0; i < number.length; i++)
            number[i] = rand.nextInt(10);
    }

    public static int test(int[] number, int n) {
        int len = 7;
        int[] nd = new int[len];
        for (int i = 0; i < len; i++) {
            nd[len - 1 - i] = n % 10;
            n /= 10;
        }
        int cont = 0;
        int maxcont = 0;
        for (int i = 0; i < len; i++) {
            if (nd[i] == number[i]) {
                cont++;
                if (cont > maxcont)
                    maxcont = cont;
            } else
                cont = 0;
        }
        return maxcont;
    }

    void play() {
        int n = rand.nextInt(10000000);
        int level = test(number, n);
        long got = 0;
        long rest = 10000000;
        switch (level) {
        case 2:
            got = 5;
            c2++;
            break;
        case 3:
            got = 20;
            c3++;
            break;
        case 4:
            got = 300;
            c4++;
            break;
        case 5:
            got = rest * 2 / 100;
            c5++;
            break;
        case 6:
            got = rest * 10 / 100;
            c6++;
            break;
        case 7:
            got = rest * 70 / 100;
            c7++;
            break;
        }
        player += got;
        pool -= got;
    }

    static int PRICE = 2;

    public static void main(String[] args) {
        TiCai_7 game = new TiCai_7();
        long runs = 1000;
        for (int i = 0; i < runs; i++) {
            game.play();
        }
        long cost = runs * PRICE;
        System.out.printf("Gain %d/Cost %d = %f\n", // 
                game.player, cost, (float) game.player / cost);
        System.out.println("C2=" + game.c2);
        System.out.println("C3=" + game.c3);
        System.out.println("C4=" + game.c4);
        System.out.println("C5=" + game.c5);
        System.out.println("C6=" + game.c6);
        System.out.println("C7=" + game.c7);
    }

}
