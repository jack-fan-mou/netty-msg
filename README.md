#  一个简单rpc框架，基于netty<br>
## springboot 集成
## 使用protobuf编解码

###  protobuf 生成工具说明

1、下载工具地址 https://github.com/protocolbuffers/protobuf/releases/tag/v3.20.1<br>
2、使用jar版本最好与工具版本一致，否则生成的代码某些方法不支持，
<pre>
&lt;dependency>
    &lt;groupId>com.google.protobuf&lt;/groupId>
    &lt;artifactId>protobuf-java&lt;/artifactId>
    &lt;version>3.20.1&lt;/version>
&lt;/dependency>
</pre>
