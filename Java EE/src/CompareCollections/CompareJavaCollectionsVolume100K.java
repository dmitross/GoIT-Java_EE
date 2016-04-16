package CompareCollections;

import java.util.*;

public class CompareJavaCollectionsVolume100K {

    public static void main(String[] args) {

        final int volume = 100000;

        final boolean contains = contains(volume, new ArrayList<>());

        final List<Integer> filledArrayList = fillListWithData(volume, new ArrayList<>());
        final List<Integer> filledLinkedList = fillListWithData(volume, new LinkedList<>());

        final long timeArrayListAdding = benchmarkListAdd(volume, new ArrayList<>());
        final long timeLinkedListAdding = benchmarkListAdd(volume, new LinkedList<>());

        final long timeArrayListGetting = benchmarkListGet(volume, filledArrayList);
        final long timeLinkedListGetting = benchmarkListGet(volume, filledLinkedList);

        final long timeArrayListRemove = benchmarkListRemove(volume, filledArrayList);
        final long timeLinkedListRemove = benchmarkListRemove(volume, filledLinkedList);

        final long timeHashSetAdding = benchmarkSetAdd(volume, new HashSet<>());
        final long timeHashSetRemove = benchmarkSetRemove(volume, new HashSet<>());

        final long timeTreeSetAdding = benchmarkSetAdd(volume, new TreeSet<>());
        final long timeTreeSetRemove = benchmarkSetRemove(volume, new TreeSet<>());

        System.out.println(contains);

        printResults("Array list add    ", timeArrayListAdding);
        printResults("Array list get    ", timeArrayListGetting);
        printResults("Array list remove ", timeArrayListRemove);
        printResults("Linked list add   ", timeLinkedListAdding);
        printResults("Linked list get   ", timeLinkedListGetting);
        printResults("Linker list remove", timeLinkedListRemove);

        printResults("HashSet add       ", timeHashSetAdding);
        printResults("HashSet remove    ", timeHashSetRemove);
        printResults("TreeSet add       ", timeTreeSetAdding);
        printResults("TreeSet remove    ", timeTreeSetRemove);

    }

    private static void printResults(String title, long timeArrayListAdding) {
        System.out.format("%20s|%15d%2s\n", title, timeArrayListAdding, "| ns |");
    }

    private static boolean contains(int volume, List<Boolean> list) {

        for (int i = 0; i < volume; i++) {
            boolean retval = list.contains(volume);
        }

        boolean retval = true;

        if (retval == true) {
            System.out.println("The list contained " + volume + " elements");
        }
        else {
            System.out.println("Elements is not contained");
        }

        return retval;
    }

    private static long benchmarkListAdd(int volume, List<Integer> list) {
        final long nanoTimeArrayListAddStart = System.nanoTime();
        for ( int i = 0; i < volume; i++) {
            list.add(volume);

        }
        final long nanoTimeArrayListAddStop = System.nanoTime();
        return nanoTimeArrayListAddStop - nanoTimeArrayListAddStart;
    }

    private static long benchmarkListGet(int volume, List<Integer> list) {
        final long nanoTimeArrayListGetStart = System.nanoTime();
        Integer sum = 0;
        for ( int i = 0; i < volume; i++) {
            final Integer integer = list.get(i);
            sum += integer;
        }
        System.out.println(sum);
        final long nanoTimeArrayListGetStop = System.nanoTime();

        return nanoTimeArrayListGetStop - nanoTimeArrayListGetStart;
    }

    private static long benchmarkListRemove(int volume, List<Integer> list) {
        final long nanoTimeArrayListRemoveStart = System.nanoTime();
        for ( int i = 0 ; i < list.size() ; i++ ) {
            list.remove(i);
        }
        final long nanoTimeArrayListRemoveStop = System.nanoTime();
        return nanoTimeArrayListRemoveStop - nanoTimeArrayListRemoveStart;
    }

    private static List<Integer> fillListWithData(int volume, List<Integer> list) {
        for ( int i = 0 ; i < volume ; i++ ) {
            list.add(volume);
        }
        return list;
    }

    private static long benchmarkSetAdd(int volume, Set<Integer> set) {
        final long nanoTimeHashSetStart = System.nanoTime();
        for (int i = 0; i < volume; i++) {
            set.add(volume);
        }
        final long nanoTimeHashSetAddStop = System.nanoTime();
        return nanoTimeHashSetAddStop - nanoTimeHashSetStart;
    }

    private static long benchmarkSetRemove(int volume, Set<Integer> set) {
        final long nanoTimeHashSetRemoveStart = System.nanoTime();
        for (int i = 0; i < set.size(); i++) {
            set.remove(i);
        }
        final long nanoTimeHashSetRemoveStop = System.nanoTime();

        return nanoTimeHashSetRemoveStop - nanoTimeHashSetRemoveStart;
    }
}
