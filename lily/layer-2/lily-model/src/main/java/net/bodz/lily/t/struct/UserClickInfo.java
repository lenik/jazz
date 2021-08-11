package net.bodz.lily.t.struct;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;

public class UserClickInfo
        extends MixinStruct
        implements IUserVotable, IUserPinnable {

    private int voteUps;
    private int voteDowns;
    private List<UserVoteRecord> voteRecords;

    private int pinCount;
    private List<UserPinRecord> pinRecords;

    private int flagCount;
    private List<UserFlagRecord> flagRecords;

    private int readCount;

    /**
     * 正投票的计数，说明多少人赞成、支持了这条信息。
     *
     * @label Vote Ups
     * @label.zh 赞
     */
    @OfGroup(StdGroup.Ranking.class)
    public int getVoteUps() {
        return voteUps;
    }

    public void setVoteUps(int voteUps) {
        this.voteUps = voteUps;
    }

    /**
     * 负投票的计数，说明多少人对这条信息表达了反对、扔鸡蛋的态度。
     *
     * @label Vote Downs
     * @label.zh 踩
     */
    @OfGroup(StdGroup.Ranking.class)
    public int getVoteDowns() {
        return voteDowns;
    }

    public void setVoteDowns(int voteDowns) {
        this.voteDowns = voteDowns;
    }

    /**
     * 有效投票的计数，和<code>正投票 - 负投票</code>相等。
     *
     * @label Vote Count
     * @label.zh 票数
     */
    @OfGroup(StdGroup.Ranking.class)
    @Derived
    @Override
    public int getVoteCount() {
        return voteUps - voteDowns;
    }

    /**
     * 参与投票的信息列表。
     *
     * @label Vote Records
     * @label.zh 投票记录
     */
    @OfGroup(StdGroup.Ranking.class)
    @Override
    public List<UserVoteRecord> getVoteRecords() {
        return voteRecords;
    }

    public void setVoteRecords(List<UserVoteRecord> voteRecords) {
        this.voteRecords = voteRecords;
    }

    /**
     * 说明这条信息被多少人收藏了。
     *
     * @label Pin Count
     * @label.zh 收藏数
     */
    @OfGroup(StdGroup.Ranking.class)
    public int getPinCount() {
        return pinCount;
    }

    public void setPinCount(int pinCount) {
        this.pinCount = pinCount;
    }

    /**
     * @label Pin Records
     * @label.zh 收藏记录
     */
    @OfGroup(StdGroup.Ranking.class)
    @Override
    public List<UserPinRecord> getPinRecords() {
        return pinRecords;
    }

    public void setPinRecords(List<UserPinRecord> pinRecords) {
        this.pinRecords = pinRecords;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }

    public List<UserFlagRecord> getFlagRecords() {
        return flagRecords;
    }

    public void setFlagRecords(List<UserFlagRecord> flagRecords) {
        this.flagRecords = flagRecords;
    }

    /**
     * 这条信息被阅读的次数。
     *
     * @label Read Count
     * @label.zh 阅读数
     */
    @OfGroup(StdGroup.Ranking.class)
    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        voteUps = o.getInt("voteUps", voteUps);
        voteDowns = o.getInt("voteDowns", voteDowns);
        // voteRecords;

        pinCount = o.getInt("pinCount", pinCount);
        // pinRecords;

        flagCount = o.getInt("flagCount", flagCount);
        // flagRecords;

        readCount = o.getInt("readCount", readCount);

    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("voteUps", voteUps);
        out.entry("voteDowns", voteDowns);
        out.entry("pinCount", pinCount);
        out.entry("flagCount", flagCount);
        out.entry("readCount", readCount);
    }

}
