package com.codenation.centraldeerros.controllers.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardError {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
}
