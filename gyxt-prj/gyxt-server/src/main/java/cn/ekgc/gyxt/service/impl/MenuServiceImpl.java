package cn.ekgc.gyxt.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ekgc.gyxt.dao.MenuDao;
import cn.ekgc.gyxt.pojo.entity.Menu;
import cn.ekgc.gyxt.pojo.entity.Role;
import cn.ekgc.gyxt.service.MenuService;
import cn.ekgc.gyxt.util.ConstantUtil;


/**
 * <b>菜单模块业务层接口实现类</b>
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	/**
	 * <b>根据角色信息查找其所对应的的首页面菜单列表</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getMenuListForIndex(Role role) throws Exception {
		// 将所有的查询条件封装到Map集合中
		Map<String, Object> params = new HashMap<String, Object>();
		// 查询所有的一级菜单
		params.put("parentId", 0L);
		params.put("roleId", role.getRoleId());
		params.put("statusCode", ConstantUtil.STATUS_ENABLE);
		
		// 进行查询操作
		List<Menu> menuList = menuDao.findListByQuery(params);
		// 判断一级菜单是否存在，如果存在，遍历一级菜单，获得其对应的二级菜单
		if (menuList != null && menuList.size() > 0) {
			for (Menu parent : menuList) {
				// 使用一级菜单的主键，查询二级菜单
				params.put("parentId", parent.getMenuId());
				List<Menu> childList = menuDao.findListByQuery(params);
				parent.setChildList(childList);
			}
		}
		return menuList;
	}
}
