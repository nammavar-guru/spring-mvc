package SpringMVC.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteProductController {

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
	
	@DeleteMapping(path="/products/{productId}")
	@ResponseStatus(code=HttpStatus.OK, reason="This product is deleted ")
	public void deleteProduct(@PathVariable String productId) {
		Product deleteProduct = productRepository.get(productId);
		if(deleteProduct ==null) {
			throw new ProductNotFoundException();
		}
		productRepository.remove(productId);
		
	}
	
	@DeleteMapping(path="/products")
	@ResponseStatus(code=HttpStatus.OK, reason="This product is deleted ")
	public void deleteProduct(@RequestBody Product product) {
		Product deleteProduct = productRepository.get(product.getId());
		deleteProduct.setName(product.getName());
	}
	
	@GetMapping("/deleteproducts")
	public ResponseEntity<Object> getProduct(){
		return new ResponseEntity<>(productRepository.values(),HttpStatus.OK);
	}
}
