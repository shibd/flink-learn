> 该工程提供两个model,一个是从官方工程中提取出来的greet demo,另一个是模仿greet创建的一个account demo,实现了基础的划拨操作

# 工程结构

```shell
account
|-- AccountIO.java                  // 定义account的输入输出,序列化和反序列化
|-- AccountModule.java              // account程序入口,组合io,router,Function等配置
|-- AccountRouter.java              // 存放路由信息
|-- AccountStatefulFunction.java    // 这里存放account的处理逻辑
`-- protobuf                        // message类
    |-- Account.java
    |-- AccountRequest.java
    |-- AccountRequestOrBuilder.java
    |-- AccountResponse.java
    `-- AccountResponseOrBuilder.java

resources/MATA-INF.services/org.apache.flink.statefun.sdk.spi.StatefulFunctionModule        // 定义程序入口
```

## 运行实例

构建工程

```
docker-compose build
docker-compose up
```

系统入口为`names` topic,出口为`greetings`topic:

```
docker-compose exec kafka-broker kafka-console-producer.sh \
     --broker-list localhost:9092 \
     --topic names
```

```
docker-compose exec kafka-broker kafka-console-consumer.sh \
     --bootstrap-server localhost:9092 \
     --isolation-level read_committed \
     --from-beginning \
     --topic greetings
```
