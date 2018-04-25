package com.example.validations.demo.exceptions;

import com.example.validations.demo.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ControllerAdvice
public class ExceptionsController {


	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public MessageDto handleValidationException(MethodArgumentNotValidException exception) {
		Locale locale = LocaleContextHolder.getLocale();
		String code = exception.getBindingResult().getFieldError().getDefaultMessage();
		return new MessageDto(messageSource.getMessage(code, null, locale));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TaskNotFoundException.class)
	@ResponseBody
	public MessageDto handleTaskNotFoundException(TaskNotFoundException exception) {
		String[] parameters = {Long.toString(exception.getTaskId())};
		Locale locale = LocaleContextHolder.getLocale();
		return new MessageDto(messageSource.getMessage("exception.TaskNotFound.description", parameters, locale));
	}
}
