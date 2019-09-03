package com.corren.lotto.alert;

import com.oneapm.touch.retrofit.boot.RetrofitServiceScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@RetrofitServiceScan
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AlertApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlertApplication.class, args);
    }

}
