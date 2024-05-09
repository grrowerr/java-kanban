package ru.yandex.javacource.fadeev.schedule.service;

import ru.yandex.javacource.fadeev.schedule.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    protected List <Task> lastTenTasks = new ArrayList<>();
    @Override
    public void add(Task task) {

        if (lastTenTasks.size() >= 10) {
            lastTenTasks.removeFirst();
        }
        lastTenTasks.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return lastTenTasks;
    }


}
