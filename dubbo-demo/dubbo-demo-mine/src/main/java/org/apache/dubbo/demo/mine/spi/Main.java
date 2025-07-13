package org.apache.dubbo.demo.mine.spi;


import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 测试 Java 原生的 SPI 机制
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<LogService> serviceLoader = ServiceLoader.load(LogService.class);

        Iterator<LogService> iterator = serviceLoader.iterator();

        while (iterator.hasNext()) {
            LogService log = iterator.next();
            System.out.println(log.log("hello world"));
        }
    }
}