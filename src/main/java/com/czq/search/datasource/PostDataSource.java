package com.czq.search.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czq.search.service.PostService;
import com.czq.search.model.dto.post.PostQueryRequest;
import com.czq.search.model.entity.Post;
import com.czq.search.model.vo.PostVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 文章数据源规范适配器
 */
@Component
public class PostDataSource implements DataSource<Post>{

    @Resource
    private PostService postService;


    @Override
    public Page<Post> doSearch(String searchText, long current, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(current);
        postQueryRequest.setPageSize(pageSize);
        //从es中查询
        return postService.searchFromEs(postQueryRequest);
    }
}
