package cn.ekgc.gyxt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ekgc.gyxt.dao.PurchaseDao;
import cn.ekgc.gyxt.pojo.entity.Purchase;
import cn.ekgc.gyxt.pojo.entity.Status;
import cn.ekgc.gyxt.pojo.entity.User;
import cn.ekgc.gyxt.pojo.vo.Page;
import cn.ekgc.gyxt.service.PurchaseService;
import cn.ekgc.gyxt.util.ConstantUtil;



/**
 * <b>采购模块业务层接口实现类</b>
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
@Service("purchaseService")
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
	@Resource(name = "purchaseDao")
	private PurchaseDao purchaseDao;

	/**
	 * <b>根据采购状态分页查询采购分页对象</b>
	 * @param page
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	public Page<Purchase> getPurchasePageByStatusCode(Page<Purchase> page, String statusCode) 
			throws Exception {
		// 绑定Map查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 绑定分页信息
		paramMap.put("begin", page.getBegin());
		paramMap.put("size", page.getSize());
		// 绑定采购状态
		paramMap.put("statusCode", statusCode);
		
		// 进行分页查询，获得列表
		List<Purchase> list = purchaseDao.findPurchaseListByQuery(paramMap);
		// 为了能够获得相应的总条数，需要去掉分页的信息
		paramMap.remove("begin");
		paramMap.remove("size");
		Long totalSize = (long)purchaseDao.findPurchaseListByQuery(paramMap).size();
		
		// 分别设定分页信息
		page.setList(list);
		page.setTotalSize(totalSize);
		page.setTotalPage();
		return page;
	}

	/**
	 * <b>保存采购信息</b>
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	public boolean savePurchase(Purchase purchase, User applicant) throws Exception {
		// 生成订单编号 规则:采购首字母+当前时间的毫秒数
		purchase.setPurchaseNo("CG"+System.currentTimeMillis());
		// 设定采购人
		purchase.setApplicant(applicant);
		// 设定采购的时间
		purchase.setPurchaseTime(new Date());
		// 更新状态为  已申请，未审批
		Status status = new Status();
		status.setStatusCode(ConstantUtil.STATUS_APPLY);
		purchase.setStatus(status);
		
		// 使用dao层保存
		try {
			purchaseDao.savePurchase(purchase);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * <b>修改采购信息</b>
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	public boolean updatePurchase(Purchase purchase) throws Exception {
		try {
			purchaseDao.updatePurchase(purchase);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
