package com.orm.dialect;

/**
 * @ClassName: SQLServerDialect
 * @Description: sql server分页支持
 * @author hankaibo
 * @date 2013-12-26 上午11:10:09
 * 
 */
public class SQLServerDialect implements Dialect {

    public String getPageSql(String sql, int offset, int limit) {
        sql = sql.trim();
        StringBuffer pageSql = new StringBuffer(sql.length() + 100);
        // 其实这里还是有一点问题的，就是排序问题，指定死了，有解决的提供一下，等复习到Hibernate看看Hibernat内部是如何实现的。
        pageSql.append("select * from(select a.*,row_number() over (order by id desc) rownum from( ");
        pageSql.append(sql);
        pageSql.append(") a )b where rownum> " + offset + " and rownum <= " + (offset + limit));
        return pageSql.toString();
    }

}
