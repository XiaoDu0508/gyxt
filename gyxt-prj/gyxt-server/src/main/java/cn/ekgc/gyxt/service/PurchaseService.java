package cn.ekgc.gyxt.service;

import cn.ekgc.gyxt.pojo.entity.Purchase;
import cn.ekgc.gyxt.pojo.entity.User;
import cn.ekgc.gyxt.pojo.vo.Page;


/**
 * <b>采购模块业务层接口</b>
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
public interface PurchaseService {
	/**
	 * <b>根据采购状态分页查询采购分页对象</b>
	 * @param page
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	Page<Purchase> getPurchasePageByStatusCode(Page<Purchase> page, String statusCode) throws Exception;
	
	/**
	 * <b>处理业务保存申请信息</b>
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	boolean savePurchase(Purchase purchase, User applicant) throws Exception;
	
	
	/**
	 * <b>进行申请业务处理</b>
	 * @param purchaseId
	 * @param reviewRemark
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	boolean updatePurchase(Purchase purchase)throws Exception;
}
