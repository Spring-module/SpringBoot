package com.wisely.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import static com.wisely.specs.CustomerSpecs.*;

/**
 * 自定义 Repository 的实现：
 * （2）定义接口的实现：
 */
public class CustomRepositoryImpl <T, ID extends Serializable> 
					extends SimpleJpaRepository<T, ID>  implements CustomRepository<T,ID> {//1
	
	private final EntityManager entityManager;//2
	
	public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {//3
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Page<T> findByAuto(T example, Pageable pageable) {
		return findAll(byAuto(entityManager, example),pageable);
	}


}
