package net.bodz.lily.concrete;

public interface IUniqueNamed {

    /**
     * <p lang="en">
     * An optional unique code name, which can be specified and alternated by user.
     *
     * <p lang="zh">
     * 类似于 id，唯一名不可重复。和 id 不同的是，唯一名是可以自行指定的，可以包含数字、字母，且可以更改。
     *
     * @label Unique Name
     * @label.zh 唯一名
     * @placeholder 输入唯一名…
     */
    String getUniqueName();

    void setUniqueName(String name);

}
