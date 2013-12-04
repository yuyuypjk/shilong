package com.shilong.web.action.product;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.shilong.bean.product.ProductType;
import com.shilong.service.product.ProductTypeService;

@Controller("/control/product/type/list")
public class ProductTypeAction extends Action {
    @Resource(name="productTypeServiceBean")
	private ProductTypeService productTypeService;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductType productType= productTypeService.find(ProductType.class, 3);
		request.setAttribute("productType", productType);
		return mapping.findForward("list");
	}
}
