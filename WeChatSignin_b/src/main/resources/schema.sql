
-- 远程数据库
-- 连接：localhost:3306/server
-- 用户名：root，密码：0123456789
-- 创建库
CREATE DATABASE checking
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

-- 创建表
USE checking;
-- 老师
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id`          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  `create_time` DATETIME         NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='老师';

-- 学生
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id`          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  `major`       VARCHAR(40)
  COMMENT '专业',
  `grade`       VARCHAR(40)
  COMMENT '年级',
  `clazz`       VARCHAR(40)
  COMMENT '班级',
  `create_time` DATETIME         NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='学生';

-- 用户
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  `open_id`     VARCHAR(40)      NOT NULL
  COMMENT '微信用户编号',
  `role`        VARCHAR(20)
  COMMENT '角色',
  `name`        VARCHAR(40)
  COMMENT '姓名',
  `school`      VARCHAR(40)
  COMMENT '学校',
  `campus_id`   VARCHAR(40)
  COMMENT '校园账号',
  `department`  VARCHAR(40)
  COMMENT '学院',
  `phone_num`   CHAR(11)
  COMMENT '手机号',
  `email`       VARCHAR(40)
  COMMENT 'email',
  `teacher_id`  INTEGER UNSIGNED
  COMMENT '老师编号',
  `student_id`  INTEGER UNSIGNED
  COMMENT '学生编号',
  `create_time` DATETIME         NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='用户';

-- 课程
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id`          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  `teacher_id`  INTEGER UNSIGNED NOT NULL
  COMMENT '老师编号',
  `name`        VARCHAR(40)
  COMMENT '课程名称',
  `code`        VARCHAR(20)
  COMMENT '课程代码',
  `time`        VARCHAR(40)
  COMMENT '上课时间',
  `place`       VARCHAR(40)
  COMMENT '上课地点',
  `create_time` DATETIME         NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_course_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='课程';

-- 课程申请
DROP TABLE IF EXISTS `course_application`;
CREATE TABLE `course_application` (
  `id`          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  `student_id`  INTEGER UNSIGNED NOT NULL
  COMMENT '学生编号',
  `course_id`   INTEGER UNSIGNED NOT NULL
  COMMENT '课程编号',
  `status`      VARCHAR(20)      NOT NULL
  COMMENT '状态',
  `create_time` DATETIME         NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_course_application_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_course_application_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='课程申请';

-- 学生-课程
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `id`          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  `student_id`  INTEGER UNSIGNED NOT NULL
  COMMENT '学生编号',
  `course_id`   INTEGER UNSIGNED NOT NULL
  COMMENT '课程编号',
  `create_time` DATETIME         NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_student_course_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_course_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='学生-课程';

-- 点名
DROP TABLE IF EXISTS `call`;
CREATE TABLE `call` (
  `id`          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  `course_id`   INTEGER UNSIGNED
  COMMENT '课程编号',
  `password`    VARCHAR(20)      NOT NULL
  COMMENT '口令',
  `latitude`    FLOAT            NOT NULL
  COMMENT '坐标纬度',
  `longitude`   FLOAT            NOT NULL
  COMMENT '坐标经度',
  `create_time` DATETIME         NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_call_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='点名';

-- 签到
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `id`          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  `call_id`     INTEGER UNSIGNED
  COMMENT '点名编号',
  `student_id`  INTEGER UNSIGNED
  COMMENT '学生编号',
  `create_time` DATETIME         NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sign_call` FOREIGN KEY (`call_id`) REFERENCES `call` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_sign_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='点名';

-- 反馈
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id`          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  `user_id`     INTEGER UNSIGNED
  COMMENT '用户编号',
  `content`     VARCHAR(600)
  COMMENT '内容',
  `create_time` DATETIME         NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_feedback_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='反馈';
