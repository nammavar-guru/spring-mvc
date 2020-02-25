package SpringMVC.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {
	private static Map<String,Product> productRepository = new HashMap();
	
	static {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepository.put(honey.getId(), honey);
		
		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepository.put(almond.getId(), almond);
		
	}
	
	
	@GetMapping("/products")
	public ResponseEntity<Object> getProduct(){
		return new ResponseEntity<>(productRepository.values(),HttpStatus.OK);
	}
	
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Object> getProductId(@PathVariable String productId){
		Product product = productRepository.get(productId);
		if(product==null) {
			return new ResponseEntity<>("ID is not found or invalid",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/products/responsebody/{productId}")
	public Product getProductIdResponseBody(@PathVariable String productId){
		Product product = productRepository.get(productId);
		return product;
	}
	
	@GetMapping(path="/products/xml/{productId}",produces=MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getProductIdinXML(@PathVariable String productId){
		Product product = productRepository.get(productId);
		return new ResponseEntity<>(product,HttpStatus.OK);
		
	}
	
	
	@GetMapping(path="/products/json/{productId}",produces="application/json")
	@ResponseBody
	public ResponseEntity<Object> getProductIdinJSON(@PathVariable String productId){
		Product product = productRepository.get(productId);
		return new ResponseEntity<>(product,HttpStatus.OK);
		
	}
	
	
	
	
	///products/matrix/1;q=2;r=3
	@GetMapping("/products/matrix/{productId}")
	public ResponseEntity<Object> getProductMatrix(@PathVariable String productId,
			@MatrixVariable(name="q", pathVar="productId") String q,
			@MatrixVariable(required=false) String r){
		Product product = productRepository.get(productId);
		System.out.println("q"+q);
		System.out.println("r"+r);
		return new ResponseEntity<>(product,HttpStatus.OK);
		
	}
	
		
	//products/requestparam/1?productKey=guru
		@GetMapping("/products/requestparam/{productId}")
		public ResponseEntity<Object> getProductParam(@PathVariable String productId,
				@RequestParam(required=false) String productKey){
			Product product = productRepository.get(productId);
			System.out.println("productKey"+productKey);
			return new ResponseEntity<>(product,HttpStatus.OK);
			
		}
}
