package MyBatisPlusApp.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode
//@Data
//@Data注解的作用包括上面所有所有
public class User {
    @TableId(type = IdType.AUTO)//id生成策略--->自增
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;
    //@TableLogic(value = "0",delval = "1")
    //@TableField(exist = false)//因为实际数据库没有这个字段，这里只是为了指定上诉注解
    //private Integer state;
    //@Version//用这个字段实现乐观锁，或者说，设置这个字段为乐观锁
    //private Integer version;
}