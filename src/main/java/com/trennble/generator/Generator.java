package com.trennble.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;


public class Generator {
    public static void main(String[] args) {
        String modelName = "lottery_rule_info";
        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(getGlobalConfig());
        generator.setDataSource(getDataSourceConfig());
        generator.setStrategy(getStrategyConfig(modelName));
        generator.setPackageInfo(getPackageConfig(modelName));
        generator.execute();
    }

    private static GlobalConfig getGlobalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("/Users/edz/IdeaProjects/my-toy/src/main/java/com/trennble/generator/target");
        globalConfig.setAuthor("jiangbo");
        globalConfig.setOpen(false);
        globalConfig.setKotlin(false);
        globalConfig.setSwagger2(true);
        globalConfig.setFileOverride(true);
        globalConfig.setDateType(DateType.ONLY_DATE);
        return globalConfig;
    }

    private static DataSourceConfig getDataSourceConfig(){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://172.31.120.11:3306/lottery_dev?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("dxb_dev");
        dataSourceConfig.setPassword("lF!OJGGqXDXf");

        return dataSourceConfig;
    }

    private static StrategyConfig getStrategyConfig(String modelName){
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setTablePrefix("fj_");
        strategyConfig.setLikeTable(new LikeTable("fj_"+modelName, SqlLike.RIGHT));
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setSkipView(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        List<TableFill> tableFillList=new ArrayList<>();
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_time", FieldFill.UPDATE));
        strategyConfig.setTableFillList(tableFillList);
        strategyConfig.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // strategyConfig.setSuperServiceClass("com.trennble.generator.base.BaseService");
        strategyConfig.setSuperServiceImplClass("com.trennble.generator.base.BaseServiceImpl");
        return strategyConfig;
    }
    private static PackageConfig getPackageConfig(String modelName){
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setParent(modelName);
        packageConfig.setEntity("entity");
        return packageConfig;
    }
}