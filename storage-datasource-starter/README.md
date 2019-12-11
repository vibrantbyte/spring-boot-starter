#基本用法
1、注解：
```java
    //事务管理，优先级最高，如果使用了多个注解，@DataSourceGroup，@DataSourceSelector注解会失效。
    @Transactional(transactionManager = "txManager_dealer_order_en",rollbackFor = Exception.class)
    //类注解，非事务模式下使用，优先级低于 @DataSourceSelector，使用了@DataSourceSelector，则@DataSourceGroup会失效。
    @DataSourceGroup(name = "dealer_order_en")
    //方法注解，非事务模式下使用，优先级高于@DataSourceGroup
    @DataSourceSelector(name = "dealer_order_en")
```
2、注意
```java
txManager_ 为事务的前缀需要增加。
name 默认为 default

```