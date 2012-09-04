package net.bodz.bas.c.java.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

class type_t {
}

class Type_t {
}

class sum_t {
}

public class ArraysTemplate {

    // SECTION _elm

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    public static type_t[] concat(type_t[]... arrays) {
        int total = 0;
        for (type_t[] a : arrays)
            total += a.length;
        if (total == 0)
            return new type_t[0];

        type_t[] result = new type_t[total];
        int offset = 0;
        for (type_t[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    // SECTION _elm

    /**
     * Prepend value to the array.
     */
    public static type_t[] prepend(type_t val, type_t... array) {
        type_t[] result = new type_t[1 + array.length];
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    // SECTION _elm

    /**
     * Append values to the array.
     */
    public static type_t[] append(type_t[] array, type_t... vals) {
        type_t[] result = new type_t[array.length + vals.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    // SECTION _elm

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<Type_t, type_t[]> shift(type_t... array) {
        if (array.length == 0)
            return null;
        type_t[] remaining = new type_t[array.length - 1];
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<Type_t, type_t[]>(array[0], remaining);
    }

    // SECTION _elm

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<type_t[], Type_t> pop(type_t... array) {
        if (array.length == 0)
            return null;
        type_t[] remaining = new type_t[array.length - 1];
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<type_t[], Type_t>(remaining, array[array.length - 1]);
    }

    // SECTION elm

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static type_t[] reverse(type_t... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(type_t[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            type_t temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    // SECTION elm

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static type_t[] shuffle(type_t... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(type_t[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            type_t temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    // SECTION alg

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static sum_t sum(type_t... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static sum_t sum(type_t[] array, int begin, int end) {
        sum_t sum = ZERO;
        for (int i = begin; i < end; i++)
            sum = ADD(sum, array[i]);
        return sum;
    }

    // SECTION alg

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static type_t average(type_t... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static type_t average(type_t[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        sum_t sum = ZERO;
        for (int i = begin; i < end; i++)
            sum = ADD(sum, array[i]);
        return (type_t) (DIVIDE_N(sum, n));
    }

    // SECTION alg

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static type_t min(type_t... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static type_t min(type_t[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        type_t min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            type_t v = array[i];
            if (LESS_THAN(v, min))
                min = v;
        }
        return min;
    }

    // SECTION alg

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static type_t max(type_t... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static type_t max(type_t[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        type_t max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            type_t v = array[i];
            if (GREATER_THAN(v, max))
                max = v;
        }
        return max;
    }

    // END

}
