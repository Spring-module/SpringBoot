package com.wisely.support;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 自定义 Repository 的实现：
 * （1）定义自定义 Repository 接口：
 * 1 @NoRepositoryBean 指明当前这个接口不是我们领域类的接口（如 PersonRepository）。
 * 2 我们自定义的 Repository 实现 PagingAndSortingRepository 接口，具备分页和排序的能力。
 * 3 要定义的数据操作方法在接口中的定义。
 */
@NoRepositoryBean//1
public interface CustomRepository<T, ID extends Serializable>extends JpaRepository<T, ID> ,JpaSpecificationExecutor<T>{//2
	
	Page<T> findByAuto(T example,Pageable pageable);//3
	

}
