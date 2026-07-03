package com.nicico.copper.common.dto.response.storage;

import com.nicico.copper.common.dto.Tag;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UploadResponse implements Serializable {
    private static final long serialVersionUID = -3938739687049527248L;

    private int status;
    private String message;
    private String key;
    private List<Tag> tags;
}
