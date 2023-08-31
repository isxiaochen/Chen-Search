# Chen-Search
> 基于 Spring Boot + Elastic Stack+Vue的一站式信息聚合搜索平台。用户可在同一页面集中搜索出不同来源、不同类型的内容，如接口，文章，图片，用户等数据
## 项目展示

文章搜索

![文章搜索](https://github.com/isxiaochen/Chen-Search/blob/master/image/image-20230816151026106.png)



图片搜索

![图片搜索1](https://github.com/isxiaochen/Chen-Search/blob/master/image/image-20230816151456344.png)

![图片搜索2](https://github.com/isxiaochen/Chen-Search/blob/master/image/image-20230816152027607.png)

用户搜索

![用户搜索](https://github.com/isxiaochen/Chen-Search/blob/master/image/image-20230816153006489.png)

## 项目背景

本人做过多个系统发现搜索业务几乎是每个系统都需要的一个功能，并且自己平时开发一直有在用不同的搜索引擎，比如百度搜索，必应搜索等，一直也好奇他们是如何实现这么厉害的搜索功能的。怀揣着这样的好奇心，并且自己的业务也有需求，遂萌生了开发此系统的想法



## 系统架构

![系统架构](https://github.com/isxiaochen/Chen-Search/blob/master/image/image-20230802130136320.png)

## 技术栈

- 开发框架：Vue
- 组件库：Ant Design、Ant Design Components
- 语法扩展：TypeScript、Less
- 打包工具：Webpack
- 代码规范：ESLint、Prettier



- 主语言：Java
- 框架：SpringBoot 2.7.0、Mybatis-plus、ElasticSearch
- 数据库：Mysql8.0
- 数据同步：Logstash

## 功能模块

> 🌟 亮点功能 🚀 未来计划

- 制定数据源接入规范，实现多数据源的聚合搜索
- MySQL和ES的数据同步

## 快速上手

### 后端

1. 本地安装ES并开启ES服务
2. 安装Maven依赖
3. 启动服务



### 前端

环境要求：Node.js >= 16

安装依赖：

```
yarn
```

启动：

```
npm run start:dev
```



## 欢迎贡献

项目需要大家的支持，期待更多小伙伴的贡献，你可以：

- 对于项目中的Bug和建议，能够在Issues区提出建议，我会积极响应
