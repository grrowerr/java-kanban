/*
* Исправил 14 строку: поменял название списка последних 10 задач на history, убрал лишний пробел, поменял модификатор
* доступа на private, так-как прямой доступ к этому полю не нужен
*
* В 18 строке добавил перенос строки, чтобы визуально разделить объявление переменных и методов
*
* В 23 строке добавил проверку Таски на null
*/

package ru.yandex.javacource.fadeev.schedule.service;

import ru.yandex.javacource.fadeev.schedule.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    private List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }

        if (history.size() >= 10) {
            history.removeFirst();
        }
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }


}
