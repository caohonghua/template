# template

Simple java background template.  
Using Spring Boot + Mybatis + MySQL + Swagger3

### spring-devtools 开发工具
* spring.devtools.add-properties = true 开启开发模式
* spring.devtools.restart.enabled = true 开启重启功能

### swagger-ui访问地址
* bootstrap地址： http://{host}:{port}/{basePath}/doc.html

### page-helper 分页插件
```
Page<xxx> page = PageHelper.start(pageNum,pageSize)
    .doSelectPage(()-> xxxMapper.findByPage());
```
### 跨域配置
* 通过在application.yml中配置custom.cors， 配置跨域信息