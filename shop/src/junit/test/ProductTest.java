package junit.test;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shilong.bean.QueryResult;
import com.shilong.bean.product.ProductType;
import com.shilong.service.product.ProductTypeService;

public class ProductTest {

	private static ApplicationContext cxt;
	private static ProductTypeService productTypeService;
	
	@BeforeClass
   public static void setUpBeforeClass()throws Exception{
		
	   try {
		cxt=new ClassPathXmlApplicationContext("beans.xml");
		   productTypeService=(ProductTypeService)cxt.getBean("productTypeServiceBean");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
	
	@Test
	public void testSave(){
		for(int i=0;i<20;i++){
	 ProductType type= new ProductType();
	 type.setName(i+"篮球用品");
	 type.setNote("好篮球");
	 productTypeService.save(type);
		}
	// System.out.println(dataSource);
	}
	@Test
	public void testFind(){
		ProductType type=productTypeService.find(ProductType.class, 1);
		Assert.assertNotNull("获取不到id为1的数据",type);
		
	}
	@Test
	public void testUpdate(){
		ProductType type=productTypeService.find(ProductType.class, 1);
		type.setName("足球");
		type.setNote("好足球");
		productTypeService.update(type);
		
	}
	@Test
	public void testDelete(){
		productTypeService.delete(ProductType.class, 2);
		
	}
	
	@Test
	public void testgetScrollData(){
		LinkedHashMap<String, String> orderbyql=new LinkedHashMap<String, String>();
		orderbyql.put("typeid", "asc");
		
		QueryResult<ProductType> qr=productTypeService.getScrollData(ProductType.class);
		for(ProductType t:qr.getResultlist()){
			System.out.println(t.getName());
		}
	}
	
}
