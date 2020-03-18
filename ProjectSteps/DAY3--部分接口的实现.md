#### 今天所犯的小错误：

当sql语句中还有**?**时，未设置的参数不会导致程序爆错，但是参数会导致运行不出来正确结果。所以当未报错且结果不对时，就应该考虑是否是**sql语句中的参数未设置**导致的。

### Note

在进行更新时使用了数据库事务机制

因为更新主要分为两个部分：1，**删除原来的记录** 2，**将新纪录添加至原来记录的位置，id不变**

这两个明显应该是一个事务，因为他们是不可分割的，否则可能会发生记录仅仅删除没有插入新纪录的情况。

#### jdbc事务的使用：

1，设置连接不是自动提交：this.conn.setAutoCommit(false);

2, 设置Savepoint, 也可以不设置Savepoint，进行一次性提交，提交完毕直接rollback()

3,执行sq l语句但不提交：Preparement对象stmt.excuteUpdate();

4,提交：this.conn.commit();

5,事务回滚，将提交单独用一个try...catch语句包裹，在catch中进行回滚，回滚至Savepoint:  conn.rollback(Savepoint savepoint)

### 技术难点：

难点主要在事务回滚的使用上，怎样去设置断点、回滚才合理。