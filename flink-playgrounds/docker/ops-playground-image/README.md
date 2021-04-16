# 介绍

来自官方demo: https://github.com/apache/flink-playgrounds.git
官方演示流程: https://ci.apache.org/projects/flink/flink-docs-release-1.11/

创建该工程的issue,在测试过程中有修改demo的说明: 
issue: https://gitlab.iquantex.com/kunlun/phoenix/phoenix/-/issues/1023
wiki: https://gitlab.iquantex.com/kunlun/phoenix/phoenix/-/wikis/flink/Flink%E8%BF%90%E8%A1%8C%E6%97%B6%E6%9E%B6%E6%9E%84%E7%A0%94%E7%A9%B6-%231023

# Flink Operations Playground Image

The image defined by the `Dockerfile` in this folder is required by the Flink operations playground.

The image is based on the official Flink image and adds a demo Flink job (Click Event Count) and a corresponding data generator. The code of the application is located in the `./java/flink-ops-playground` folder.