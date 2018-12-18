package logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@Aspect
public class MyLogger {
    @Pointcut ("execution(* *(..)) && within(Object.*)&&execution(java.util.Map *(..)))")
    private void allMethod(){

    }
    @Around("allMethod()&&@annotation(annotation.ShowTime)")
    public Object watchTime(ProceedingJoinPoint joinpoint) {
        long start = System.currentTimeMillis();
        System.out.println("method begin: " + joinpoint.getSignature().toShortString());
        Object output = null;
        for(Object object : joinpoint.getArgs()){
            System.out.println(object);
        }
        try {
            output = joinpoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms");

        return output;
    }
    @AfterReturning(pointcut = "allMethod()&&@annotation(annotation.ShowResult)&&execution()", returning = "object")
    public void print(Object object){
        if(object instanceof Set){
            Set set = (Set) object;
            for (Object obj : set){
                System.out.println(object);
            }
        }
        else if (object instanceof Map){
            Map map = (Map) object;
            for (Object obj : map.keySet()){
                System.out.println("key " + obj + "," + map.get(obj));
                System.out.println();
            }

        }

    }
}
