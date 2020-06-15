package com.ysdrzp.oa.common;

import java.util.List;

/**
 * 封装分页
 * @param <T>
 */
public class Page <T> {

    private Integer currentPage = 0;

    private Integer pageSize = 10;

    /**
     * 总记录数
     */
    private Integer totalCount;

    /**
     * 总分页数
     */
    private Integer totalPage;

    private List<?> list;

    private String url;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        if (totalCount % pageSize == 0){
            totalPage = totalCount/pageSize;
        }else {
            totalPage = totalCount/pageSize + 1;
        }
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
