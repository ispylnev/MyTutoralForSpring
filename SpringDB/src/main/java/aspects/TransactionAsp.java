package aspects;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
@Aspect
public class TransactionAsp {

    @Pointcut("execution(* dao.implm.SQLiteDAO.*(..))")
    private void allMethod(){

    }

    @Before("allMethod()&&@annotation(annatations.ShowTransAlive)")
    public void showStatTransaction(){
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());

    }
}
