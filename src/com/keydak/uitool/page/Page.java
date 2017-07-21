package com.keydak.uitool.page;

import java.util.List;

/**
 * User: caisz
 * Date: 2017/5/4
 * Time: 14:02
 * Description:
 */

public class Page<T> {

    private Integer pageSize = 8;
    private Integer pageIndex = 1;
    private Long total;
    private List<T> datas;



    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public Page() {}

    public Page( Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    // 计算页数
    public Long getPageCount() {
        assert total != null;
        assert pageSize != 0;
        Long pageCount = ( total%pageSize == 0 )? (total/pageSize) : ( total/pageSize + 1 );
        return pageCount;
    }

    // 设置页数据
    public Page<T> loadData(List<T> datas, Long total) {
        this.total = total;
        this.datas = datas;
        return this;
    }
}
