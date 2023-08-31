package com.czq.search.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czq.search.datasource.*;
import com.czq.search.model.entity.Picture;
import com.czq.search.model.entity.Post;
import com.czq.search.model.enums.SearchTypeEnum;
import com.czq.search.model.dto.search.SearchRequest;
import com.czq.search.model.vo.PostVO;
import com.czq.search.model.vo.SearchVo;
import com.czq.search.model.vo.UserVO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 搜索门面模式
 */
@Component
public class SearchFacade {

    @Resource
    private PostDataSource postDataSource;

    @Resource
    private UserDataSource userDataSource;

    @Resource
    private PictureSource pictureSource;

    @Resource
    private DataSourceRegister dataSourceRegister;



    public SearchVo listSearch(@RequestBody SearchRequest searchRequest,
                                             HttpServletRequest request) {

        long current = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();

        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
        String searchText = searchRequest.getSearchText();
        SearchVo searchVo = new SearchVo();

        if (searchTypeEnum == null){
            //1.获取文章搜索结果
            CompletableFuture<List<Post>> postFuture = CompletableFuture.supplyAsync(() ->
                    postDataSource.doSearch(searchText, current, pageSize).getRecords());

            //2.获取用户搜索结果
            CompletableFuture<List<UserVO>> userFuture = CompletableFuture.supplyAsync(() ->
                    userDataSource.doSearch(searchText, current, pageSize).getRecords());

            //3.获取图片搜索结果
            CompletableFuture<List<Picture>> pictureFuture = CompletableFuture.supplyAsync(() ->
                    pictureSource.doSearch(searchText, current, pageSize).getRecords());
            CompletableFuture.allOf(postFuture,userFuture,pictureFuture).join();

            try {
                List<Post> postVOList= postFuture.get();
                List<UserVO> userVOList = userFuture.get();
                List<Picture> pictureList = pictureFuture.get();

                searchVo.setPostList(postVOList);
                searchVo.setUserList(userVOList);
                searchVo.setPictureList(pictureList);
                return searchVo;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }


        //4.注册器模式优化switch-case并实现根据type搜索特定的数据源
        DataSource<?> dataSource = dataSourceRegister.getDataSourceByType(type);
        Page<?> page = dataSource.doSearch(searchText, current, pageSize);
        searchVo.setDataList(page.getRecords());
        return searchVo;

    }


}
