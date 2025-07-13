package org.apache.dubbo.demo.mine.spi;

public class LogServiceImplTwo implements LogService{
    @Override
    public String log(String msg) {
        return "LogServiceImplTwo: " + msg;
    }
}
