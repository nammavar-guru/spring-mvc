package SpringMVC.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateProductController {

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
	
	@PutMapping(path="/products/{productId}")
	@ResponseStatus(code=HttpStatus.OK, reason="This product is updated ")
	public void updateProduct(@PathVariable String productId, @RequestBody Product product) {
		Product updateProduct = productRepository.get(productId);
		if(updateProduct ==null) {
			throw new ProductNotFoundException();
		}
		updateProduct.setName(product.getName());
	}
	
	@PutMapping(path="/products")
	@ResponseStatus(code=HttpStatus.OK, reason="This product is updated ")
	public void updateProduct(@RequestBody Product product) {
		Product updateProduct = productRepository.get(product.getId());
		updateProduct.setName(product.getName());
	}
	
	@GetMapping("/updateproducts")
	public ResponseEntity<Object> getProduct(){
		return new ResponseEntity<>(productRepository.values(),HttpStatus.OK);
	}
}
