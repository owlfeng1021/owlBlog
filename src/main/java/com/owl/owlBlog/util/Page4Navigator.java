package com.owl.owlBlog.util;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * //jpa 传递出来的分页对象， Page4Navigator 类就是对它进行封装以达到扩展的效果
 * Page<T> pageFromJPA;
 *
 * //分页的时候 ,如果总页数比较多，那么显示出来的分页超链一个有几个。 比如如果分页出来的超链是这样的： [8,9,10,11,12], 那么 navigatePages 就是5
 * int navigatePages;
 *
 * //总页面数
 * int totalPages;
 *
 * //第几页（基0）
 * int number;
 *
 * //总共有多少条数据
 * long totalElements;
 *
 * //一页最多有多少条数据
 * int size;
 *
 * //当前页有多少条数据 (与 size，不同的是，最后一页可能不满 size 个)
 * int numberOfElements;
 *
 * //数据集合
 * List<T> content;
 *
 * //是否有数据
 * boolean isHasContent;
 *
 * //是否是首页
 * boolean first;
 *
 * //是否是末页
 * boolean last;
 *
 * //是否有下一页
 * boolean isHasNext;
 *
 * //是否有上一页
 * boolean isHasPrevious;
 *
 * @param <T>
 */
public class Page4Navigator<T> {
    Page<T> pageFromJPA; //这里是 jpa自带的 page

    int navigatePages;

    int totalPages;

    int number;

    long totalElements;

    int size;

    int numberOfElements;

    List<T> list; // 这里是查出来的内容

    boolean isHasContent; //

    boolean first;

    boolean last;

    boolean isHasNext;

    boolean isHasPrevious;// 有没有上一页

    int[] navigatepageNums; //id的集合

    public Page4Navigator() {
        //这个空的分页是为了 Redis 从 json格式转换为 Page4Navigator 对象而专门提供的
    }

    /**
     * @description
     * @param pageFromJPA
     * @param navigatePages
     */
    public Page4Navigator(Page<T> pageFromJPA, int navigatePages) {
        this.pageFromJPA = pageFromJPA; //这个是查询的对象
        this.navigatePages = navigatePages; // 这是分页的超链
//      下面是把 jpa 封装的分页属性查到 自己封装的对象里面
        totalPages = pageFromJPA.getTotalPages();

        number  = pageFromJPA.getNumber() ;

        totalElements = pageFromJPA.getTotalElements();

        size = pageFromJPA.getSize();

        numberOfElements = pageFromJPA.getNumberOfElements();

        list = pageFromJPA.getContent();

        isHasContent = pageFromJPA.hasContent();

        first = pageFromJPA.isFirst();

        last = pageFromJPA.isLast();

        isHasNext = pageFromJPA.hasNext();

        isHasPrevious  = pageFromJPA.hasPrevious();
//        最后是把封装好的对象 进行判断
        calcNavigatepageNums();

    }
// calculation 计算
    private void calcNavigatepageNums() {
        int navigatepageNums[];
        int totalPages = getTotalPages();
        int num = getNumber(); //Number 是第几页 基于0
        //当总页数小于或等于导航页码数时 （5）
        if (totalPages <= navigatePages) {
            navigatepageNums = new int[totalPages];
            for (int i = 0; i < totalPages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatepageNums = new int[navigatePages]; // 6-2 6+2  4 8
            int startNum = num - navigatePages / 2;
            int endNum = num + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
            else if (endNum > totalPages) {
                endNum = totalPages;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
        this.navigatepageNums = navigatepageNums;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return list;
    }

    public void setContent(List<T> content) {
        this.list = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean isHasContent) {
        this.isHasContent = isHasContent;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean isHasNext) {
        this.isHasNext = isHasNext;
    }

    public boolean isHasPrevious() {
        return isHasPrevious;
    }

    public void setHasPrevious(boolean isHasPrevious) {
        this.isHasPrevious = isHasPrevious;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

}
