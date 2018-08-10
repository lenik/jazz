package net.bodz.bas.scanner.ztx965n;

public interface LockMode {

    /**
     * 0~3只适用EPC、TID和User三个数据区;4~7只适用Kill Password和Access Password。
     */

    int Writable = 0;
    int WritablePerm = 1;
    int WritableProt = 2;
    int NoWrite = 3;

    int ReadWrite = 4;
    int ReadWritePerm = 5;
    int ReadWriteProt = 6;
    int NoReadWrite = 7;

}
