package Mod4Ant;

public interface Task<T> {

    void execute();

     T getResult();


}