package com.example.validations.demo.services;

import com.example.validations.demo.dto.TaskDto;
import com.example.validations.demo.exceptions.TaskNotFoundException;
import com.example.validations.demo.models.Task;
import com.example.validations.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<TaskDto> findAll() {
		return taskRepository.findAll().stream()
				.map(task -> new TaskDto(task.getId(), task.getDescription(), task.isCompleted()))
				.collect(Collectors.toList());
	}

	public TaskDto create(TaskDto taskDto) {
		Task task = new Task(taskDto.getDescription(), taskDto.isCompleted());
		Task savedTask = taskRepository.save(task);
		return new TaskDto(savedTask.getId(), savedTask.getDescription(), savedTask.isCompleted());
	}

	public TaskDto update(Long id, TaskDto task) {
		Task taskEntity = findOneSafe(id);
		taskEntity.setDescription(task.getDescription());
		taskEntity.setCompleted(task.isCompleted());
		return new TaskDto(taskEntity.getId(), taskEntity.getDescription(), taskEntity.isCompleted());
	}

	public void delete(Long id) {
		Task task = findOneSafe(id);
		taskRepository.delete(task);
	}

	private Task findOneSafe(Long id) {
		Optional<Task> task = taskRepository.findById(id);
		if (!task.isPresent()) {
			throw new TaskNotFoundException(id);
		}
		return task.get();
	}
}