package com.czq.search.datasource;

import com.czq.search.model.enums.SearchTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册器模式优化数据源的注册，既优化switch-case注册
 */
@Component
public class DataSourceRegister {

    @Resource
    private PostDataSource postDataSource;

    @Resource
    private UserDataSource userDataSource;

    @Resource
    private PictureSource pictureSource;

    private Map<String,DataSource<?>> typeDataSourceMap;

    @PostConstruct
    public void doInit(){
        typeDataSourceMap = new HashMap<>();
        typeDataSourceMap.put(SearchTypeEnum.POST.getValue(),postDataSource);
        typeDataSourceMap.put(SearchTypeEnum.USER.getValue(),userDataSource);
        typeDataSourceMap.put(SearchTypeEnum.PICTURE.getValue(),pictureSource);
    }
    public DataSource<?> getDataSourceByType(String type){
        return typeDataSourceMap.get(type);
    }


}
