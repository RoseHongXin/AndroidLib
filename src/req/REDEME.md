- 关于网络请求的一些基类和结构代码；
- 主要是基于Retrofit 2.0网络框架和Jackson数据格式转换框架，还借助了dagger 2框架做一些简单的依赖注入；
---
```
  ModuleReq请求的一些基本配置；
  DReq包内部类，等待toast展示；
  ReqParams基于Retrofit 2.0请求数据转换的帮助类；
  RetrofitBase 虚类，请求方式的定义，如果要使用这个包，必须继承这个类；
  RetrofitBase2 不同于RetrofitBase，自定义dialog做waiting显示，基于DialogPlus框架；
  bean类的定义，比如分页；
  
  ```
        -- 同时，需要在app的入口，也就是Application这个地方，准备好Dagger2的注入，设置ModuleReq和ModuleApp；
        -- 如果需要在请求里面放置header，比如token，同样需要在app入口，设置SpUtil初始化；
        -- 请求数据本地缓存，具体到请求借口实现代码里面，做逻辑处理，也需要CacheUtil初始化；
  ```
```