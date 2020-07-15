# 分类
- chapter01-2
	- SpringBoot 环境搭建
	- SpringBoot 基础配置
	- SpringBoot 整合视图层技术
		- 整合 Thymeleaf
		- 整合 FreeMarker
	- SpringBoot 整合 Web 技术
		- JSON 数据返回
		- 静态资源访问
		- 文件上传
		- 异常处理
		- 自定义错误页
		- CORS 支持
		- 配置类和 XML 配置
		- 拦截器注册
		- 系统任务
		- 整合 Servlet、Filter、Listener
		- 路径映射
		- 配置 AOP
	- SpringBoot 整合持久层
		- 整合 JdbcTemplate
		- 整合 MyBatis
		- 整合 Spring Data JPA
	- SpringBoot 整合 NoSQL
		- 整合 Redis
		- 整合 MongoDB
		- Session 共享

- chapter02-1
	- SpringBoot 构建 RESTful 服务 —— JPA

- chapter02-2
	- SpringBoot 构建 RESTful 服务 —— MongoDB
	
	
# SpringBoot 学习笔记

> 声明：
> 
> 本次学习参考 《SpringBoot + Vue 开发实战》 · 王松（著） 一书。
> 
> 本文的目的是为了记录我在学习的过程和遇到的一些问题以及解决办法。
> 
> 如有侵权，请联系我删除。

## SpringBoot 环境手动搭建

### Maven 工程创建

- `mvn` 命令创建 
- `IntelliJ IDEA` 创建
	- 创建项目。（选择 `Maven`， 点击 `Next`。）
	- 输入组织名称、模块名称、项目版本号等。
	- 选择项目位置，点击 `Finish` 按钮，完成项目创建。
	- 添加 `SpringBoot` 依赖。
		```xml
		<parent>
			<groupId> org.springframework.boot </groupId>
			<artifactId> spring-boot-starter-parent </artifactId>
			<version> 2.0.4.RELEASE </version>
		</parent>
		```

		> `spring-boot-starter-parent` 是一个特殊的 starter，提供一些 `Maven` 的默认配置。
	- 编写启动类
		
		在 `Maven` 工程的 `java` 目录下创建项目的包，包里创建一个 `App` 类
		```java
		@EnableAutoConfiguration
		public class App {
			public static void main(String[] args){
				SpringApplication.run(App.class, args);
			}
		}
		```
		> `@EnableAutoConfiguration` 注解表示开启自动化配置。由于项目添加了 `spring-boot-starter-web` 依赖，因此在开启了自动化配置之后会自动进行 `Spring` 和 `Spring MVC` 的配置

		> `main` 方法中， `SpringApplication` 中的 `run` 方法启动项目。第一个参数传入 `App.class`， 告诉 `Spring` 哪个是主要组件。第二个参数是运行时输入的其他参数
	- 编写控制器测试

		```java
		@RestController
		public class HelloController {
			@GetMapping("/hello")
			public String hello() {
				return "hello spring boot!";
			}
		}
		```
		> 控制器中提供 `/hello` 接口，此时需要配置包扫描才能将 `HelloController` 注册到 `SpringMVC` 容器中，因此需要在 `App` 类上面再添加一个注解 `@ComponentScan` 进行包扫描。

		```java
		@EnableAutoConfiguration
		@ComponentScan
		public class App {
			public static void main(String[] args) {
				SpringApplication.run(App.class, args);
			}
		}
		```
		> 也可以使用组合注解 `@SpringBootApplication` 来替代。
		
		```java
		@SpringBootApplication
		public class App {
			public static void main(String[] args) {
				SpringApplication.run(App.class, args);
			}
		}
		```
	- 项目启动
		- `maven` 命令启动
			```
			mvn spring-boot:run
			```
		- 运行 `main` 方法
		- `maven` 打包启动
			- 添加配置 `pom.xml`
				```
				<build>
					<plugins>
						<plugin>
							<groupId>org.springframework.boot</groupId>
							<artifactId>spring-boot-maven-plugin</artifactId>
						</plugin>
					</plugins>
				</build>
				```
			- 运行 `mvn package` 命令


## SpringBoot 环境自动搭建

