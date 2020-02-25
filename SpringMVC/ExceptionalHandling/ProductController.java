package SpringMVC.ExceptionalHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Object> getProductId(@PathVariable String productId){
		Product product = productRepository.get(productId);
		if(product==null)
			throw new ProductNotFoundException();
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	
	@ExceptionHandler
	public String handle(Exception e) {
		return "Exception"+e;
	}

	@PostMapping(path="/products",consumes="application/json")
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		Product createProduct = productRepository.get(product.getId());
		System.out.println("Create Product"+createProduct);
		if(createProduct == null) {
			productRepository.put(product.getId(), product);
			return new ResponseEntity<>("Products created successfully",HttpStatus.OK);
		}
		return null;
	}
	
}
