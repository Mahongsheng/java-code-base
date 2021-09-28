package algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 1. 首先设定一个分界值，通过该分界值将数组分成左右两部分。
 * 2. 将大于或等于分界值的数据集中到数组右边，小于分界值的数据集中到数组的左边。此时，左边部分中各元素都小于或等于分界值，而右边部分中各元素都大于或等于分界值。
 * 3. 然后，左边和右边的数据可以独立排序。对于左侧的数组数据，又可以取一个分界值，将该部分数据分成左右两部分，同样在左边放置较小值，右边放置较大值。右侧的数组数据也可以做类似处理。
 * 4. 重复上述过程，可以看出，这是一个递归定义。通过递归将左侧部分排好序后，再递归排好右侧部分的顺序。当左、右两个部分各数据排序完成后，整个数组的排序也就完成了。
 */
public class QuickSort {

    /**
     * 分而治之的快排(nlog(n))
     *
     * @param nums 待排序数组
     */
    public static void sort(int[] nums, int start, int end) {
        int i = start, j = end;

        // 使用随机主元
        int pivotIndex = new Random().nextInt(end - start + 1) + start;
        swap(nums, pivotIndex, start);
        int pivot = nums[start];

        while (i < j) {
            while (i < j && nums[j] > pivot) {
                j--;
            }

            while (i < j && nums[i] < pivot) {
                i++;
            }

            // 对“等于”的情况进行处理
            if (i < j && nums[i] == nums[j]) {
                // 此处i++与j--等价
                i++;
            } else {
                swap(nums, i, j);
            }
        }

        if (i - 1 > start) {
            sort(nums, start, i - 1);
        }
        if (j + 1 < end) {
            sort(nums, j + 1, end);
        }
    }

    /**
     * 交换值的位置
     *
     * @param nums 待交换数组
     * @param i    位置i
     * @param j    位置j
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{13, 5, 17, 23, 12, 8, 34, 31, 28};
        QuickSort.sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
