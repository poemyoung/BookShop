### 今天实现了用户注销、用户在首页点击“我的”检查cookie并选择该跳转的页面的功能

##### 思路：

用户点击注销---->一个servlet删除所有cookie

用户在首页点击我的--->一个servlet负责检查所有的cookie，如果cookie中有phone属性，用户跳转至个人中心，否则用户跳转至登录界面

##### 技术难点：

呃..这个功能确实比较简单，暂时没有什么技术难点