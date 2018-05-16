![AUR](https://img.shields.io/aur/license/yaourt.svg)
##  项目说明 
*   系统管理后台基本功能包括:

        用户管理 
        角色管理
        资源链接管理
        图书管理
        
        权限动态分配
        SQL监控
        个人注册
        密码次数验证
        待还与查询
        资源操作记录
        集成Scala支持与Java混合开发 
        
        
        
 *       使用的技术

       
        主体框架：SpringBoot1.5.9
        模板引擎：Freemarker
        MVC框架：SpringMVC
        JDK:1.8必须
        Scala:2.11.8可选
        前端：Jquery、Jquery Validate、 hAdmin UI、bootstrap、BootStrap Table
        持久层框架：SpringData JPA
        权限控制框架：Shiro
        缓存：Ehcache、Spring cache、Redis
        数据源：Druid
        数据库：MySQL
        日志：logback
        JSON工具：google gson
        其他：Aspect
        开发工具：Eclipse
        依赖管理：maven
        代码托管：Github
        发布方式：Jar/docker
        应用服务器：内嵌式Tomcat8.5 
        其他：lombok
 
### 预览图
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF.png)
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E6%9F%A5%E8%AF%A2.png)
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E7%94%A8%E6%88%B7%E7%AE%A1%E7%90%86.png)
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E7%99%BB%E5%BD%95%E6%B3%A8%E5%86%8C.png)
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E5%AF%86%E7%A0%81%E9%99%90%E5%88%B6.png)
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/xmind%E5%9B%BE.png)
![](https://github.com/jxnu-liguobin/SpringBoot-Base-System/blob/master/base/images/%E6%93%8D%E4%BD%9C%E8%AE%B0%E5%BD%95.png)
 
### 使用：
    1）使用mysql数据库，先建立一个空数据库base，使用utf-8字符集。
    2）把application.properties中的数据库连接信息修改成自己数据库的连接信息。默认使用master主数据源
    3）resources下存放*.sql是使用Spring jdbc自动建表并初始化数据，默认放在resources/sql下
    【即不生效的，想用JPA也可以，具体看application.properties说明】
    4）启动后，访问：http://localhost:9999会自动跳转到后台登录页面。
    5）初始用户名和密码为：admin/adminm。可能是222222
    6）使用Jar打包发布，预览地址http://weixinsell.nat300.top
    7）在线测试信息
    账号：1526705033 密码：111111 角色：test 用户姓名：test
    拥有所有权限【禁用了资源修改与删除，希望在线测试者不要修改本账号的密码。】
    注：因使用natapp映射，速度可能有点慢。
    
    
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
        增加删除角色用户的依赖限制
        增加Redis对删除图书的监控限制
        修复Redis缓存与Eacache缓存均开启时，造成Cacheable等等注解失效的BUG
        修复角色删除与禁用操作逻辑混乱问题
        使用定时任务+阻塞队列，定期清理过期的受到密码次数限制的用户
        使用ConcurrentHashMap替代队列，并记录登录时间戳来判断
        增加AOP刷新授权缓存
        增加系统登陆操作信息记录
        增加角色/用户增删改操作记录
        增加图书增删改操作记录
        修复在库存变更时没有更新当前可用库存的bug
        使用Scala重构部分非耦合代码代码
        Loading...
 
 
###     QAQ
        
        
        EhCache缓存不会过期失效、CacheEventListener失效
        但是CacheManagerEventListener正常,导致锁定用户不能自动恢复
        考虑重写密码次数限制逻辑
