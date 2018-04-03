package com.wisely.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * 自定义 Repository 的实现
 * (3)自定义 RepositoryFactoryBean
 * 1 自定义 RepositoryFactoryBean，继承 JpaRepositoryFactoryBean。
 * 2 重写 createRepositoryFactory方法，用当前的 CustomRepositoryFactory创建实例。
 * 3 创建 CustomRepositoryFactory，并继承 JpaRepositoryFactory。
 * 4 重写 getTargetRepository方法，获得当前自定义的 Repository实现。
 * 5 重写 getRepositorBaseClass，获得当前自定义的 Repository实现的类型。
 */
public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {// 1

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {// 2
		return new CustomRepositoryFactory(entityManager);
	}

	private static class CustomRepositoryFactory extends JpaRepositoryFactory {// 3


		public CustomRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
		}

		@Override
		@SuppressWarnings({"unchecked"})
		protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
				RepositoryInformation information, EntityManager entityManager) {// 4
			return new CustomRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);

		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {// 5
			return CustomRepositoryImpl.class;
		}
	}
}