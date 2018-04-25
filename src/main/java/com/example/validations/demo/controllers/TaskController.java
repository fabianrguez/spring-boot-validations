package com.example.validations.demo.controllers;

import com.example.validations.demo.dto.MessageDto;
import com.example.validations.demo.dto.TaskDto;
import com.example.validations.demo.services.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Find all tasks", notes = "Retrieving the collection of user tasks", response = TaskDto[].class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = TaskDto[].class)
	})
	public List<TaskDto> findAllTasks() {
		return taskService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create task", notes = "Creating a new user task", response = TaskDto.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = TaskDto.class),
			@ApiResponse(code = 400, message = "Bad request", response = MessageDto.class)
	})
	public TaskDto createTask(
			@ApiParam(required = true, name = "task", value = "New task")
			@Valid @RequestBody TaskDto task) {
		return taskService.create(task);
	}

	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Update task", notes = "Updating an existing user task", response = TaskDto.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = TaskDto.class),
			@ApiResponse(code = 400, message = "Bad request", response = MessageDto.class),
			@ApiResponse(code = 404, message = "Not found", response = MessageDto.class)
	})
	public TaskDto updateTask(
			@ApiParam(required = true, name = "id", value = "ID of the task you want to update", defaultValue = "0")
			@PathVariable Long id,
			@ApiParam(required = true, name = "task", value = "Updated taks")
			@RequestBody TaskDto task) {
		return taskService.update(id, task);
	}

	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Delete task", notes = "Deleting an existing user task")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Success"),
			@ApiResponse(code = 404, message = "Not found", response = MessageDto.class)
	})
	public void delete(
			@ApiParam(required = true, name = "id", value = "ID of the task you want to delete")
			@PathVariable Long id) {
		taskService.delete(id);
	}
}
