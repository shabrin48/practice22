import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Practice22 {

    public static void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;

            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, end);

            merge(array, start, middle, end);
        }
    }

    private static void merge(int[] array, int start, int middle, int end) {
        int leftSize = middle - start + 1;
        int rightSize = end - middle;

        int[] leftPart = new int[leftSize];
        int[] rightPart = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftPart[i] = array[start + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightPart[j] = array[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = start;

        while (i < leftSize && j < rightSize) {
            if (leftPart[i] <= rightPart[j]) {
                array[k] = leftPart[i];
                i++;
            } else {
                array[k] = rightPart[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = leftPart[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightPart[j];
            j++;
            k++;
        }
    }

    public static void countingSort(int[] array) {
        if (array.length == 0) return;

        int minVal = array[0];
        int maxVal = array[0];

        for (int val : array) {
            if (val > maxVal) maxVal = val;
            if (val < minVal) minVal = val;
        }

        int range = maxVal - minVal + 1;
        int[] frequency = new int[range];

        for (int val : array) {
            frequency[val - minVal]++;
        }

        int arrayIdx = 0;
        for (int i = 0; i < range; i++) {
            while (frequency[i] > 0) {
                array[arrayIdx] = i + minVal;
                arrayIdx++;
                frequency[i]--;
            }
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotLocation = partition(array, low, high);

            quickSort(array, low, pivotLocation - 1);
            quickSort(array, pivotLocation + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivotElement = array[high];
        int targetIdx = low - 1;

        for (int currentIdx = low; currentIdx < high; currentIdx++) {
            if (array[currentIdx] <= pivotElement) {
                targetIdx++;

                int temporary = array[targetIdx];
                array[targetIdx] = array[currentIdx];
                array[currentIdx] = temporary;
            }
        }

        int temporary = array[targetIdx + 1];
        array[targetIdx + 1] = array[high];
        array[high] = temporary;

        return targetIdx + 1;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Random randomGenerator = new Random();

        System.out.print("Введіть розмір масиву: ");
        int arrayLength = inputScanner.nextInt();

        System.out.print("Від якого числа генерувати: ");
        int rangeFrom = inputScanner.nextInt();

        System.out.print("До якого числа генерувати: ");
        int rangeTo = inputScanner.nextInt();

        int[] baseArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            baseArray[i] = randomGenerator.nextInt(rangeTo - rangeFrom + 1) + rangeFrom;
        }

        System.out.println("\nПочатковий масив:");
        System.out.println(Arrays.toString(baseArray));

        int[] mergeSortedCopy = Arrays.copyOf(baseArray, baseArray.length);
        int[] countingSortedCopy = Arrays.copyOf(baseArray, baseArray.length);
        int[] quickSortedCopy = Arrays.copyOf(baseArray, baseArray.length);

        long timerStart;
        long timerEnd;

        timerStart = System.nanoTime();
        mergeSort(mergeSortedCopy, 0, mergeSortedCopy.length - 1);
        timerEnd = System.nanoTime();
        long mergeResultTime = timerEnd - timerStart;

        timerStart = System.nanoTime();
        countingSort(countingSortedCopy);
        timerEnd = System.nanoTime();
        long countingResultTime = timerEnd - timerStart;

        timerStart = System.nanoTime();
        quickSort(quickSortedCopy, 0, quickSortedCopy.length - 1);
        timerEnd = System.nanoTime();
        long quickResultTime = timerEnd - timerStart;

        System.out.println("\n===== Merge Sort =====");
        System.out.println(Arrays.toString(mergeSortedCopy));
        System.out.println("Час виконання: " + mergeResultTime + " нс");

        System.out.println("\n===== Counting Sort =====");
        System.out.println(Arrays.toString(countingSortedCopy));
        System.out.println("Час виконання: " + countingResultTime + " нс");

        System.out.println("\n===== Quick Sort =====");
        System.out.println(Arrays.toString(quickSortedCopy));
        System.out.println("Час виконання: " + quickResultTime + " нс");

        inputScanner.close();
    }
}