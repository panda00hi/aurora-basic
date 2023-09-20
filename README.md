# aurora基础组件

## 版本号输出 aurora-version

配合python脚本，方便确认现有项目的版本。另外，支持将组件版本信息打metric，方便监控系统集成展示。

### 使用

- 项目的resources目录下新建version.txt文本文件
- pom.xml引入version-starter依赖
- 验证请求curl ip:port/dev/v1/version

version.txt示例

```
${__rootArtifactId__}_1.0.1_20230101_B1998_c9c6e68
```

usage示例

```xml

<dependency>
    <groupId>com.aurora</groupId>
    <artifactId>version-stater</artifactId>
    <version>1.0.0</version>
</dependency>
```

