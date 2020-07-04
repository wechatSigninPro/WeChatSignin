package com.nenu.edu.server.other;

import com.nenu.edu.server.BaseTest;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.Test;

import javax.validation.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:29 2020/7/4
 */
public class ValidatorTest extends BaseTest {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @Override
    protected Class getTargetClass() {
        return ValidatorTest.class;
    }

    @Test
    public void testValidate() {
        User user = new User();

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user);
        this.printMessage(set);
    }

    @Test
    public void testGroupValidate() {
        User user = new User();

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user, Group.Add.class);
        this.printMessage(set);
    }

    @Test
    public void testValidateParams() throws NoSuchMethodException {
        ExecutableValidator validator = factory.getValidator().forExecutables();

        UserController userController = new UserController();
        User user = new User();
        user.setName("hello");
        Limit limit = new Limit();
        Object[] params = {user, limit};
        Method method = UserController.class.getDeclaredMethod("getUser", User.class, Limit.class);
        Set<ConstraintViolation<UserController>> set = validator.validateParameters(userController, method, params);
        System.out.println("set: "+set);
        System.out.println("set.size: "+set.size());
        this.printMessage(set);
    }

    public <T> void printMessage(Set<ConstraintViolation<T>> constraintViolations) {
        for (ConstraintViolation<T> violation : constraintViolations) {
            log.info(violation.getMessage());
            PathImpl path = (PathImpl) violation.getPropertyPath();
            log.info(path.getLeafNode().getName());
        }
    }

    @Data
    private static class User {
        @NotEmpty(groups = Group.Add.class, message = "编号不能为空")
        private String id;
        @NotEmpty(message = "名称不能为空")
        private String name;
        @NotEmpty(groups = Group.Modify.class, message = "年龄不能为空")
        private Integer age;
    }

    @Data
    private static class Limit {
        @NotEmpty(groups = Group.Add.class, message = "编号不能为空")
        private String id;
        @NotEmpty(message = "名称不能为空")
        private String name;
        @NotEmpty(groups = Group.Modify.class, message = "年龄不能为空")
        private Integer age;
    }

    private interface Group {
        interface Add {

        }

        interface Modify {

        }
    }

    private static class UserController {

        public User getUser(@Valid User user, Limit limit) {
            return user;
        }
    }
}
