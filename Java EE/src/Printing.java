 class Print {

     public static void main(String[] args) {

         // just practice ...
         System.out.println("Attention,THE MAGIC in studio ! ");
         System.out.printf("%6d%n%6d%n%6d%n%6d%n%6d%n%6d%n", 666666, 55555, 4444, 333, 22, 1);

         System.out.printf("PI petc: " + "%1$+020.10f", Math.PI);

         System.out.println();
         Integer q = 675;
         double root;
         root = Math.sqrt(q);
         System.out.println("Root of " + q + " is: " + root);

         /** В этом случае преобразование кода в строку автоматически генерируется
          * компилятором Java. Этот способ плох тем, что при большом количестве переменных
          * и текста для вывода, легко потерять контроль над результатами.
          */

         // С форматированием :

         System.out.printf("Root of %d is %.2f", q, root);
 //--------------------------------------------------------------------------------------------------------------//
         int[][] multiplayTab = new int[10][10];
         System.out.println();
         System.out.println();
         for (int i = 0; i < 10; i++) {
             for (int j = 0; j < 10; j++) {
                 multiplayTab[i][j] = (i + 1) * (j + 1);

                 // вывод ряда чисел разделенных табуляцией;
                 //System.out.print(multiplayTab[i][j] + "\t");

                 /** Таблица выглядит ровно, но она слишком широкая.
                  * Для того, чтобы сделать таблицу более компактной,
                  * будем использовать метод printf(). Заменим в предыдущем коде строку */


                 System.out.printf("%4d", multiplayTab[i][j]);

                 /** Как мы видим, таблица стала компактнее. Более того,
                  * теперь мы можем уменьшать или увеличивать промежутки между числами по нашему желанию.
                  * Для этого нужно лишь заменить число 4 в выражении «%4d».
                  * */
             }

             System.out.println();
         }
     }
}