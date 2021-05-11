package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.StarBadge;
import ecma.ai.codingbatapp.entity.Task;
import ecma.ai.codingbatapp.entity.User;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.payload.UserDto;
import ecma.ai.codingbatapp.repository.StarBadgeRepository;
import ecma.ai.codingbatapp.repository.TaskRepository;
import ecma.ai.codingbatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    StarBadgeRepository starBadgeRepository;

    public List<User> getUser() {
        List<User> all = userRepository.findAll();
        return all;
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public ApiResponse addUser(UserDto userDto) {
        User user = new User();
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return new ApiResponse("This is email already exists!", false);
        }
        user.setEmail(userDto.getEmail());
        if (userRepository.existsByPassword(userDto.getPassword())) {
            return new ApiResponse("This is password already exists!", false);
        }
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName());
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (!optionalStarBadge.isPresent()) {
            return new ApiResponse("Star badge not found!", false);
        }
        user.setStarBadge(optionalStarBadge.get());

        List<Integer> taskList = userDto.getTaskList();
        List<Task> taskList1 = new ArrayList<>();

        for (Integer integer : taskList) {
            Optional<Task> optionalTask = taskRepository.findById(integer);
            if (!optionalTask.isPresent()) {
                return new ApiResponse("Task not found!", false);
            }
            taskList1.add(optionalTask.get());
        }
        user.setTaskList(taskList1);
        userRepository.save(user);

        return new ApiResponse("User added", true);
    }

    public ApiResponse editUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userRepository.existsByEmail(userDto.getEmail())) {
                return new ApiResponse("This is email already exists!", false);
            }
            user.setEmail(userDto.getEmail());
            if (userRepository.existsByPassword(userDto.getPassword())) {
                return new ApiResponse("This is password already exists!", false);
            }
            user.setPassword(userDto.getPassword());
            user.setFullName(userDto.getFullName());
            Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
            if (!optionalStarBadge.isPresent()) {
                return new ApiResponse("Star badge not found!", false);
            }
            user.setStarBadge(optionalStarBadge.get());

            List<Integer> taskList = userDto.getTaskList();
            List<Task> taskList1 = new ArrayList<>();

            for (Integer integer : taskList) {
                Optional<Task> optionalTask = taskRepository.findById(integer);
                if (!optionalTask.isPresent()) {
                    return new ApiResponse("Task not found!", false);
                }
                taskList1.add(optionalTask.get());
            }
            user.setTaskList(taskList1);
            userRepository.save(user);

            return new ApiResponse("User editing!", true);
        }
        return new ApiResponse("User not found!", false);
    }

    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("User deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("User not found!", false);
        }
    }
}
