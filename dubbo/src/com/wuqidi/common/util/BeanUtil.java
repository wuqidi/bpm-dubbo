package com.wuqidi.common.util;

import org.springframework.cglib.beans.BeanCopier;

public class BeanUtil {
	
	private static BeanCopier copy = null;
	
	public BeanUtil(Class source,Class desc){
		if(copy==null){
			copy = BeanCopier.create(source, desc, false);
		}
	}
	
	
	/**
	 * 单个对象的bean复制
	 * @param source 源对象
	 * @param dest  目的对象
	 * @return 目的对象
	 */
	public static <T> T copy(Object source,T dest){
		BeanCopier copy = BeanCopier.create(source.getClass(), dest.getClass(), false);
		copy.copy(source, dest, null);
		return dest;
	}
	
	
	
}
