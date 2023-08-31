package com.czq.search.controller;

import com.czq.search.common.BaseResponse;
import com.czq.search.common.ResultUtils;
import com.czq.search.manager.SearchFacade;
import com.czq.search.service.PictureService;
import com.czq.search.service.PostService;
import com.czq.search.service.UserService;
import com.czq.search.model.dto.search.SearchRequest;
import com.czq.search.model.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private PictureService pictureService;

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    @Resource
    private SearchFacade searchFacade;

    /**
     * 分页获取列表（封装类）
     *
     * @param searchRequest
     * @param request
     * @return
     */
    @PostMapping("/all")
    public BaseResponse<SearchVo> listSearch(@RequestBody SearchRequest searchRequest,
                                             HttpServletRequest request) {
        return ResultUtils.success(searchFacade.listSearch(searchRequest,request));
    }


}
