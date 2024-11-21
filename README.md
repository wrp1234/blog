# blog
基于jdk21、springboot 、vue3 实现的个人博客系统，文章使用markdown格式。



## 技术栈

| 依赖库       | 版本    | 说明            |
| :----------- | ------- | --------------- |
| springboot   | 3.3.5   | web开发核心依赖 |
| postgresql   | 16      | 关系型数据库    |
| mybatis-plus | 3.5.9   | ORM映射         |
| lombok       | 1.18.34 | 简化Bean        |
| jjwt         | 0.12.6  | jwt生成token    |
| redis        | 7.4.0   | 缓存            |
|              |         |                 |
|              |         |                 |
|              |         |                 |



## 更新日志

### 20241121

- 整合redis，实现缓存
- 开发验证码模块，获取验证码，校验验证码
- 用户登录时需要校验验证码

### 20241120

- 文件上传、文件下载
- 文件支持JPG、PNG，采用文件开头几位字节判断文件类型，单个文件1MB

### 20241119

- 创建项目
- 整合mybatis-plus，设置逻辑删除字段deleted、设置分页插件、设置自动填充配置
- 创建实体类基类

- 开发用户模块：注册、账号密码登录、个人详情
- 封装统一接口响应类
- jwt生成token，权限校验

待处理

- 手机号登录
- Bearer eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MzQ2MjMzMjQsInN1YiI6IntcImlkXCI6XCIxODU4ODg3MDE2MTU2NDQyNjI1XCIsXCJ1c2VybmFtZVwiOlwid3JwXCIsXCJ0b2tlblwiOm51bGx9In0.EJ8mhzI_bC2NlXxNnQEtHIGacHvmIkPXIKx_XnoaQP0 ，稍微改一下signature，依旧能通过token验证，感觉是bug
