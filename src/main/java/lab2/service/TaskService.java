package lab2.service;

import lab2.model.Task;

import java.util.Collection;

public interface TaskService extends MainService<Task> {

    Collection<Task> findTasksWithoutDescription();
}
