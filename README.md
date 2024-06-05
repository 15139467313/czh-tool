# czh-tool 快速开发框架工具
# [文档地址](http://czh.znunwm.top/)  http://czh.znunwm.top/
### 介绍
czh-tool 基于SpringBoot一款快速单体框架里面集成了快速开发需要的环境架构代码集成工具

```
 项目基本保持每日更新，右上随手点个 🌟 Star 关注，这样才有持续下去的动力，谢谢～

```

## 2. 快速入门  (仅支持SprinBoot2x版本不支持3x版本)

### 3.1 引入czh-tool组件

czh-tool已发布至maven中央仓库，我们可以直接引入到项目中。

maven依赖如下：

xml

```java
    //仅支持sprinboot2x版本不支持3x版本
    <dependency>
            <groupId>io.github.15139467313</groupId>
            <artifactId>czh-tool</artifactId>
            <version>0.1.9</version>
        </dependency>
```

### 3.2 启用czh-tool

在启动类中引入@ EnableCzhTool注解，即可启用czh-tool组件。

```java
@EnableCzhTool
@SpringBootApplication
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
```

如果您觉得这个项目对您有帮助，可以帮作者买杯饮料鼓励鼓励!

<img src="https://znunwm.top/upload/2023/04/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230402163414.jpg" width = "230" height="300" style="float:left; margin: 15px;"/>




<img src="https://znunwm.top/upload/2023/04/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230402161550.jpg" width = "230" height="300" style="float:left; margin-left: 35px;"/>


# 作者信息

- 作者：陈思源(WP)
- 邮箱：2766694258@qq.com
- 微信：15139467313
- 就点点🌟 Star 🌟 关注更新，支持下作者就可以了





