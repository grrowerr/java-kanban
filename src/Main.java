/*
* Михаил, здравствуй!
*
* Хотел бы узнать твоего совета, можно ли как-то оптимизировать реализацию методов удаления всех эпиковв и сабтасков,
* а также создания сабтаска.
* Что-то мне совсем не нравится моя реализация, но я прямо говоря – полностью сломал свою голову, пока пытался придумать
* что-нибудь лучше.
*/

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // checking the methods for working with class Task
        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска", Status.NEW));
        Task secondTask = taskManager.createTask(new Task("Второй Таск", "Описание второго Таска", Status.NEW));
        Task thirdTask = taskManager.createTask(new Task("Третий Таск", "Описание третьего Таска", Status.NEW));

        System.out.println("Created task: " + firstTask);

        Task taskFromManagerById = taskManager.getTaskById(firstTask.getId());
        System.out.println("Get task by Id: " + taskFromManagerById);

        Task updatedTask = new Task("Обновленный первый таск", "Обновленное описание таска", taskFromManagerById.getId(), Status.IN_PROGRESS);
        taskManager.updateTask(updatedTask);
        System.out.println("Updated task: " + taskManager.getTaskById(updatedTask.getId()));
        System.out.println("Updated task: " + updatedTask);

        System.out.println(taskManager.getAllTasks());
        taskManager.deleteTaskById(updatedTask.getId());
        System.out.println(taskManager.getAllTasks());

        // checking the methods for working with class Epic and two subtasks
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));
        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск", "Описание СабТаска", Status.NEW), epic);
        SubTask secondSubTask = taskManager.createSubTask(new SubTask("Второй СабТаск", "Описание СабТаска", Status.NEW), epic);
        System.out.println("Created epic: " + taskManager.getAllEpics());
        System.out.println("Created subTask: " + taskManager.getAllSubTasks());
        System.out.println("Print all subTasks from epic: " + taskManager.returnSubTaskList(epic));

        SubTask subTaskFromManagerById = taskManager.getSubTaskById(firstSubTask.getId());
        System.out.println("Get first subTask by ID: " + subTaskFromManagerById);

        System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––");

        System.out.println("Epic: " + taskManager.getEpicById(epic.getId()));
        SubTask updatedFirstSubTask = new SubTask("Обновленный первый СабТаск", "Обновленное описание первого СабТаска", Status.DONE);
        taskManager.updateSubTask(updatedFirstSubTask, firstSubTask);
        System.out.println("Epic with updated first SubTask: " + epic);
        System.out.println("Updated first SubTask: " + updatedFirstSubTask);

        System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––");

        SubTask updatedSecondSubTask = new SubTask("Обновленный второй СабТаск", "Обновленное описание второго СабТаска", Status.DONE);
        taskManager.updateSubTask(updatedSecondSubTask, secondSubTask);
        System.out.println("Epic with both updated SubTasks: " + epic);
        System.out.println("Updated second SubTask: " + updatedSecondSubTask);
        System.out.println("Print all subTasks from epic: " + taskManager.returnSubTaskList(epic));

        System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––");

        //checking the methods for delete all tasks, subTasks and epics
        System.out.println("All epics: " + taskManager.getAllEpics());
        System.out.println("All tasks: " + taskManager.getAllTasks());
        System.out.println("All subTasks: " + taskManager.getAllSubTasks());

//        taskManager.removeAllTasks();
//        System.out.println("All tasks: " + taskManager.getAllTasks());

//        taskManager.removeAllSubTasks();
//        System.out.println("All subTasks: " + taskManager.getAllSubTasks());
//        System.out.println("Print all subTasks from epic: " + taskManager.returnSubTaskList(epic));

        taskManager.removeAllEpics();
        System.out.println("All epics: " + taskManager.getAllEpics());
        System.out.println("All subTasks: " + taskManager.getAllSubTasks());
        System.out.println("Print all subTasks from epic: " + taskManager.returnSubTaskList(epic));
    }
}
