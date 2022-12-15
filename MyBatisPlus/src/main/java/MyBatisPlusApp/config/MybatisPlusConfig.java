package MyBatisPlusApp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//当一个类加了@configuration注解那么表示这个炎就是一个配置类
// 配置类 在项目启动的时候优先加载配置
@Configuration
public class MybatisPlusConfig {
    //添加分页拦截器
    // @Bean注解 只要方法加了这个注那么这个方法的返回值就会被封装到Spring容器中
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        //1.创建MybatisPlus的拦截器对象MybatisPlusInterceptor
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //2.把分页插件添加到拦截器对象中
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //3.添加乐观锁的拦截器
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        return mybatisPlusInterceptor;
    }

}
