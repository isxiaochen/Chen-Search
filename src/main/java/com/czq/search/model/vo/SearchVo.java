package com.czq.search.model.vo;

import com.czq.search.model.entity.Picture;
import com.czq.search.model.entity.Post;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 聚合搜索结果
 */
@Data
public class SearchVo implements Serializable {

    private List<Post> postList;
    private List<Picture> pictureList;
    private List<UserVO> userList;

    private List<?> dataList;

    private static final long serialVersionUID = 1L;
}
