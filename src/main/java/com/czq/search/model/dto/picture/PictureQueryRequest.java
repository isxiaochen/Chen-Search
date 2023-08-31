package com.czq.search.model.dto.picture;

import com.czq.search.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
public class PictureQueryRequest extends PageRequest implements Serializable {

    private String searchText;
    private static final long serialVersionUID = 1L;
}