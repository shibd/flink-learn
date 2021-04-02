Flink 遵循 Dataflow 模型的理念: 批处理是流处理的特例。下面分别提供批处理和流处理的两个案例进行说明。

## 批处理

BatchJob实现了一个简单的批处理案例。从文件读取有限的数据后到内存中，将每一行进行分组统计。

具体的代码解释可以看代码中的注释。

## 流处理

flink的状态分为算子状态与键控状态。这里以算子状态做演示，算子状态与键控状态的主要区别为算子状态是对于算子而言，而键控状态是针对消息的key而言。具体的相关文档可查阅[有状态流处理](https://ci.apache.org/projects/flink/flink-docs-release-1.12/zh/concepts/stateful-stream-processing.html)

具体的代码解释可以看代码StreamingJob中的注释。

StreamingJob是以kafka为消息源，不断地从kafka中拉取消息。并且配置了算子状态、重启策略、checkpoint与状态恢复的功能。

你可以使用kafka tool或命令行往kafka中的`topic001`中发送消息。这里建议配置topic的分区数为1用于测试，当然你也可以指定kakfa消费的分区号或者offset。kafka消费者相关api可以查看FlinkKafkaConsumer011

具体的代码解释可以看代码中的注释。