package com.bootdo.system.dao;

import com.bootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hgy
 * @email 448249687@qq.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);

	//获取第一个节点审批人
	List<Map<String,Object>>  getAuditPsn(Map<String,String> map);
	//获取第一个以后节点审批人
	List<Map<String,Object>>  getAuditPsnQuery(Map<String,String> map);

	List<UserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

}
