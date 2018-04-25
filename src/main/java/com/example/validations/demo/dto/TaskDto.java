package com.example.validations.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskDto {

	private Long id;

	@NotNull(message = "NotNull.taskDto.description")
	@Size(min = 1, max = 64, message = "Size.taskDTO.description")
	private String description;
	private boolean completed;

	public TaskDto() {
	}

	public TaskDto(Long id, String description, boolean completed) {
		this.id = id;
		this.description = description;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
