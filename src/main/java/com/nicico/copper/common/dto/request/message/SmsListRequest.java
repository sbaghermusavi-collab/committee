package com.nicico.copper.common.dto.request.message;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SmsListRequest implements Serializable {

    private static final long serialVersionUID = 6941429044081437360L;

    private List<String> to;
    private String message;
}
