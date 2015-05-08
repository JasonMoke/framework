package com.orm;

/**
 * 排序
 * 
 * @author zhaopuqing
 * @created 2012-7-16 下午1:57:10
 * @since v1.0
 * @history
 * @see
 */
public class Sort {
    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序方式
     */
    private OrderStyle style;

    public enum OrderStyle {
        ASC, // 升序
        DESC // 降序
    }

    /**
     * 私有构造方法防止外部初始化
     * 
     * @param field
     *            参与排序的字段
     * @param style
     *            排序方式 ASC, DESC
     */
    private Sort(String field, OrderStyle style) {
        this.field = field;
        this.style = style;
    }

    /**
     * 创建升序对象
     * 
     * @param field
     *            排序字段
     * @return 排序对象
     */
    public static Sort asc(String field) {
        return new Sort(field, OrderStyle.ASC);
    }

    /**
     * 创建降序对象
     * 
     * @param field
     *            排序字段
     * @return 排序对象
     */
    public static Sort desc(String field) {
        return new Sort(field, OrderStyle.DESC);
    }

    /**
     * 本条件是否为升序
     * 
     * @return 是否为升序
     */
    public boolean isAsc() {
        return OrderStyle.ASC.equals(this.style);
    }

    @Override
    public String toString() {
        return new StringBuffer(this.field).append(" ").append(this.style).toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result *= this.getField().hashCode();
        result *= this.style.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        // obj's type is correct and is not null
        if (!(obj instanceof Sort))
            return false;
        Sort s = (Sort) obj;
        return s.style == this.style && (s.getField() == null ? false : s.getField().equals(this.field));
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public OrderStyle getStyle() {
        return style;
    }

}
