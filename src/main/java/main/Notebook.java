package main;

import main.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Notebook {

    private static int currentId = 1;

    private static HashMap<Integer, Task> tasks = new HashMap<>();

    public static List<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.addAll(tasks.values());
        return taskList;
    }

    public static int addTask(Task task) {
        int id = currentId++;
        task.setId(id);
        task.setCreationTime(LocalDateTime.now());
        task.setDone(false);
        tasks.put(id, task);
        return id;
    }

    public static Task getTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        return null;
    }
}
