package ru.ok.rmatafonov.functest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import ru.ok.rmatafonov.config.WebDriverConfiguration;
import ru.ok.rmatafonov.functest.businessobjects.UserPrivateDataMonthManager;
import ru.ok.rmatafonov.functest.businessobjects.UsersManager;

@Configuration
@Import({WebDriverConfiguration.class})
@ComponentScan({"ru.ok.rmatafonov"})
@PropertySources({
        @PropertySource(value = "classpath:${APP_ENV:prod}.properties"),
        @PropertySource(value = "classpath:${TESTED_LANGUAGE:ru}_templates.properties")
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestingConfig {

    @Value("${ru.ok.rmatafonov.app.url}")
    private String propertyAppURL;

    @Value("${ru.ok.rmatafonov.account1.login}")
    private String propertyAccountLogin1;

    @Value("${ru.ok.rmatafonov.account1.password}")
    private String propertyAccountPassword1;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.text.template}")
    private String propertyPrivateDataSettingsTemplate;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.gender.descriptor.male}")
    private String propertyPrivateDataGenderDescriptorMale;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.gender.descriptor.female}")
    private String propertyPrivateDataGenderDescriptorFemale;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.jan}")
    private String propertyPrivateDataMonthOrigJan;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.jan}")
    private String propertyPrivateDataMonthChangedJan;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.feb}")
    private String propertyPrivateDataMonthOrigFeb;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.feb}")
    private String propertyPrivateDataMonthChangedFeb;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.mar}")
    private String propertyPrivateDataMonthOrigMar;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.mar}")
    private String propertyPrivateDataMonthChangedMar;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.apr}")
    private String propertyPrivateDataMonthOrigApr;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.apr}")
    private String propertyPrivateDataMonthChangedApr;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.may}")
    private String propertyPrivateDataMonthOrigMay;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.may}")
    private String propertyPrivateDataMonthChangedMay;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.jun}")
    private String propertyPrivateDataMonthOrigJun;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.jun}")
    private String propertyPrivateDataMonthChangedJun;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.jul}")
    private String propertyPrivateDataMonthOrigJul;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.jul}")
    private String propertyPrivateDataMonthChangedJul;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.aug}")
    private String propertyPrivateDataMonthOrigAug;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.aug}")
    private String propertyPrivateDataMonthChangedAug;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.sep}")
    private String propertyPrivateDataMonthOrigSep;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.sep}")
    private String propertyPrivateDataMonthChangedSep;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.oct}")
    private String propertyPrivateDataMonthOrigOct;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.oct}")
    private String propertyPrivateDataMonthChangedOct;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.nov}")
    private String propertyPrivateDataMonthOrigNov;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.nov}")
    private String propertyPrivateDataMonthChangedNov;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.orig.dec}")
    private String propertyPrivateDataMonthOrigDec;

    @Value("${ru.ok.rmatafonov.settings.main.privatedata.month.descriptor.changed.dec}")
    private String propertyPrivateDataMonthChangedDec;

    @Value("${ru.ok.rmatafonov.home.privatedata.hometown.pefix}")
    private String propertyPrivateDataHometownHomePagePrefix;

    @Value("${ru.ok.rmatafonov.home.privatedata.1.year.descriptor}")
    private String propertyPrivateDataHomeAgeDescriptor1;

    @Value("${ru.ok.rmatafonov.home.privatedata.2.3.4.years.descriptor}")
    private String propertyPrivateDataHomeAgeDescriptor234;

    @Value("${ru.ok.rmatafonov.home.privatedata.5.more.years.descriptor}")
    private String propertyPrivateDataHomeAgeDescriptor5more;

    @Bean
    public String appURL() {
        return propertyAppURL;
    }

    @Bean
    public UsersManager usersManager() {
        UsersManager usersManager = new UsersManager();
        usersManager.addUser(propertyAccountLogin1, propertyAccountPassword1);
        return usersManager;
    }

    @Bean
    public String privateDataSettingsTemplate() {
        return propertyPrivateDataSettingsTemplate;
    }

    @Bean
    public String privateDataGenderDescriptorMale() {
        return propertyPrivateDataGenderDescriptorMale;
    }

    @Bean
    public String privateDataGenderDescriptorFemale() {
        return propertyPrivateDataGenderDescriptorFemale;
    }

    @Bean
    public UserPrivateDataMonthManager userPrivateDataMonthManager() {
        UserPrivateDataMonthManager userPrivateDataMonthManager = new UserPrivateDataMonthManager();
        userPrivateDataMonthManager.addMonthsPair(0, propertyPrivateDataMonthOrigJan, propertyPrivateDataMonthChangedJan);
        userPrivateDataMonthManager.addMonthsPair(1, propertyPrivateDataMonthOrigFeb, propertyPrivateDataMonthChangedFeb);
        userPrivateDataMonthManager.addMonthsPair(2, propertyPrivateDataMonthOrigMar, propertyPrivateDataMonthChangedMar);
        userPrivateDataMonthManager.addMonthsPair(3, propertyPrivateDataMonthOrigApr, propertyPrivateDataMonthChangedApr);
        userPrivateDataMonthManager.addMonthsPair(4, propertyPrivateDataMonthOrigMay, propertyPrivateDataMonthChangedMay);
        userPrivateDataMonthManager.addMonthsPair(5, propertyPrivateDataMonthOrigJun, propertyPrivateDataMonthChangedJun);
        userPrivateDataMonthManager.addMonthsPair(6, propertyPrivateDataMonthOrigJul, propertyPrivateDataMonthChangedJul);
        userPrivateDataMonthManager.addMonthsPair(7, propertyPrivateDataMonthOrigAug, propertyPrivateDataMonthChangedAug);
        userPrivateDataMonthManager.addMonthsPair(8, propertyPrivateDataMonthOrigSep, propertyPrivateDataMonthChangedSep);
        userPrivateDataMonthManager.addMonthsPair(9, propertyPrivateDataMonthOrigOct, propertyPrivateDataMonthChangedOct);
        userPrivateDataMonthManager.addMonthsPair(10, propertyPrivateDataMonthOrigNov, propertyPrivateDataMonthChangedNov);
        userPrivateDataMonthManager.addMonthsPair(11, propertyPrivateDataMonthOrigDec, propertyPrivateDataMonthChangedDec);
        return userPrivateDataMonthManager;
    }

    @Bean
    public String privateDataHometownHomePagePrefix() {
        return propertyPrivateDataHometownHomePagePrefix;
    }

    @Bean
    public String privateDataHomeAgeDescriptor1() {
        return propertyPrivateDataHomeAgeDescriptor1;
    }

    @Bean
    public String privateDataHomeAgeDescriptor234() {
        return propertyPrivateDataHomeAgeDescriptor234;
    }

    @Bean
    public String privateDataHomeAgeDescriptor5more() {
        return propertyPrivateDataHomeAgeDescriptor5more;
    }

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
}
