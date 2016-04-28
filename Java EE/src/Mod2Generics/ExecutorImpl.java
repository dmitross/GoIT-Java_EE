package Mod2Generics;

import java.util.ArrayList;
import java.util.List;

public class ExecutorImpl<T> implements Task<T>{

    List<T> tasks = new ArrayList<>();
    List<Task> results = new ArrayList<>();

    public void addTask (T task) {
        tasks.add(task);
    }

    public void execute() {
        for (T t : tasks) {
            t.execute();
        }
    }

    public T getResult() {
        return (T) results;
    }
}


