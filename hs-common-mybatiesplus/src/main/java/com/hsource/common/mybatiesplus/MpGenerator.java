package com.hsource.common.mybatiesplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MpGenerator {

    public static void main(String[] args) {

        //文件夹名称
        String packageName = "com.hsource";
        //输出目录
        String outDir = "D://MpGenerator";
        generateByTables(packageName, outDir, "saying");
        System.out.print("接口测试!");
    }

    private static void generateByTables(String packageName, String outDir, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://192.168.135.123:3307/hs";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("zxw123")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .entityTableFieldAnnotationEnable(true)
                //表前缀
                .setTablePrefix(new String[]{""})
                //修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames);

        config.setActiveRecord(true)
                .setFileOverride(true)
                // 不需要ActiveRecord特性的请改为false
                .setActiveRecord(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                .setAuthor("xwzhou")
                .setOutputDir(outDir)
                .setFileOverride(true)

                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController");

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                                .setService("service")
                                .setMapper("mapper")
                ).execute();
    }
}
