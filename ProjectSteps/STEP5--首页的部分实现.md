### 关于本项目：

这个项目的原型是一个servlet + jsp 的在线图书购物系统，但是由于jsp技术比较过时了，所以我决定这个项目使用servlet 来做后端，前端使用bootstrap + vue.js，这三个技术都是第一次接触，还是有一定难度的

调了几天的bug，终于把请求数据部分搞定了，现在还差一个渲染数据，首页就差不多完成了。大致记录一下我遇到的错误。

##### ERROR 1：项目部署后首页请求servlet，找不到请求文件。

最开始是以为ajax请求有什么问题，然后转用a 链接进行请求servlet文件，仍然找不到。

查看web.xml中的配置，url-pattern部分配置的路径是/pagebooks。请求的路径是/pagebooks，弄了很久，发现  *1，*要把请求路径改为 ./pagebooks才能访问到。也就是index.html/pagebooks...第一次用还是真的不太懂  2，每次进行更改servlet后要重新部署，而不是UpdateResources，这样servlet无法被更新！！

##### ERROR 2: 可以访问servlet，但是状态码500

查了一下这个状态码，是服务器中有异常导致的。这里说一下我犯的几个错误导致了异常，主要还是对java编程不太熟悉导致：

###### 1，数据转json问题：

我使用的是fastJson库来操作json, 然后状态码500报 NoClassDefException ，我想奇了怪了，我明明有导入jar包啊，后来网上翻了半天，原来Tomcat 中的lib目录下也要导入该jar，否则tomcat无法识别lib中没有的类。

###### 2，编码问题：

使用

```java
resp.setContentType("text/html;charset=UTF-8");
resp.setCharacterEncoding("UTF-8");
```

来设置返回给浏览器的编码为utf-8，就是在返回的HTTP报文中设置header，告诉浏览器返回的编码。

### 技术难点：

项目文件的部署位置：在web.xml的 servlet url-pattern中配置了访问路径，注意自己文件所在的路径