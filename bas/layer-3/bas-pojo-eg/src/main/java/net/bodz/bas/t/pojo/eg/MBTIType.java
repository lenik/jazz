package net.bodz.bas.t.pojo.eg;

public enum MBTIType {

    ISTJ(false, true, false, false),
    ISFJ(false, true, true, false),
    INFJ(false, false, true, false),
    INTJ(false, false, false, false),
    ISTP(false, true, false, true),
    ISFP(false, true, true, true),
    INFP(false, false, true, true),
    INTP(false, false, false, true),
    ESTP(true, true, false, true),
    ESFP(true, true, true, true),
    ENFP(true, false, true, true),
    ENTP(true, false, false, true),
    ESTJ(true, true, false, false),
    ESFJ(true, true, true, false),
    ENFJ(true, false, true, false),
    ENTJ(true, false, false, false),

    ;

    public final boolean extraversion;
    public final boolean sensation;
    public final boolean feeling;
    public final boolean perception;

    private MBTIType(boolean extraversion, boolean sensation, boolean feeling, boolean perception) {
        this.extraversion = extraversion;
        this.sensation = sensation;
        this.feeling = feeling;
        this.perception = perception;
    }

    public static enum MBTIAttitude {
        introversion,
        extraversion
    }

    public static enum MBTIPerceiving {
        intuition, // alias N
        sensation
    }

    public static enum MBTIJudging {
        feeling,
        thinking
    }

    public static enum MBTILifestyle {
        judging,
        perception
    }

    public MBTIAttitude getAttitude() {
        return extraversion ? MBTIAttitude.extraversion : MBTIAttitude.introversion;
    }

    public MBTIPerceiving getPerception() {
        return sensation ? MBTIPerceiving.sensation : MBTIPerceiving.intuition;
    }

    public MBTIJudging getJudging() {
        return feeling ? MBTIJudging.feeling : MBTIJudging.thinking;
    }

    public MBTILifestyle getLifestyle() {
        return perception ? MBTILifestyle.perception : MBTILifestyle.judging;
    }

    public static void main(String[] args) {
        String[] v = "ISTJ ISFJ INFJ INTJ ISTP ISFP INFP INTP ESTP ESFP ENFP ENTP ESTJ ESFJ ENFJ ENTJ".split(" ");
        for (String a : v) {
            boolean e = a.charAt(0) == 'E';
            boolean s = a.charAt(1) == 'S';
            boolean f = a.charAt(2) == 'F';
            boolean p = a.charAt(3) == 'P';
            System.out.printf("%s(%s,%s,%s,%s),", a, e, s, f, p);
        }
    }

}
