package com.shilong.service.base;

import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shilong.bean.QueryResult;

@Transactional
public abstract class DaoSupport implements DAO {
     @PersistenceContext protected EntityManager em;
     
	@Override
	public void save(Object entity) {
		em.persist(entity);
	}

	@Override
	public void update(Object entity) {
		em.merge(entity);
	}

	@Override
	public <T> void delete(Class<T> entityClass,Object entityid) {
		delete(entityClass,new Object[]{entityid});
	}

	@Override
	public <T> void delete(Class<T> entityClass,Object[] entityids) {
		for(Object id : entityids){
			em.remove(em.getReference(entityClass, id));
		}
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> T find(Class<T> entityClass, Object entityId) {
		
		return em.find(entityClass, entityId);
	}

	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxsult, String wherejpql, Object[] queryParams) {
		// TODO Auto-generated method stub
		return getScrollData(entityClass, firstindex, maxsult, wherejpql, queryParams,null);
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxsult, LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return getScrollData(entityClass, firstindex, maxsult, null, null,orderby);
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxsult) {
		// TODO Auto-generated method stub
		return getScrollData(entityClass, firstindex, maxsult, null, null,null);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> QueryResult<T> getScrollData(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return getScrollData(entityClass, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstindex, int maxsult,String wherejpql,Object[] queryParams,LinkedHashMap<String, String> orderby) {
QueryResult<T> qr= new QueryResult<T>();
String entityname=getEntityName(entityClass);
Query query= em.createQuery("select o from "+entityname+" o "+(wherejpql==null?"":"where "+wherejpql)+buildOrderby(orderby));
setQueryParams(query, queryParams);
if(firstindex!=-1&&maxsult!=-1)query.setFirstResult(firstindex).setMaxResults(maxsult);
qr.setResultlist(query.getResultList());
query=em.createQuery("select count(o) from "+entityname+" o "+(wherejpql==null?"":"where "+wherejpql));
setQueryParams(query, queryParams);
qr.setTotalrecord((Long)query.getSingleResult());
		return qr;
	}
	
	protected void setQueryParams(Query query,Object[] queryParams) {
		if(queryParams!=null&&queryParams.length>0){
			for(int i=0;i<queryParams.length;i++){
				query.setParameter(i+1, queryParams[i]);
			}
		}
	}
	
	/**
	 * 组装order by 语句
	 * @param orderby
	 * @return
	 */
	protected String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql=new StringBuffer("");
		if(orderby!=null&&orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	
	/**
	 * 获取实体的名称
	 * @param entityClass实体类
	 * @return
	 */
	protected <T> String getEntityName(Class<T> entityClass)
	{
		String  entityname=entityClass.getSimpleName();
		Entity entity=entityClass.getAnnotation(Entity.class);
		if(entity.name()!=null&&!"".equals(entity.name())){
			entityname=entity.name();
		}
		return entityname;
	}
}
