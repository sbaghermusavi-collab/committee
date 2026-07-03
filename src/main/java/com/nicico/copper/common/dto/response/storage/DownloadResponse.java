package com.nicico.copper.common.dto.response.storage;

import com.nicico.copper.common.dto.Tag;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DownloadResponse implements Serializable {

    private static final long serialVersionUID = -4734157797992387529L;
    private int status;
    private String message;
    private byte[] content;
    private List<Tag> tags;
}
