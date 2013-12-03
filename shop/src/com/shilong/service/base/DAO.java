package com.shilong.service.base;

import java.util.LinkedHashMap;

import com.shilong.bean.QueryResult;

public interface DAO {
	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体
	 */
	public void save(Object entity);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体
	 */
	public void update(Object entity);

	/**
	 * 删除实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityid
	 *            实体id
	 */
	public <T> void delete(Class<T> entityClass, Object entityid);

	/**
	 * 删除实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityids
	 *            实体id
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityids);

	/**
	 * 获取实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 * @return
	 */
	public <T> T find(Class<T> entityClass, Object entityId);

	/**
	 * 获取分页数据
	 * 
	 * @param entityClass
	 *            实体类
	 * @param firstindex
	 *            开始索引
	 * @param maxsult
	 *            需要获取的记录数
	 * @return
	 */
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxsult, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby);

	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxsult, String wherejpql,
			Object[] queryParams);

	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxsult, LinkedHashMap<String, String> orderby);

	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxsult);

	public <T> QueryResult<T> getScrollData(Class<T> entityClass);

}
