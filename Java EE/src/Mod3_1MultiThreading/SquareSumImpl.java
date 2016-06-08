package Mod3_1MultiThreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Используя Phaser и Executors реализовать класс,
 * который бы считал сумму квадратов элементов массива параллельно в заданном количестве потоков

 interface SquareSumImpl {
 long getSquareSum(int[] values, int numberOfThreads);
 }

 Идея в том, чтобы разбить массив на равные части и найти сумму квадратов
 в каждом кусочке в отдельном потоке параллельно.
 Используя Phaser, дождаться результатов всех вычислений и сложить их, получив конечный результат.
 * */

public class SquareSumImpl implements SquareSum {

        @Override
        public long getSquareSum(int[] values, int numberOfThreads) {

            ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
            // разбить массив
            // предположим, что размер массива кратен количеству потоков
            // создать задачи и передать части массива в них
            // дождаться выполнения всех задач
            // посчитать результаты

            int batchSize = values.length / numberOfThreads; // 1 // 100/5 = 20

            Phaser phaser = new Phaser(numberOfThreads + 1); // 2

            List<Future<Integer>> results = new ArrayList<>(); // 3
            int count = 0;
            for (int i = 0; i < values.length; i+=batchSize) {
                int[] work = new int[batchSize];
                System.arraycopy(values, i, work, 0, batchSize);
                results.add(service.submit(new Worker(work, phaser, "Thread" + ++count)));
            }

            System.out.println("Main arrived");
            phaser.arriveAndAwaitAdvance();
            System.out.println("Main passed");
            phaser.arriveAndDeregister();
            long sum = 0;
            try {
                for (Future<Integer> future : results) {
                    sum += future.get();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            service.shutdown();


            return sum;
        }


        public class Worker implements Callable<Integer> {

            final int[] work;
            Phaser phaser;
            String name;

            public Worker(int[] part, Phaser phaser, String threadName) {
                this.work = part;
                this.phaser = phaser;
                this.name = threadName;
            }

            @Override
            public Integer call() throws InterruptedException {
                int sum = 0;
                for (int i : work) {
                    sum += i * i;
                }
                Thread.sleep(1000);
                System.out.println(name + "[phase:" + phaser.getPhase() + "]");
                phaser.arriveAndAwaitAdvance();
                phaser.arriveAndDeregister();
                return sum;
            }
        }
    }