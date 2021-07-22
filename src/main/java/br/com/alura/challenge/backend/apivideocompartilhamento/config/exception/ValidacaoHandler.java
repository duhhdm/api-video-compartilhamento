package br.com.alura.challenge.backend.apivideocompartilhamento.config.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alura.challenge.backend.apivideocompartilhamento.dto.ValidacaoDto;

@RestControllerAdvice
public class ValidacaoHandler {
	
	@Autowired
	MessageSource messageSource;
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidacaoDto> handle(MethodArgumentNotValidException exception){
		List<ValidacaoDto> list = new ArrayList<>();
		List<FieldError> listError = exception.getBindingResult().getFieldErrors();
		
		listError.forEach(e ->{
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ValidacaoDto validacaoDto = new ValidacaoDto(e.getField(),mensagem);
			list.add(validacaoDto);
		});
		return list;
	}
}
