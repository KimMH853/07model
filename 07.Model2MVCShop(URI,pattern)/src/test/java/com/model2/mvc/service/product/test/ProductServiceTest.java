package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

/*
 *	FileName :  ProductServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml", 
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml"})
public class ProductServiceTest {

	// ==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	
	@Test 
	public void testAddProduct() throws Exception {
	  
		Product product = new Product(); 
		// product.setProdNo(10008);
		product.setProdName("빼빼로"); 
		product.setProdDetail("과자");
		product.setPrice(500); 
		product.setManuDate("111");
		product.setFileName("111");
		  
		productService.addProduct(product);
		  
		product = productService.getProduct(10014);
		System.out.println("=================" + product);
		  
		// ==> console 확인 //System.out.println(product);
		  
		// ==> API 확인 Assert.assertEquals("testProductId", product.getProductId());
		// Assert.assertEquals(10008, product.getProdNo());
		Assert.assertEquals("빼빼로", product.getProdName()); 
		Assert.assertEquals("과자",
		product.getProdDetail()); 
		Assert.assertEquals(500, product.getPrice());
		Assert.assertEquals("111", product.getManuDate()); 
		Assert.assertEquals("111",
		product.getFileName()); 
	}
  
	  @Test 
	  public void testGetProduct() throws Exception {
	  
		  Product product = new Product(); 
		  // ==> 필요하다면... //
		  //product.setProductId("testProductId"); //
		 // product.setProductName("testProductName"); //
		 // product.setPassword("testPasswd"); // product.setSsn("1111112222222"); //
		 // product.setPhone("111-2222-3333"); // product.setAddr("경기도"); //
		 // product.setEmail("test@test.com");
		  
		  product = productService.getProduct(10001);
		  
		  // ==> console 확인 // System.out.println(product);
		  
		  // ==> API 확인 Assert.assertEquals(10001, product.getProdNo());
		  Assert.assertEquals("자전거", product.getProdName());
		  Assert.assertEquals("자전거 좋아요~", product.getProdDetail());
		  Assert.assertEquals("20120514", product.getManuDate());
		  Assert.assertEquals(10000, product.getPrice());
		  Assert.assertEquals("AHlbAAAAvetFNwAA.jpg", product.getFileName());
		  
		  Assert.assertNotNull(productService.getProduct(10001)); //
		  //Assert.assertNotNull(productService.getProduct("product05")); 
	  }
	  
	  @Test 
	  public void testUpdateProduct() throws Exception{
	  
		  Product product = productService.getProduct(10010);
		  Assert.assertNotNull(product);
		  
		  Assert.assertEquals("꼬깔콘", product.getProdName()); 
		  Assert.assertEquals("과자",
		  product.getProdDetail()); 
		  Assert.assertEquals("111", product.getManuDate());
		  Assert.assertEquals(500 , product.getPrice()); 
		  Assert.assertEquals("111",
		  product.getFileName());
		  
		  product.setProdName("change"); product.setManuDate("change");
		  
		  productService.updateProduct(product);
		  
		  product = productService.getProduct(10010); Assert.assertNotNull(product);
		  
		  //==> console 확인 //System.out.println(product);
		  
		  //==> API 확인 Assert.assertEquals("change", product.getProductName());
		  Assert.assertEquals("change", product.getProdName());
		  Assert.assertEquals("change", product.getManuDate()); 
	  }
  
 

	  @Test public void testGetUserListAll() throws Exception{
	  
		  Search search = new Search(); 
		  search.setCurrentPage(1);
		  search.setPageSize(3);
		  Map<String,Object> 
		  map = productService.getProductList(search);
		  
		  List<Object> list = (List<Object>)map.get("list"); 
		  Assert.assertEquals(3,list.size());
		  
		  //==> console 확인 System.out.println(list);
		  
		  Integer totalCount = (Integer)map.get("totalCount");
		  System.out.println(totalCount);
		  
		  System.out.println("=======================================");
		  
		  search.setCurrentPage(1); search.setPageSize(3);
		  search.setSearchCondition("0"); search.setSearchKeyword(""); map =
		  productService.getProductList(search);
		  
		  list = (List<Object>)map.get("list"); 
		  Assert.assertEquals(3, list.size());
		  
		  //==> console 확인 System.out.println(list);
		  
		  totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
	  }
 
	

	  @Test 
	  public void testGetProductListByProdNo() throws Exception{
	  
		  Search search = new Search(); 
		  search.setCurrentPage(1);
		  search.setPageSize(3); 
		  search.setSearchCondition("0");
		  search.setSearchKeyword("10000"); 
		  Map<String,Object> 
		  map = productService.getProductList(search);
		  
		  List<Object> list = (List<Object>)map.get("list"); 
		  Assert.assertEquals(1,list.size());
		  
		  //==> console 확인 //System.out.println(list);
		  
		  Integer totalCount = (Integer)map.get("totalCount");
		  System.out.println(totalCount);
		  
		  System.out.println("=======================================");
		  
		  search.setSearchCondition("1");
		  search.setSearchKeyword(""+System.currentTimeMillis()); 
		  map = productService.getProductList(search);
		  
		  list = (List<Object>)map.get("list"); 
		  Assert.assertEquals(0, list.size());
		  
		  //==> console 확인 //System.out.println(list);
		  
		  totalCount = (Integer)map.get("totalCount"); 
		  System.out.println(totalCount);
	  }
 
		  
			
			  @Test 
			  public void testGetProductListByProdName() throws Exception{
			  
			  Search search = new Search(); 
			  search.setCurrentPage(1);
			  search.setPageSize(3); 
			  search.setSearchCondition("2");
			  search.setSearchKeyword("새우깡"); 
			  Map<String,Object> 
			  map = productService.getProductList(search);
			  
			  List<Object> list = (List<Object>)map.get("list"); 
			  Assert.assertEquals(3,list.size());
			  
			  //==> console 확인 System.out.println(list);
			  
			  Integer totalCount = (Integer)map.get("totalCount");
			  System.out.println(totalCount);
			  
			  System.out.println("=======================================");
			  
			  search.setSearchCondition("1");
			  search.setSearchKeyword(""+System.currentTimeMillis()); 
			  map = productService.getProductList(search);
			  
			  list = (List<Object>)map.get("list"); 
			  Assert.assertEquals(0, list.size());
			  
			  //==> console 확인 System.out.println(list);
			  
			  totalCount = (Integer)map.get("totalCount"); 
			  System.out.println(totalCount);
			  }
			 
	 
	 
}