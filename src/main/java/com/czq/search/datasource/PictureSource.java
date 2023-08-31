package com.czq.search.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czq.search.model.dto.picture.PictureQueryRequest;
import com.czq.search.model.entity.Picture;
import com.czq.search.service.PictureService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 图片数据源适配器
 */
@Component
public class PictureSource implements DataSource<Picture> {

    @Resource
    private PictureService pictureService;

    @Override
    public Page<Picture> doSearch(String searchText, long current, long pageSize) {
        PictureQueryRequest pictureQueryRequest = new PictureQueryRequest();
        pictureQueryRequest.setSearchText(searchText);
        pictureQueryRequest.setCurrent(current);
        pictureQueryRequest.setPageSize(pageSize);
        return pictureService.getPicturePage(pictureQueryRequest);


    }
}
