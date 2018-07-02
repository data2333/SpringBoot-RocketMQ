package demo.springboot.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sun on 18-7-1.
 */
@Component
@Aspect
public class ClearPermissions {

    @Pointcut("execution(public * com.wqh.blog.controller.*.*(..))")
    public void nowPoint(){

    }

    @After("nowPoint()")
    public void getTestAspect(JoinPoint pjp){
        MyShiroRealm realm=new MyShiroRealm();
        List<Integer> list=(List)pjp.getArgs()[0];
        realm.clearUserAuthByUserId(list);
    }
}
