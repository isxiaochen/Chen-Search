package com.czq.search.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czq.search.service.UserService;
import com.czq.search.model.dto.user.UserQueryRequest;
import com.czq.search.model.vo.UserVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户数据源适配器
 */
@Component
public class UserDataSource implements DataSource<UserVO>{

    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, long current, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setCurrent(current);
        userQueryRequest.setPageSize(pageSize);
        return userService.listUserVOByPage(userQueryRequest);
    }
}
