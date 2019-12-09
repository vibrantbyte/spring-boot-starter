执行命令：
1、编译
```shell script
# *** 为你的密码
mvn -s "/Users/xiaoyueya/.m2/settings.xml" clean deploy -P sonatype-oss-release -Darguments="gpg.passphrase=***"
```

2、发送
```shell script
gpg --keyserver hkp://keyserver.ubuntu.com:11371 --send-keys BD4648800B300AF5EF52F67010A8E971603E528E
```

3、登录设置
https://oss.sonatype.org/#stagingRepositories
