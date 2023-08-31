package com.czq.search.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czq.search.model.entity.Picture;
import com.czq.search.common.BaseResponse;
import com.czq.search.common.ErrorCode;
import com.czq.search.common.ResultUtils;
import com.czq.search.exception.ThrowUtils;
import com.czq.search.model.dto.picture.PictureQueryRequest;
import com.czq.search.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;


    /**
     * 分页获取列表（封装类）
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                         HttpServletRequest request) {
        long size = pictureQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);


        Page<Picture> picturePage = pictureService.getPicturePage(pictureQueryRequest);
        return ResultUtils.success(picturePage);
    }


}
