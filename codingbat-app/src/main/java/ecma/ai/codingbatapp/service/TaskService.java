package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.Category;
import ecma.ai.codingbatapp.entity.Task;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.payload.TaskDto;
import ecma.ai.codingbatapp.repository.CategoryRepository;
import ecma.ai.codingbatapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Task> getTask() {
        List<Task> all = taskRepository.findAll();
        return all;
    }

    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public ApiResponse addTask(TaskDto taskDto) {
        Task task = new Task();
        if (taskRepository.existsByNameAndCategoryId(task.getName(), taskDto.getCategoryId())) {
            return new ApiResponse("Bu categoryda shunday nomli task bor!", false);
        }
        task.setName(taskDto.getName());
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Category not found!", false);
        }
        task.setCategory(optionalCategory.get());
        task.setCompleted(taskDto.isCompleted());
        task.setDescription(taskDto.getDescription());
        taskRepository.save(task);
        return new ApiResponse("Task added!", true);
    }

    public ApiResponse editTask(Integer id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (taskRepository.existsByNameAndCategoryId(task.getName(), taskDto.getCategoryId())) {
                return new ApiResponse("Bu categoryda shunday nomli task bor!", false);
            }
            task.setName(taskDto.getName());
            Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
            if (!optionalCategory.isPresent()) {
                return new ApiResponse("Category not found!", false);
            }
            task.setCategory(optionalCategory.get());
            task.setCompleted(taskDto.isCompleted());
            task.setDescription(taskDto.getDescription());
            taskRepository.save(task);
            return new ApiResponse("Task editing!", true);
        }
        return new ApiResponse("Task not found!", false);
    }

    public ApiResponse deleteTask(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("Task deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Task not found!", false);
        }
    }

}
