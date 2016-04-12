import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompareJavaCollections {

    /** Написать программу для сравнения эффективности коллекций:
     Программа должна сравнивать различные имплементации коллекций по эффективности выполнения следующих операций:

     List:
     add(index)
     get(index)
     remove(index)
     contains(value)
     populate (наполнение коллекции)
     ListIterator.add()
     ListIterator.remove()

     Set:
     add(value)
     remove(value)
     contains(value)
     populate (наполнение коллекции)

     Сравнения должны выполнятся на объемах: 10К (10 000) 100К 1000К элементов.
     Для каждого набора (10К, 100К, 1000К) выполнить не менее 100 измерений и вычислить среднее значение.
     Результаты измерений вывести на экран и сохранить в файл в виде таблицы.
     */

    public static void main(String[] args) {

        final int volume = 1000;

        final long timeArrayListAdding = benchmarkListAdd(volume, new ArrayList<>());
        final long timeLinkedListAdding = benchmarkListAdd(volume, new LinkedList<>());

        final List<Integer> filledArrayList = fillListWithData(volume, new ArrayList<>());
        final List<Integer> filledLinkedList = fillListWithData(volume, new LinkedList<>());

        final long timeArrayListGeting = benchmarkListGet(volume, filledArrayList);
        final long timeLinkedListGeting = benchmarkListGet(volume, filledLinkedList);

        final long timeArrayListRemove = benchmarkListRemove(volume, filledArrayList);
        final long timeLinkedListRemove = benchmarkListRemove(volume, filledLinkedList);

        printResults("Array list add", timeArrayListAdding);
        printResults("Linked list add", timeLinkedListAdding);
        printResults("Array list get", timeArrayListGeting);


    }

    private static void printResults(String title, long timeArrayListAdding) {
        System.out.format("%20s|%10d%2s\n", title, timeArrayListAdding, "ns");
    }

    private static long benchmarkListRemove(int volume, List<Integer> list) {
        final long nanoTimeArrayListRemoveStart = System.nanoTime();
        for ( int i = 0 ; i < list.size() ; i++ ) {
            list.remove(i);
        }
        final long nanoTimeArrayListRemoveStop = System.nanoTime();
        return nanoTimeArrayListRemoveStop - nanoTimeArrayListRemoveStart;
    }

    private static long benchmarkListAdd(int volume, List<Integer> list) {
        final long nanoTimeArrayListAddStart = System.nanoTime();
        for ( int i = 0 ; i < volume ; i++ ) {
            list.add(volume);
        }
        final long nanoTimeArrayListAddStop = System.nanoTime();
        return nanoTimeArrayListAddStop - nanoTimeArrayListAddStart;
    }

    private static long benchmarkListGet(int volume, List<Integer> list) {
        final long nanoTimeArrayListGetStart = System.nanoTime();
        Integer sum = 0;
        for ( int i = 0 ; i < volume ; i++ ) {
            final Integer integer = list.get(i);
            sum += integer;
        }
        System.out.println(sum);
        final long nanoTimeArrayListGetStop = System.nanoTime();

        return nanoTimeArrayListGetStop - nanoTimeArrayListGetStart;
    }

    private static List<Integer> fillListWithData(int volume, List<Integer> list) {
        for ( int i = 0 ; i < volume ; i++ ) {
            list.add(volume);
        }
        return list;
    }

}
