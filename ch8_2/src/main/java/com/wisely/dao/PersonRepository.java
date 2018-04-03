package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisely.domain.Person;
import com.wisely.support.CustomRepository;

/**
 * 定义数据访问接口:
 * 1 使用方法名查询，接受一个 name 参数，返回值为列表。
 * 2 使用方法名查询，接受 name 和 address，返回值为单个对象。
 * 3 使用 @query 查询，参数按照名称绑定。
 * 4 使用 @NamedQuery 查询，请注意我们在实体类中做的 @NamedQuery 的定义。
 */
public interface PersonRepository extends CustomRepository<Person, Long> {
	//1
	List<Person> findByAddress(String address);
	
	//2
	Person findByNameAndAddress(String name,String address);
	
	//3
	@Query("select p from Person p where p.name= :name and p.address= :address")
	Person withNameAndAddressQuery(@Param("name")String name,@Param("address")String address);
	
	//4
	Person withNameAndAddressNamedQuery(String name,String address);

}
