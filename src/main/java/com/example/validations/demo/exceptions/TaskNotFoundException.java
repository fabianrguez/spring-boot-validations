package com.example.validations.demo.exceptions;

public class TaskNotFoundException extends RuntimeException {

	private Long taskId;

	public TaskNotFoundException() {
		super();
		this.taskId = null;
	}

	public TaskNotFoundException(Long taskId) {
		super();
		this.taskId = taskId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
}
