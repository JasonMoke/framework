package com.orm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyBatisSql {
    /**
     * @Fields sql :运行期的sql
     */
    private String sql;
    /**
     * @Fields parameters :运行期的参数
     */
    private Object[] parameters;
    /**
     * @Fields resultClass :<select id="XXX" resultType="ZZZ">中的resultType
     */
    private Class<?> resultClass;

    // ---------------getter setter----------------
    public Class<?> getResultClass() {
        return resultClass;
    }

    public void setResultClass(Class<?> resultClass) {
        this.resultClass = resultClass;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        if (parameters == null || sql == null) {
            return "";
        }

        List<Object> parametersArray = Arrays.asList(parameters);
        List<Object> list = new ArrayList<Object>(parametersArray);

        for (Object object : list) {

            if (object instanceof Number) {
                sql = sql.replaceFirst("\\?", object.toString());
            } else {
                sql = sql.replaceFirst("\\?", "'" + object + "'");
            }

        }
        return sql.replaceAll("(\r?\n(\\s*\r?\n)+)", "\r\n");
    }

}
