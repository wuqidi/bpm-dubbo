package com.wuqidi.common.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 导入辅助注解
 * 作        者： 左明强
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IEmport {
	/**
	 * 导入列索引
	 * @return
	 */
	int index() default -1;
	
	/**
	 * 导入列是否可空
	 * @return
	 */
	boolean nullable() default false;
	
	/**
	 * 导入列正则校验
	 * @return
	 */
	String regex() default "";
	
	/**
     * 导入列类型枚举
     *
     */
    public enum Field{ 
    	/**
    	 * 普通列
    	 */
    	COMMON,
    	/**
    	 * 字典列
    	 */
    	MAP,
    	/**
    	 * 数据转换列
    	 */
    	SWITCH
    };
	/**
	 * 导入列类型
	 * @return
	 */
    Field type() default Field.COMMON;
	/**
	 * 导入列字典值转换
	 * @return
	 */
	String map() default "";
	/**
	 * 导入列字典值转换
	 * @return
	 */
	String mapKey() default "";
	/**
	 * 导入列字典值转换
	 * @return
	 */
	String mapValue() default "";
}
