package com.example.validations.demo;

import com.example.validations.demo.dto.TaskDto;
import com.example.validations.demo.models.Task;
import com.example.validations.demo.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

	private final static Logger log = LoggerFactory.getLogger(DataInitializer.class);

	@Autowired
	private TaskService taskService;

	@EventListener
	@Order(1)
	public void initTask(ContextRefreshedEvent event) {
		log.info("===================== INIT TASKS =====================");
		List<TaskDto> taskList = Arrays.asList(
				new TaskDto(Long.valueOf("1"),"Setting up application", true),
				new TaskDto(Long.valueOf("2"), "Testing app", false));
		taskList
				.forEach(task -> taskService.create(task));
	}
}
