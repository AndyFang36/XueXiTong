package edu.example.xuexitong;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
    @Test
    public void main() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String d=sdf.format(date);
        System.out.println(d);
    }
}
