package com.flexhamp.algorithms;


import com.flexhamp.util.RunnableSort;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.IntFunction;

public class ArraySort {
    private static int swap = 0;
    private static final Random rand = new Random();

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 7, 8, 1, 5, 4, 8, 9, 7, 2, 3, 83};
        int[] nums = generateArray(15);

        LinkedList<RunnableSort<int[]>> list = new LinkedList<>();
        list.add(ArraySort::bubbleSort);
        list.add(ArraySort::bubbleSort2);
        list.add(ArraySort::selectionSort);

        list.forEach(runnableSort -> {
            execute(runnableSort, nums.clone());
        });
    }

    /*
    Пузырьковая сортировка

    Для 10 элементов в сумме получается:
    9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45.
    В общем виде, если массив состоит из N элементов, на первом проходе выполняются N–1 сравнений, на втором — N–2 и т. д. Сумма такого ряда вычисляется
    по формуле:
    (N–1) + (N–2) + (N–3) + ... + 1 = N × (N–1)/2.

    Таким образом, алгоритм выполняет около N^2 / 2 сравнений (на –1 можно не обращать внимания, особенно при больших N

    Время выполнения O(N^2)
    */
    public static int[] bubbleSort(int[] nums) {
        for (int i = nums.length - 1; i > 1; i--) { // Внешний цикл (обратный)
            for (int j = 0; j < i; j++) {            // Внутренний цикл (прямой)
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
        return nums;
    }

    public static int[] bubbleSort2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
        return nums;
    }


    /*
        Сортировки методом выбора

        Число сравнений в теле цикла равно (N-1)*N/2.
        Число сравнений в заголовках циклов (N-1)*N/2.
        Число сравнений перед операцией обмена N-1.
        Суммарное число сравнений N2−1.
        Число обменов N-1.

        Cортировка методом выбора выполняется за время O(N^2),
        как и пузырьковая сортировка.
        Тем не менее совершенно очевидно,
        что сортировка методом выбора выполняется быстрее из-за меньшего количества перестановок.
    */

    public static int[] selectionSort(int[] nums) {
        int min;
        for (int i = 0; i < nums.length - 1; i++) {         // Внешний цикл
            min = i;                                        // Минимум
            for (int j = i + 1; j < nums.length; j++) {     // Внутренний цикл
                if (nums[j] < nums[min]) {                  // Если значение min больше
                    min = j;
                }
            }
            swap(nums, i, min);                             // Поменять их местами
        }
        return nums;
    }


    /*
        Сортировка методом вставки


    */

//    public static void insertionSort(int[] nums) {
//        for (int i,j = 1; i < nums.length; i++) {
//
//        }
//    }

    private static void swap(int[] arr, int left, int reght) {
        swap++;
        int tmp = arr[left];
        arr[left] = arr[reght];
        arr[reght] = tmp;
    }

    private static int[] generateArray(int length, int from, int to) {
        int[] array = new int[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = getRandom(from, to);
        }
        return array;
    }

    private static int[] generateArray(int from, int to) {
        final int length = getRandom(15, 100);
        return generateArray(length, from, to);
    }

    private static int[] generateArray(int length) {
        return generateArray(length, 0, 1000);
    }

    private static int[] generateArray() {
        final int length = getRandom(15, 100);
        return generateArray(length, 0, 1000);
    }

    private static int getRandom(int from, int to) {
        return from + rand.nextInt(to - from + 1);
    }

    private static <T> void execute(RunnableSort<T> runnable, T t) {
//        String methodName = new Object() {}
//                .getClass()
//                .getEnclosingMethod()
//                .getName();
//
//        System.out.println("Current Method is " + methodName);

        System.out.println("======================================================");
        System.out.println("Initial");
        printArray(t);

        LocalDateTime from = LocalDateTime.now();
        final T result = runnable.run(t);
        LocalDateTime to = LocalDateTime.now();
        System.out.println("Spent time sorting: " + Duration.between(from, to));

        System.out.println("Swap: " + swap);
        swap = 0;

        System.out.println("Result");
        printArray(result);
    }

    private static <T> void printArray(T t) {
        final Class<?> aClass = t.getClass();
        final Class<?> componentType = aClass.getComponentType();
        if (aClass.isArray() && componentType.isPrimitive()) {
            if (componentType.equals(int.class)) {
                System.out.println(Arrays.toString((int[]) t));
            } else if (componentType.equals(long.class)) {
                System.out.println(Arrays.toString((long[]) t));
            } else {
                System.out.println(t);
            }
        }
    }
}

