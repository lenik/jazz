package net.bodz.bas.site.vhost.registry;

import net.bodz.bas.repr.form.SortOrder;

public class NumbersRegistry<T>
        extends NumNodeRegistry<NumbersRegistry<T>, T> {

    public NumbersRegistry() {
        super();
    }

    public NumbersRegistry(SortOrder order) {
        super(order);
    }

    static int[] convert(String[] hex) {
        int[] num = new int[hex.length];
        for (int i = 0; i < hex.length; i++)
            num[i] = Integer.parseInt(hex[i], 16);
        return num;
    }

    public boolean containsHex(String[] hex) {
        return contains(convert(hex));
    }

    public NumNodeRegistry<NumbersRegistry<T>, T> resolve(int[] nums) {
        NumNodeRegistry<NumbersRegistry<T>, T> node = this;
        for (int i = 0; i < nums.length; i++) {
            NumbersRegistry<T> next = node.find(nums[i]);
            if (next == null)
                return null;
            node = next;
        }
        return node;
    }

    public boolean contains(int[] nums) {
        return resolve(nums) != null;
    }

    public T findHex(String[] hexes) {
        return find(convert(hexes));
    }

    public T find(int[] nums) {
        NumNodeRegistry<NumbersRegistry<T>, T> node = resolve(nums);
        if (node != null)
            return node.getValue();
        return null;
    }

    public boolean set(int[] nums, T value) {
        NumNodeRegistry<NumbersRegistry<T>, T> node = resolve(nums);
        if (node == null)
            return false;
        node.setValue(value);
        return true;
    }

    public boolean add(int[] nums, T value) {
        NumNodeRegistry<NumbersRegistry<T>, T> node = resolve(nums);
        if (node == null)
            return false;
        node.setValue(value);
        return true;
    }

    public T remove(int[] nums) {
        NumNodeRegistry<NumbersRegistry<T>, T> node = resolve(nums);
        // XXX node.remove(nums[last])
        return null;
    }

}
