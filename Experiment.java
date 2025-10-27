import sorting.*;

public class Experiment {
    public static void run(int[] sizes, DistributionType[] types, int repetitions) {
        for (DistributionType type : types) {
            for (int size : sizes) {
                for (String alg : new String[]{"Merge", "Heap", "Quick-FIRST", "Quick-RANDOM", "Quick-MEDIAN"}) {
                    long totalTime = 0, totalComp = 0, totalSwap = 0;
                    for (int k = 0; k < repetitions; k++) {
                        int[] data = DatasetGenerator.generate(size, type);
                        long t0 = System.nanoTime();
                        // Algoritmo
                        if (alg.equals("Merge")) {
                            MergeSort ms = new MergeSort();
                            ms.sort(data);
                            totalComp += ms.comparisons;
                            totalSwap += ms.swaps;
                        } else if (alg.equals("Heap")) {
                            HeapSort hs = new HeapSort();
                            hs.sort(data);
                            totalComp += hs.comparisons;
                            totalSwap += hs.swaps;
                        } else {
                            QuickSort.PivotType pt = QuickSort.PivotType.FIRST;
                            if (alg.equals("Quick-RANDOM")) pt = QuickSort.PivotType.RANDOM;
                            if (alg.equals("Quick-MEDIAN")) pt = QuickSort.PivotType.MEDIAN;
                            QuickSort qs = new QuickSort(pt);
                            qs.sort(data);
                            totalComp += qs.comparisons;
                            totalSwap += qs.swaps;
                        }
                        long t1 = System.nanoTime();
                        totalTime += (t1 - t0);
                    }
                    System.out.printf("%d | %s | %s | %.2f ms | %d | %d\n",
                        size, type, alg, (totalTime / repetitions) / 1e6,
                        totalComp / repetitions, totalSwap / repetitions);
                }
            }
        }
    }
}