> 上述是整个搭建 `SpringBoot` 开发环境的步骤，实际开发中，我们很少做这些，因为，我们可以借助一些工具自动搭建并配置 `SpringBoot` 环境
- 方法一：`SpringBoot` 官网在线创建： https://start.spring.io
- 方法二：`IntelliJIDEA` 创建
	- 新建（选择 `Spring Initializr`)
		![新建项目_步骤1](C:\Users\F1680502\Desktop\springboot\新建项目_步骤1.png)
	- 输入基本信息
	 	![新建项目_步骤2](C:\Users\F1680502\Desktop\springboot\新建项目_步骤2.png)
	- 选择依赖
		![新建项目_步骤3](C:\Users\F1680502\Desktop\springboot\新建项目_步骤3.png)
	- 选择项目路径
		![新建项目_步骤4](C:\Users\F1680502\Desktop\springboot\新建项目_步骤4.png)

## SpringBoot 基础配置

### `spring-boot-starter-parent`
	
主要提供了如下默认配置：
- Java 版本默认 1.8
- 编码格式默认 UTF-8
- 提供 `Dependency Management` 进行项目依赖的版本管理
- 默认的资源过滤与插件配置

### `@SpringBootApplication` 注解

是一个组合注解。由三个注解组成：
- `@SpringBootConfiguration`
	
	实际上就是一个 `@Configration` 注解，表明这是一个配置类，开发者可以在这个类中配置 `Bean`。该类扮演的角色有点类似于 `Spring` 中的 `applicationContext.xml` 。

- `@EnableAutoConfiguration` 

	表示开启自动化配置。 `SpringBoot` 中的自动化配置是非侵入式的，在任意时刻，开发者都可以使用自定义配置替代自动化配置中的某一个配置。

- `@ComponentScan`
	
	完成包扫描，也是 `Spring` 中的功能。由于 `@ComponentScan` 注解默认扫描的类都位于当前类所在包下，因此建议将启动项目类放在根目录中。

> 虽然项目启动类中包含 `@Configuration` 注解（`@SpringBootConfiguration`），但是开发者可以创建一个自定义的类，专门用来配置 `Bean` ， 这样便于配置的管理，只需要加上 `@Configuration` 注解即可。

```java
@Configuration
public class MyConfig(){}
```

> 项目启动类中的 `@ComponentScan` 注解，除了扫描 `@Service`、`@Repository`、`@Component`、`@Controller` 和 `@RestController` 等之外，也会扫描 `@Configuration` 注解的类


### 自定义 banner

> `SpringBoot` 项目启动时会打印一个标识。可以在 `resource` 目录下通过创建一个 `banner.txt` 文件来自定义。

参考艺术字网站：

- http://www.network-science.de/ascii

### Web 容器配置

#### TomCat 配置

> 在 `Spring Boot` 配置中，可以内置 `Tomcat`、`Jetty`、`Undertow`、`Netty`等容器。
>
> 当添加了 `spring-boot-starter-web` 依赖之后，默认会使用 `Tomcat` 作为 `Web` 容器。
> 
> 如果需要对 `Tomcat` 做进一步配置，可以在 `application.properties` 中进行配置：

```xml
server.port=8081
server.error.path=/error
server.servlet.session.timeout=30m
server.servlet.context-path=/chapter02
server.tomcat.uri-encoding=utf-8
server.tomcat.max-threads=500
server.tomcat.basedir=/home/sang/tmp
```

- server.port
	
	Web 容器的端口号

- server.error.path

	项目出错时跳转的页面

- server.servlet.session.timeout

	session 失效时间。默认单位（秒：s），但由于 Tomcat 以（分钟：s）为单位，因此，如果设置为秒，则会被转换为一个小于等于该秒数的分钟。
- server.servlet.context-path

	项目名称。默认是/，如果配置了，就需要在访问路径上加上配置的路径

- server.tomcat.uri-encoding

	Tomcat 请求编码

- server.tomcat.max-threads

	Tomcat 最大线程数

- server.tomcat.basedir

	存放 Tomcat 运行日志和临时文件的目录。若不配置，则默认使用系统临时目录

#### HTTPS 配置

> 由于 HTTPS 良好的安全性，在开发中得到了越来越广泛的应用。

