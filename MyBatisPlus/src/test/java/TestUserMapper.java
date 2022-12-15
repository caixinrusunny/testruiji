import MyBatisPlusApp.Mapper.UserMaper;
import MyBatisPlusApp.MyBatisPlus_App;
import MyBatisPlusApp.bean.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = MyBatisPlus_App.class)
public class TestUserMapper {

    @Autowired
    private UserMaper userMaper;

    @Test
    public void getAll() {
        List<User> users = userMaper.selectList(null);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void addUser() {
        User usera = new User(null, "张三", "1234", 18, "1234567890");
        int insert = userMaper.insert(usera);
        System.out.println("i:" + insert);
    }

    @Test
    public void update() {
        User usera = new User(15L, "李四", "1234", 18, "1234567890");
        int i = userMaper.updateById(usera);
        System.out.println("i---->" + i);
    }

    @Test
    public void getOne() {
        User user = userMaper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void delone() {
        int i = userMaper.deleteById(16);
        System.out.println("i------>" + i);
    }

    @Test
    public void getPage() {
        //1.创建分页对象 没置分页参数
        // Page构造方法 第一个参数是当前页 第二个参数是每页显示几条数据
        Page<User> page = new Page<>(1, 3);
        //2.执行查询
        userMaper.selectPage(page, null);
        //获得分页数据
        System.out.println("页码值" + page.getCurrent());
        System.out.println("页显示条数" + page.getSize());
        System.out.println("总页数" + page.getPages());
        System.out.println("总条数" + page.getTotal());
        System.out.println("当前数" + page.getRecords());
    }

    //条件查询
    public void getByAge() {
        //1.构造条件查询构造器（条件构造器）
        //下面代码相当于 select * from user
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //1.2添加查询条件
        //在查询条件构造器中：lt->小于 le->小于等于 gt->大于  ge->大于等于
        //两个参数或者三个参数
        //两个的情况下：(字段,过滤条件)
        //三个的情况下：(Boolean,字段,过滤条件)
        //字段名：直接写字段名，Strin类型
        //过滤条件：就是筛选条件
        //Boolean值：true(后面的链接条件成立，拼接到查询语句中)；false(后面的查询条件无效，不拼接到查询语句中)
        //下面相当于 where age > 18
        userQueryWrapper.gt("age", 18);
        //2.执行查询语句
        List<User> users = userMaper.selectList(userQueryWrapper);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void getByAgeLambda() {
        //1.构造条件查询构造器（条件构造器）
        //下面代码相当于 select * from user
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //1.2添加查询条件
        //在查询条件构造器中：lt->小于 le->小于等于 gt->大于  ge->大于等于
        //有两种方式
        //1.2.1:userQueryWrapper.lambda()把查询条件转成lambda格式
        LambdaQueryWrapper<User> lambda = userQueryWrapper.lambda();
        //1.2.2:用lambdaQueryWrapper，则不用转化
        //两种都有两个参数或者三个参数
        //两个的情况下：(字段,过滤条件)
        //三个的情况下：(Boolean,字段,过滤条件)
        //字段名：直接写字段名，Strin类型
        //过滤条件：就是筛选条件
        //Boolean值：true(后面的链接条件成立，拼接到查询语句中)；false(后面的查询条件无效，不拼接到查询语句中)
        lambda.lt(User::getAge, 18);//第一种方法
        lambdaQueryWrapper.lt(User::getAge, 18);//第二种方法

        //2.执行查询语句
        List<User> users = userMaper.selectList(lambdaQueryWrapper);
        for (User u : users) {
            System.out.println(u);
        }
    }

    //条件的&&和||
    @Test
    public void getByAge_() {
        //&&
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //方式一(常用的)
        //userLambdaQueryWrapper.lt(User::getAge, 18);
        //userLambdaQueryWrapper.lt(User::getAge, 38);
        //方式二：链式编程
        // lambda特有(后面也可再加其他的)
        userLambdaQueryWrapper.lt(User::getAge,18).lt(User::getAge,38);
        //在构造器中，如果有多个条件，中间没有说明什么关联关系，默认and
        //即SELECT id,name,password,age,tel FROM user WHERE (age < ? AND age < ?)
        List<User> users = userMaper.selectList(userLambdaQueryWrapper);
        System.out.println(users);
    }
    @Test
    public void getByAge__() {
        // ||
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //方式一(常用的)
        //userLambdaQueryWrapper.lt(User::getAge, 18);
        //userLambdaQueryWrapper.or();//说明关联关系
        //userLambdaQueryWrapper.lt(User::getAge, 38);
        //方式二：链式编程
        // lambda特有，后面也可再加其他的
        //其执行先后次序遵循逻辑排序，即先and再or
        //SELECT id,name,password,age,tel FROM user WHERE (age < ? OR age > ? AND name = ?)
        userLambdaQueryWrapper.lt(User::getAge,18).or().gt(User::getAge,20).eq(User::getName,"tom");
        //SELECT id,name,password,age,tel FROM user WHERE (age < ? AND name = ? OR age > ? AND name = ?)
        userLambdaQueryWrapper.lt(User::getAge,18).eq(User::getName,"tom").or().gt(User::getAge,20).eq(User::getName,"tom");
        //在构造器中，如果有多个条件，中间没有说明什么关联关系，默认and
        //即SELECT id,name,password,age,tel FROM user WHERE (age < ? AND age < ?)
        List<User> users = userMaper.selectList(userLambdaQueryWrapper);
        System.out.println(users);
    }

    //非空判断
    //先判断null再查询
    //直接用三个参数的查询语句

    //查询包含部分函数属性
    @Test
    public void test1(){
    //1.方法一:
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //相当与 select id,name,age from user;
        userQueryWrapper.select("id","name","agge");//统计要显示的字段
    //2.方法二:
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getId,User::getName,User::getAge);
        List<User> users = userMaper.selectList(userLambdaQueryWrapper);


    }


    @Test
    //查询没有定义的属性，比如count(不可运行)
    //看源代码------>查询每个电话有多少记录(电话可重复)
    //完成这条语句的查询：SELECT COUNT(*),tel FROM USER GROUP BY tel
    public void test2(){
        //方法一:
        ////可以完成：SELECT COUNT(*),tel FROM USER GROUP BY tel
        //QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        ////select count(*) as count,tel  from user
        //userQueryWrapper.select("count(*) as count,tel");
        //userQueryWrapper.groupBy("tel");
        ////Integer integer1 = userMaper.selectCount(null);//与上一行一样的作用
        //List<Map<String, Object>> maps = userMaper.selectMaps(userQueryWrapper);//因为返回值是int不是User
        //System.out.println(maps);

        //方法二:
        //// 可以完成：SELECT COUNT(*),tel FROM USER GROUP BY tel
        //userQueryWrapper.select("count(*) as count","tel");
        //userQueryWrapper.groupBy("tel");
        //List<Map<String, Object>> maps = userMaper.selectMaps(userQueryWrapper);
        //System.out.println(maps);

        ////方法三:
        //lambda不能完成复杂的聚合(sum、max等)和分组(group by)
        //特别不可以聚合和分组混合一起！！！！！！
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ////以下不是完成语句的
        ////select * from user where tel = 12345678
        ////userLambdaQueryWrapper.eq(User::getTel,"12345678910");
        //selectCount--->返回一个Integer类型的数值，所以不可以查询返回多个表的count或者分表后的每个表的count
        ////Integer integer = userMaper.selectCount(userLambdaQueryWrapper);//只会显示一个表的全部数据数
        //

        userLambdaQueryWrapper.groupBy(User::getTel);//只会显示每个分组中的一个
        List<Map<String, Object>> maps = userMaper.selectMaps(userLambdaQueryWrapper);
        System.out.println(maps);

    }


    //多条件查询
    //范围匹配(>,<,=,>=,<=,between)
    //模糊(like)
    //空值判断(null)
    //包含匹配(in)
    //分组(group)
    //排序(order)


    //步骤
    //1.创建构造器
    //2.添加查询条件
    //3.执行查询

    public void test_between(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.between(User::getAge,18,30);//(18,30)
        List<User> users = userMaper.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }

    public void test_like(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.like(User::getName,"t");//where name like '%t%'
        lambdaQueryWrapper.likeLeft(User::getName,"t");//where name like '%t'-->以“t”结尾
        lambdaQueryWrapper.likeRight(User::getName,"t");//where name like 't%'-->以“t”开头


        List<User> users = userMaper.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }

    public void test_group(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //select count(*) as count,tel  from user
        userQueryWrapper.select("count(*) as count,tel");
        userQueryWrapper.groupBy("tel");
        List<Map<String, Object>> maps = userMaper.selectMaps(userQueryWrapper);//因为返回值是int不是User
        System.out.println(maps);
    }

    public void test_order(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(User::getAge);//升序
        //lambdaQueryWrapper.orderByDesc(User::getAge);//降序
        List<User> users = userMaper.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }

    public void test_in(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(User::getAge,23,22,16);
        List<User> users = userMaper.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }


    //批量删除
    public void test_deleteall(){
        //先定义删除条件
        ArrayList<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        int i = userMaper.deleteBatchIds(list);
    }

    //批量查询
    public void test_searchallById(){
        ArrayList<Long> list = new ArrayList<>();
        list.add(4L);
        list.add(5L);
        List<User> users = userMaper.selectBatchIds(list);
    }

    //逻辑删除
    /**
     *问题:在实际的环境中，如果要删除一条数据，是否真的能把这条数据删除了
     * 删除有两种
     * 1,物理删除 真正的指定删除语句delet 讲数据从数据库中删除掉也就是讲数据从硬盘中删除
     * 2.逻辑删除 为数据设置是否可用的状态字段 删除的是设置该字段 的状态为不可用数据任然在数据库中
     * 注意:
     * ①逻辑删除是要在字段上加注解@TableLogic(value = "0",delval = "1")
     * ②也可以在yml文件上配置：
     * logic-delete-field: flag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
     *logic-delete-value: 1 # 逻辑已删除值(默认为 1)
     *logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
     * 实际逻辑删除就是我们执行sql的删除语句，但是实际我们只是把某字段给修改了，表明该记录已无
     */
    //不可以执行，因为实体类没有加字段，如果要加，则很多代码的要改
    public void test_delete(){
        int i = userMaper.deleteById(1);
    }

    //乐观锁：如果查询和实际操作不一致，则不让操作
    //一般用在类似于抢票之类的，同时两个人查到有1张票，但是其中一个买了后，紧跟着另一个也要买，但是因为后面的人查询到的信息还是
    // 和前一个一样，但是实际前一个已经买了就是修改了剩余票数，和后面买的人查询到的不一致，所以后一个不能买
    //用乐观锁，需要添加乐观锁拦截器

    public void test_optimisticlock(){
        //1.先查询，再修改才会被乐观锁拦截
        User user = userMaper.selectById(1L);
        User user1 = userMaper.selectById(1L);
        user.setName("李四");
        userMaper.updateById(user);//name->李四,version->1
        user1.setName("王五");
        userMaper.updateById(user1);//name->李四,version->1
    }


}
