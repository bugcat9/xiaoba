# 校吧后端
## 接口
LoginController:
登录：
```java
 /**
     * 登录
     * @param userName 用户名
     * @param userPassword  密码
     * @param session
     * @return json文件，其中有个token用户判断用户登录是否成功
     */
    @PostMapping("/login")
    public Map<String,Object> login(@RequestParam("userName") String userName,
                                    @RequestParam("userPassword") String userPassword,
                                    HttpSession session)
```

```java
    /**
     * 查询用户信息
     * @param token
     * @return
     */
 @GetMapping("/user")
    public SysUser getInfo(@RequestParam("token")  String token)
```

```java
    /**
     * 注册
     * @param userName 用户名
     * @param userPassword 密码
     * @return
     */
    @GetMapping("/register")
    public boolean register(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword)
```
