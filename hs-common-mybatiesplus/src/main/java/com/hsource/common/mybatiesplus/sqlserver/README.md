# mybatis-plus解决 sqlserver批量插入list报错解决方案

##### 第一步: 建立NoahSqlMethod(也可以不写,但是项目尽量不出现魔法值)
##### 第二步: 建立InsertBatch对象
##### 第三步: 建立NoahSqlInjector对象
##### 第四步: 重写ServiceImpl超类为AbstractNoahServiceImpl
##### 第五步: 将业务service继承类改为AbstractNoahServiceImpl
##### 第六步: 将SqlInjector注入系统中
