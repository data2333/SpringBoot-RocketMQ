package demo.springboot.web;

import demo.springboot.service.ArticleService;
import demo.springboot.service.TestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Book 控制层
 * <p>
 * Created by bysocket on 27/09/2017.
 */
@RestController
@EnableCaching
@RequestMapping("/test")
public class BookController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ArticleService articleService;

    //    @Autowired
//    BookService bookService;
//
//
//    /**
//     * 获取 Book 列表
//     * 处理 "/book" 的 GET 请求，用来获取 Book 列表
//     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public void getBookList() {
        articleService.getAllArticles();
    }

    @Resource
    private TestService service;

    @RequestMapping("/get")
    public String query() {
        return "[" + getDateNow() + "]" + service.query(1);
    }

    private static String getDateNow() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
    @RequestMapping("/login")
    private String TestShiro(@RequestParam("userName") String userName, @RequestParam("password") String password){
        logger.info("==========" + userName + password + true);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(true);
//        subject.isPermitted("");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
//            rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
            return "{\"Msg\":\"您的账号或密码输入错误\",\"state\":\"failed\"}";
        }
        return "{\"Msg\":\"登陆成功\",\"state\":\"success\"}";
    }
//
//    /**
//     * 获取 Book
//     * 处理 "/book/{id}" 的 GET 请求，用来获取 Book 信息
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Book getBook(@PathVariable Long id) {
//        return bookService.findById(id);
//    }
//
//    /**
//     * 创建 Book
//     * 处理 "/book/create" 的 POST 请求，用来新建 Book 信息
//     * 通过 @RequestBody 绑定实体参数，也通过 @RequestParam 传递参数
//     */
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public Book postBook(@RequestBody Book book) {
//        return bookService.insertByBook(book);
//    }
//
//    /**
//     * 更新 Book
//     * 处理 "/update" 的 PUT 请求，用来更新 Book 信息
//     */
//    @RequestMapping(value = "/update", method = RequestMethod.PUT)
//    public Book putBook(@RequestBody Book book) {
//        return bookService.update(book);
//    }
//
//    /**
//     * 删除 Book
//     * 处理 "/book/{id}" 的 GET 请求，用来删除 Book 信息
//     */
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//    public Book deleteBook(@PathVariable Long id) {
//        return bookService.delete(id);
//    }
//
}
