# 校吧后端
## 接口
### LoginController:

获得验证码图片
用法：http://127.0.0.1:8080/captcha.jpg?uuid=111
```java
 
    /**
     * 获得验证码图片
     * @param request
     * @param response
     * @param uuid 生成 验证码的 id
     * @throws IOException
     */
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletRequest request,HttpServletResponse response, String uuid) 
```


登录：
用法： http://localhost:8080/login?userName=zhouning&&userPassword=123456&&captcha=f677n&&uuid=111
```java
  /**
      *
      * @param form 登录的表单
      * @return
      */
     @GetMapping("/login")
     @ResponseBody
     public Map<String,Object> login(SysLoginForm form)

//表单
public class SysLoginForm {
    private String userName;
    private String userPassword;
    private String captcha;
    private String uuid;
}
```

查询用户信息：
用法： http://localhost:8080/user?token=d25565839cc23e344eaf747554b254f4
```java
   /**
     * 查询用户信息
     * @param token
     * @return
     */
    @GetMapping("/user")
    @ResponseBody
    public SysUser getInfo(@RequestParam("token")  String token)
```

### RegisterController

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
### FileController
上传文件
```java
    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String upload (@RequestParam("file") MultipartFile file)
```

存储文件
```java
    /**
     * 传送 md 内容给服务器，存为md文件
     * @param content
     * @param title
     * @param essayAbstract
     * @param author
     * @return
     */
    @RequestMapping("/md")
    @ResponseBody
    public String saveMd(String content,String title,String essayAbstract,String author)
```
### SearchController
搜索
例子：http://localhost:8080/search/c++/0
```java
 @ResponseBody
    @GetMapping("/search/{keyword}/{currentPage}")
    public Map<String,Object> search(@PathVariable("keyword")String keyword,
                                     @PathVariable("currentPage")int currentPage) throws IOException {
        return elasticSearchService.search(keyword,currentPage);
    }
```