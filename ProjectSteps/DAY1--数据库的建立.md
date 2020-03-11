### 建立在线图书购物商城的数据库bookshop

#### tables如下：

###### 1,users，用户表

+----------+-------------+------+-----+---------+-------+

| Field  | Type    | Null | Key | Default | Extra |

+----------+-------------+------+-----+---------+-------+

| id    | int     | NO  | PRI | NULL  |    |

| name   | varchar(10) | NO  |   | NULL  |    |

| password | varchar(20) | YES |   | NULL  |    |

| phone  | varchar(20) | YES |   | NULL  |    |

+----------+-------------+------+-----+---------+-------+

##### 2，books,图书表

+-------+--------------+------+-----+---------+-------+

| Field | Type     | Null | Key | Default | Extra |

+-------+--------------+------+-----+---------+-------+

| id  | int     | NO  | PRI | NULL  |    |

| name | varchar(10) | NO  |   | NULL  |    |

| price | varchar(3)  | YES |   | NULL  |    |

| image | varchar(200) | NO  |   | NULL  |    |

| stock | varchar(200) | NO  |   | NULL  |    |

+-------+--------------+------+-----+---------+-------+

###### 3, address, 收获地址表

+----------+-------------+------+-----+---------+-------+

| Field  | Type    | Null | Key | Default | Extra |

+----------+-------------+------+-----+---------+-------+

| id    | int     | NO  | PRI | NULL  |    |

| province | varchar(10) | NO  |   | NULL  |    |

| city   | varchar(15) | NO  |   | NULL  |    |

| county  | varchar(15) | NO  |   | NULL  |    |

| village | varchar(10) | NO  |   | NULL  |    |

| detail  | varchar(30) | YES |   | NULL  |    |

+----------+-------------+------+-----+---------+-------+

###### 4，orders订单表

+-------------+-------------+------+-----+---------+-------+

| Field    | Type    | Null | Key | Default | Extra |

+-------------+-------------+------+-----+---------+-------+

| id     | int     | NO  | PRI | NULL  |    |

| userid   | int     | NO  |   | NULL  |    |

| addrid   | int     | NO  |   | NULL  |    |

| bookid   | int     | NO  |   | NULL  |    |

| count    | int     | NO  |   | NULL  |    |

| price    | varchar(50) | YES |   | NULL  |    |

| state    | int     | YES |   | NULL  |    |

| total_price | varchar(50) | YES |   | NULL  |    |

+-------------+-------------+------+-----+---------+-------+

###### 5，items清单表

+-------------+-------------+------+-----+---------+-------+

| Field    | Type    | Null | Key | Default | Extra |

+-------------+-------------+------+-----+---------+-------+

| orderid   | int     | NO  |   | NULL  |    |

| bookid   | int     | NO  |   | NULL  |    |

| count    | int     | NO  |   | NULL  |    |

| price    | varchar(20) | YES |   | NULL  |    |



###### 一些今天刚学的操作：

Alter table [table] modify [column] +**完整的表结构**，比如 varchar(10) not null;

desc [table] 描述某个表，给出具体结构

id 一般设置为int ，AUTO_INCREMENT=[数值]

CHECK 约束：在建表时约束某个值，比如CHECK(id > 5)

###### 不足：

还未具体想好订单表会有哪些东西

未设置外键约束，因为命名不一样，不太好设置

### 技术难点：

设计数据库中表之间的约束关系，以及每个字段约束，比如说非空、还有取值范围这些