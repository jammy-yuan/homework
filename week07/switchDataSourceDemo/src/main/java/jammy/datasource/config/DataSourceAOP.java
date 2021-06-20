package jammy.datasource.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAOP {
    @Pointcut("@annotation(jammy.datasource.config.Slave)")
    public void readPointcut() {
    }

    @Pointcut("@annotation(jammy.datasource.config.Master)")
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void readAdvise() {
        DynamicSwitchDBTypeUtil.slave();
    }

    @Before("writePointcut()")
    public void writeAdvise() {
        DynamicSwitchDBTypeUtil.master();
    }
}
