/**
 * @Title: Page.java
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.cnrsteel.orm
 * @Description: 分页。
 * @author hankaibo
 * @date 2013-12-19 下午2:41:14
 * @version V1.0
 */
package com.orm;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Page
 * @Description: 分页。
 * @author hankaibo
 * @date 2013-12-19 下午2:41:14
 * 
 */
public class Page<T> {
    /**
     * 当前页
     */
    protected int currentPage = 1;

    /**
     * 每页的记录条数
     */
    protected int pageSize = 10;

    /**
     * 排序的规则集
     */
    protected List<Sort> orders = new ArrayList<Sort>();

    /**
     * 自动计算总记录数
     */
    protected boolean autoCount = true;

    /**
     * 总记录数
     */
    protected long total = -1;

    /**
     * 返回结果集
     * 
     */
    private List<T> resultList;

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getFirst() {
        return ((currentPage - 1) * pageSize);
    }

    /**
     * 设置排序方式向.
     * 
     * @param order
     * 
     */
    public Page<T> addOrder(final Sort order) {
        orders.add(order);

        return this;
    }

    /**
     * 是否已设置排序字段,无默认值.
     */
    public boolean isOrderBySetted() {
        return orders.size() > 0;
    }

    public int getTotalPage() {
        return (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > 0) {
            this.currentPage = currentPage;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public List<Sort> getOrders() {
        return orders;
    }

    public void setOrders(List<Sort> orders) {
        this.orders = orders;
    }

    public boolean isAutoCount() {
        return autoCount;
    }

    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public List<T> doPageForList(List<T> totalList) {
        doSortForList(totalList);
        List<T> pageList = new ArrayList<T>();
        if (totalList != null) {
            int total = totalList.size();
            int fromIndex = getFirst();
            int toIndex = fromIndex + getPageSize();
            if (toIndex > total) {
                toIndex = total;
            }
            if (fromIndex > total) {
                return pageList;
            }
            pageList = totalList.subList(fromIndex, toIndex);
            setResultList(pageList);
            setTotal(total);
        }
        return pageList;
    }

    public void nextPage() {
        currentPage += 1;
    }

    public boolean hasNextPage() {
        return this.currentPage <= this.getTotalPage();
    }

    private void doSortForList(List<T> totalList) {
        // if (CollectionUtils.isNotEmpty(getOrders())) {
        // ComparatorChain comparatorChain = new ComparatorChain();
        // for (Sort sort : getOrders()) {
        // Comparator comparator = new BeanComparator(sort.getField());
        // if (sort.getStyle().equals(OrderStyle.DESC)) {
        // comparator = new ReverseComparator(comparator);
        // }
        // comparatorChain.addComparator(comparator);
        // }
        // Collections.sort(totalList, comparatorChain);
        // }
    }

}
