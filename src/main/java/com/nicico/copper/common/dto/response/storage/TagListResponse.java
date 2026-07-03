package com.nicico.copper.common.dto.response.storage;

import com.nicico.copper.common.dto.Tag;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class TagListResponse implements Serializable {
    private static final long serialVersionUID = 1199075889990248453L;

    private int status;
    private String message;
    private List<Tag> tags;
}