> 在 jdk 中提供了一个 Java 数字证书管理工具 keytool， 在 `/jdk/bin` 目录下，通过这个工具可以自己生成一个数字证书，命令如下：

```
keytool -genkey -alias tomcathttps -keyalg RSA -keysize 2048 -keystore sang.p12 -validity 365
```

- -genkey

	创建新密钥

- -alias

	keystore 的别名

- -keyalg

	使用加密算法 RSA， 一种非堆成加密算法

- -keysize

	密钥长度

- -keystore

	生成密钥存放位置

- -validity

	密钥的有效时间，单位：天

> 在 `cmd` 窗口中直接执行上述命令，生成密钥，将生成的名为 `sang.p12` 的文件复制到项目的根目录下，然后在 `application.properties` 中做如下配置：

```xml
server.ssl.key-store=sang.p12
server.ssl.key-alias=tomcathttps
server.ssl.key-store-password=123456
```

- key-store

	表示密钥文件名

- key-alias 

	表示密钥别名

- key-store-password

	`cmd` 命令执行过程中设定的密码

> 配置成功后，启动项目，输入 `https://localhost:8081/chapter02/hello` 来访问。由于证书是自己生成的不被浏览器认可，需要手动添加信任即可。
> 
> 此时，如果再以 `http://localhost:8081/chapter02/hello` 访问，则会访问失败 `This combination of host and port requires TLS`.

因为 `SpringBoot` 不支持同时在配置中启动 HTTP 和 HTTPS。这个时候可以配置请求重定向，将 HTTP 请求重定向为 HTTPS 请求。配置如下：

```java
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.catalina.Context;

@Configuration
public class TomcatConfig {
    @Bean
    TomcatServletWebServerFactory tomcatServletWebServerFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory(){

            @Override
            protected  void postProcessContext(Context context){
                SecurityConstraint constraint  = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        factory.addAdditionalTomcatConnectors(createTomcatConnector());
        return  factory;
    }

    /**
     * 监听 http://localhost:8081/ 端口，重定向到 https://localhost:8080/
     * @return
     */
    private Connector createTomcatConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8081);
        connector.setSecure(false);
        connector.setRedirectPort(8080);
        return connector;
    }
}
```

#### Properties 配置

> SpringBoot 中采用了大量的自动化配置，但也不可避免会有一些需要手动配置。

`application.properties` 配置文件一共可以出现在 4 个位置，加载优先级从依次降低：
- 项目根目录下 config 文件夹中
- 项目根目录下
- classpath 下的 config 文件夹中
- classpath 下

#### 类型安全配置属性

> Spring 提供了 `@Value` 注解以及 `EnvironmentAware` 接口来将 `Spring Environment` 中的数据注入到属性上，`SpringBoot` 对此进一步提出了类型安全配置属性，这样即使在数据量十分大的情况下，也可以更加方便的将配置文件中的数据注入 `Bean` 中

可以在 `application.properties` 中添加一段配置

```
book.name=SpringBoot开发实战
book.author=王松
book.price=65
```

将这一段配置数据注入 `Bean` 中

```java
package org.sang.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "book")
public class Book {
    private String name;
    private String author;
    private Float price;

    /* getter & setter */
}

``` 

如果报错
```
spring boot Configuration Annotation Proessor not found in classpath
```

可以在 `pom.xml` 中加入如下代码后重新 build 
```xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-configuration-processor</artifactId>
 <optional>true</optional>
</dependency>
```

最后，创建一个 `BookController` 进行测试：

```java
import org.sang.Bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    Book book;

    @GetMapping("/book")
    public String book(){
        return book.toString();
    }
}
```

启动项目，访问 `https://localhost:8080/book`

> Warning:  可能会发现，汉字，乱码了？？？

> 可以在 `IDEA` 的 setting --> file encoding 中，这是 global encoding 和 project encoding 为 `UTF-8`，default encoding for properties files 也为 `UTF-8`，并勾选 Transparent native-to-ascii conversion 即可。 


至此，`SpringBoot` 环境搭建和基础配置就完成了，接下来，就可以使用 `SpringBoot` 环境整合一些其他技术进行开发了。