package org.sang.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AOP  切面
 */
@Component
// @Aspect 注解表明这是一个切面类
@Aspect
public class LogAspect {
    // @Pointcut 注解定义切入点定义
    // execution 中的第一个 * 表示方法返回任意值
    // execution 中的第二个 * 表示 org.sang.service 包下的任意类
    // execution 中的第三个 * 表示类中的任意方法
    //               括号中的两个点表示地方法参数任意。
    //  即，这里描述的切入点为 service 包下所有类中的所有方法
    @Pointcut("execution(* org.sang.service.*.*(..))")
    public void pc1(){
    }

    // @Before 表示这是一个前置通知，该方法在目标方法执行前执行。通过 JoinPoint 参数可以获取目标方法名、修饰符等
    @Before(value = "pc1()")
    public void before(JoinPoint jp){
        //这里是 org.aspectj.lang.JoinPoint
        //还有一个 org.aopalliance.intercept.Joinpoint
        //不要用错了~
        String name = jp.getSignature().getName();
        System.out.println(name + "方法开始执行...");
    }

    // @After 表示这是一个后置通知，该方法在目标方法执行后执行。
    @After(value = "pc1()")
    public void after(JoinPoint jp){
        String name = jp.getSignature().getName();
        System.out.println(name + "方法执行结束...");
    }

    // @AfterReturning 表示这是一个返回通知，在该方法中可以获取目标方法的返回值。 returning 参数指返回值的变量名
    @AfterReturning(value = "pc1()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result){
        String name = jp.getSignature().getName();
        System.out.println(name + "方法返回值为：" + result);
    }

    // @AfterThrowing 表示这是一个异常通知，即当目标方法发生异常时，该方法被调用。
    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e){
        String name = jp.getSignature().getName();
        System.out.println(name + "方法发生了异常：" + e.getMessage());
    }

    // @Around 表示这是一个环绕通知。环绕通知是所有通知中功能最强大的，可以实现所有通知的功能。
    // 目标方法进入环绕通知后，可以调用 ProceedingJoinPoint 对象的 proceed 方法使目标继续执行；
    // 开发者可以在这里修改目标方法的执行参数、返回值等，并可以在此处理目标方法的异常。
    @Around("pc1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        return pjp.proceed();
    }
}
