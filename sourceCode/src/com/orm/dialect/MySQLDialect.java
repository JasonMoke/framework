package com.orm.dialect;

/**
 * @ClassName: MySQLDialect
 * @Description: mysql 分页支持
 * @author hankaibo
 * @date 2013-12-26 上午11:07:18
 * 
 */
public class MySQLDialect implements Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    public String getPageSql(String sql, boolean hasOffset) {
        return MySQLDialectHepler.getPageSql(sql, -1, -1);
    }

    public String getPageSql(String sql, int offset, int limit) {
        return MySQLDialectHepler.getPageSql(sql, offset, limit);
    }

    public boolean supportsLimit() {
        return true;
    }
}
