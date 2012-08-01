/* Querys.java

	Purpose:
		
	Description:
		
	History:
		Jul 30, 2012, Created by Ian Tsai(Zanyking)

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under ZOL in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package demo.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Ian Y.T Tsai(zanyking)
 *
 */
public class Querys {

	
	/**
	 * SELECT * FROM [clz] WHERE [fieldName] = [value]
	 * 
	 * @param <T> 
	 * @param clz 
	 * @param fieldName 
	 * @param value
	 * @param em
	 * @return
	 */
	public static <T> List<T> findEquals(Class<T> clz, String fieldName, Object value, EntityManager em){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<T> criteria = cb.createQuery(clz);

		Root<T> r = criteria.from(clz);
		criteria.select(r).where(cb.equal(r.get(fieldName), value));
		
		TypedQuery<T> q = em.createQuery(criteria); 
		
		return q.getResultList();
	}
	
	/**
	 * 
	 * @param <T>
	 * @param clz
	 * @param fieldName
	 * @param value
	 * @param em
	 * @return
	 */
	public static <T> T findSingle(Class<T> clz, String fieldName, Object value, EntityManager em){
//		try{
//			
//		}catch(RuntimeException e){
//			e.printStackTrace();
//		}
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<T> criteria = cb.createQuery(clz);

		Root<T> r = criteria.from(clz);
		criteria.select(r).where(cb.equal(r.get(fieldName), value));
		
		TypedQuery<T> q = em.createQuery(criteria); 
		
		try{
			return q.getSingleResult();	
		}catch(NoResultException e){
			return null;
		}
	}
}
