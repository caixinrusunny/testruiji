package MyBatisPlusApp.Mapper;

import MyBatisPlusApp.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

//依赖mybatis-spring-boot-starter----------->mybatis-plus-boot-starter
//1.使用Mybatisplus 我们的dao接口要继承一个BaseMapper 的接口
// 这个BaseMapper 接口需要传递一个泛型当前你这个dao接口要操作那一张表格 你就给这个BaseMapper接口的泛型传递哪一个表对应的实体类
// 这个到我们要操作user表格 所以在这里我们给BaseMapper传递的泛型是user表对应的实体类user
// 2.当我们继承了这个BaseMapper接口我们会发现这个BaseMapper给我们提供了大量的操作方法
// 当我们需要操作这个数据库表的时候 不需要自己再去实现他已经提供的方法了
// 直接去调用BaseMapper给我们提供的这些方法就可以实现CRUD操作了
// 如果我们需要的操作 他没有给我们提供这个方法 那么这个时候我们就可以根据他的规则自己去扩展相对应的操作方法
@Mapper
public interface UserMaper extends BaseMapper<User> {
}
