package com.czq.search.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czq.search.model.dto.picture.PictureQueryRequest;
import com.czq.search.model.entity.Picture;

/**
 * 图片服务
 */
public interface PictureService {

    Page<Picture> getPicturePage(PictureQueryRequest pictureQueryRequest);

}
