package com.nicico.copper.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GeneralResponse implements Serializable {

    private static final long serialVersionUID = 6343393172131604887L;

    private int status;
    private String message;
}
