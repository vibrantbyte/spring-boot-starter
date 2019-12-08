# spring-boot-starter
spring boot project


 * 控制serviceConfigInfo中的spring.config.location属性，再存在的情况下加载，没有的情况下不会报错
 * @EnableConfigurationProperties(ServiceConfigInfo.class)
 * @ConditionalOnProperty("spring.config.location")
 *
 * 控制RefreshResolver配置后顺利返回RefreshResolver的实例，并且后置到Configuration加载完成之后。
 * @AutoConfigureAfter(RefreshResolver.class)
 * @Import(RefreshResolver.class)
 *
 * 配套使用，加载配置文件，实例化ContextRefresher而不是其他实现。（多实现的条件下）
 * @Configuration
 * @ConditionalOnClass(ContextRefresher.class)