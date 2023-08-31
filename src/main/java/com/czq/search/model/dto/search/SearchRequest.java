package com.czq.search.model.dto.search;

import com.czq.search.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
public class SearchRequest extends PageRequest implements Serializable {

    /**
     * 搜索条件
     */
    private String searchText;

    private String type;


    private static final long serialVersionUID = 1L;
}