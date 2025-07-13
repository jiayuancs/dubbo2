package org.apache.dubbo.demo.mine.spi;

public class LogServiceImplOne implements LogService{
    @Override
    public String log(String msg) {
        return "LogServiceImplOne: " + msg;
    }
}
