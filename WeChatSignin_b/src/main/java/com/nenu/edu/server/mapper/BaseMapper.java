package com.nenu.edu.server.mapper;

import com.nenu.edu.server.po.BasePo;

/**
 * @Author: Lu Jingyuan
 * @Description:基础数据映射
 */
public interface BaseMapper<T extends BasePo> {
    /**
     * 插入
     *
     * @param t
     * @return
     */
    Long insert(T t);

    /**
     * 根据编号查找
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 根据编号删除
     *
     * @param id
     * @return
     */
    Long deleteById(Long id);
}
