package com.example.common;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/11/27 20:24
 * @description:
 */
public class CustomerContextHolder {

    //用ThreadLocal来设置当前线程使用哪个dataSource
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        System.out.println("切换数据源到："+customerType);
        contextHolder.set(customerType);
    }
    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
