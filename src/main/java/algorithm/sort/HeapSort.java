package algorithm.sort;

import java.util.Arrays;

/**
 * @author Hansel Ma
 * @since 2022/6/12
 */
public class HeapSort {

    public static void sort(int[] nums) {
        int len = nums.length;

        // 建堆
        buildMaxHeap(nums, len);

        // 堆化
        for (int i = len - 1; i >= 1; --i) {
            swap(nums, 0, i);
            len--;
            maxHeapify(nums, 0, len);
        }
    }

    private static void buildMaxHeap(int[] arr, int len) {
        // 我们需要对1到n/2的元素进行堆化
        for (int i = len / 2 - 1; i >= 0; --i) {
            maxHeapify(arr, i, len);
        }
    }

    // 自顶向下
    private static void maxHeapify(int[] nums, int i, int len) {
        while ((i << 1) + 1 < len) {
            // 左子结点位置
            int lson = (i << 1) + 1;
            // 右子节点位置
            int rson = (i << 1) + 2;
            // 找最大的交换
            int large;
            if (lson < len && nums[lson] > nums[i]) {
                large = lson;
            } else {
                large = i;
            }
            if (rson < len && nums[rson] > nums[large]) {
                large = rson;
            }
            if (large != i) {
                swap(nums, i, large);
                i = large;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        HeapSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
