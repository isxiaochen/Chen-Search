package com.czq.search.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 搜索数据源的规范，数据源要接入我们的系统，必须遵循此规范
 * 适配器模式让接入的数据源适配规范
 */
public interface DataSource<T> {


    /**
     * 规范是支持分页搜索和关键词搜索
     * @param searchText
     * @param current
     * @param pageSize
     * @return
     */
    Page<T> doSearch(String searchText,long current,long pageSize);
}
