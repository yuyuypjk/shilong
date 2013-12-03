package com.shilong.service.base;

import java.util.LinkedHashMap;

import com.shilong.bean.QueryResult;

public interface DAO {
	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 *            ʵ��
	 */
	public void save(Object entity);

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 *            ʵ��
	 */
	public void update(Object entity);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param entityClass
	 *            ʵ����
	 * @param entityid
	 *            ʵ��id
	 */
	public <T> void delete(Class<T> entityClass, Object entityid);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param entityClass
	 *            ʵ����
	 * @param entityids
	 *            ʵ��id
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityids);

	/**
	 * ��ȡʵ��
	 * 
	 * @param entityClass
	 *            ʵ����
	 * @param entityId
	 *            ʵ��id
	 * @return
	 */
	public <T> T find(Class<T> entityClass, Object entityId);

	/**
	 * ��ȡ��ҳ����
	 * 
	 * @param entityClass
	 *            ʵ����
	 * @param firstindex
	 *            ��ʼ����
	 * @param maxsult
	 *            ��Ҫ��ȡ�ļ�¼��
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
