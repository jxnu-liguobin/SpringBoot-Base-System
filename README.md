*   系统管理后台基本功能包括用户管理，角色管理，资源链接管理，图书管理模块，SQL监控，个人信息查看与密码修改，待还图书看出与自主还书。可以动态分配权限和角色。
*   使用SpringBoot、SpringData JPA、Shiro等技术，使用Freemarker模版渲染页面。
*   系统中对SpringData的查询条件Specification做了简单的封装，更加方便。
*   前端技术：使用Hadmin系统模版，数据表格使用BootStrap Table插件，弹窗使用Layer插件，日期选择使用Laydate插件。表单验证使用JQuery Validate插件等等。
*   数据库使用MySQL,数据库连接池使用Druid支持多数据源，Spring Web与Service层监控。
 
### 预览图
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF.png)
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E6%9F%A5%E8%AF%A2.png)
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E7%94%A8%E6%88%B7%E7%AE%A1%E7%90%86.png)
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E7%99%BB%E5%BD%95%E6%B3%A8%E5%86%8C.png)
 
### 使用：
    1）使用mysql数据库，先建立一个空数据库base，使用utf-8字符集。
    2）把application.properties中的数据库连接信息修改成自己数据库的连接信息。默认使用master主数据源
    3）修改spring.jpa.hibernate.ddl-auto为create，目的是让系统自动建表同时初始化相关集成数据。
    如果不需要自动初始化数据，可以删除resource目录下的managesystemdb.sql文件。或者改为validate
    4）启动后，访问：http://localhost:8080/admin会自动跳转到后台登录页面。
    5）初始用户名和密码为：admin/adminm。

### ^ ^
        增加全局异常处理
        增加显示系统环境变量，与应用监听器
        使用 druid sql监控
        还书bug、还书日期格式化修复
        增加统计欠款
        改善UI显示
        增加多数据源支持
        增加密码尝试次数限制
        删除逻辑变更--->设置一个删除标志位
        添加对删除或锁定的恢复
        增加学号存在动态验证
        修复主页图书查询
        增加图书管理页面的查询
        增加不可重复删除
        发现图书分页bug
        增加空提交判断
        修复图书分页bug[bootstrap table组件]
        添加登录界面的注册功能
        表格导出功能没有使用权限
        添加用户自主修改个人信息功能【但学号与姓名不可自主更改】
        增加我的借阅，包含借阅书籍与借阅日期、待还日期等等、预期不可自主换设定
        添加自主还书后台
        完全区分，删除用户与删除数据【前者相当于注销，后者完全删除】
