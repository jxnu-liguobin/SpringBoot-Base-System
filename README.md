*   系统管理后台基本框架包括用户管理，角色管理，资源链接管理，图书管理模块，可以动态分配权限和角色。
*   使用springboot、springdata jpa、shiro等服务端技术，使用freemarker模版渲染页面。
*   系统中对springdata的查询条件Specification做了简单的封装，更加方便查询条件的灵活使用。
*   前端技术：使用Hadmin系统模版，数据表格使用bootstrap table插件，弹窗使用layer插件，日期选择使用laydate插件。表单验证使用jQuery validate插件等等。
*   数据库使用Mysql,数据库连接池使用Druid支持多数据源，Spring Web与Service层监控

 
### 使用：
    1）使用mysql数据库，先建立一个空数据库base，使用utf-8字符集。
    2）把application.properties中的数据库连接信息修改成自己数据库的连接信息。默认使用master主数据源
    3）修改spring.jpa.hibernate.ddl-auto为create，目的是让系统自动建表同时初始化相关集成数据。
    如果不需要自动初始化数据，可以删除resource目录下的managesystemdb.sql文件。或者改为validate
    4）启动后，访问：http://localhost:8080/admin会自动跳转到后台登录页面。
    5）初始用户名和密码为：admin/111111。

### 3月26日
1、使用 druid sql监控
### 3月31
1、还书bug、还书日期格式化修复
2、增加统计欠款
3、改善UI显示
4、增加多数据源支持
### 4月2日
1、增加密码尝试次数限制
2、删除逻辑变更--->设置一个删除标志位
3、添加对删除或锁定的恢复
### 4月3日
1、增加学号存在动态验证
2、修复主页图书查询
3、增加图书管理页面的查询
4、增加不可重复删除
5、发现图书分页bug
6、增加空提交判断
7、修复图书分页bug[bootstrap table组件]
### 4月4日
1、添加登录界面的注册功能
2、表格导出功能没有使用权限
### 4月9日
1、添加用户自主修改个人信息功能【但学号与姓名不可改】
2、增加我的借阅，包含借阅书籍与借阅日期、待还日期等等、预期不可自主换设定、剩余还书操作未写
### 4月10日
1、添加自主还书后台
