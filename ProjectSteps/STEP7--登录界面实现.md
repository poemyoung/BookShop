### 实现了登录界面，还未完成登录后的跳转：

前端一样的使用bootstrap+vueJs快速构建，一个表单提交信息，在vue中使用el.prventDefault阻止表单提交，并且使用jQuery的ajax（本来想用vue的，想想也差不多，不用绑定数据了）进行请求，使用serialize()方法将表单序列化成name=value的形式提交给servlet,然后得到servlet的返回结果（success or fail)

### cookie

优化了一下使用了cookie的方式，省去了每次登录。在登录成功后用Cookie cookie = new Cookie("name","value"）；response.addCookie(cookie)的方法给前端返回一个cookie，作为登录凭证，在有效期内可以免登录。

### 技术难点：

1，在后端传过来记住密码的参数时，根据参数值判断是否记住密码，为null（代表没有该参数）则不记住密码

