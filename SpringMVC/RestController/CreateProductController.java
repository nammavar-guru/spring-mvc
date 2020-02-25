package SpringMVC.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CreateProductController{

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
	
	
	
	@PostMapping(path="/products",consumes="application/json")
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		if(product.getId()==null) {
			return new ResponseEntity<>("Product ID is not found",HttpStatus.NOT_FOUND);
		}
		Product createProduct = productRepository.get(product.getId());
		if(createProduct != null) {
			return new ResponseEntity<>("Product ID is conflict",HttpStatus.CONFLICT);
		}
		System.out.println("Create Product"+createProduct);
		if(createProduct == null) {
			productRepository.put(product.getId(), product);
			return new ResponseEntity<>("Products created successfully"+product.getId(),HttpStatus.CONFLICT);
		}
		return null;
	}
	
	@PostMapping(path="/productsxml",consumes="application/xml")
	public ResponseEntity<Object> createProductXML(@RequestBody Product product){
		Product createProduct = productRepository.get(product.getId());
		System.out.println("Create Product"+createProduct);
		if(createProduct == null) {
			productRepository.put(product.getId(), product);
			return new ResponseEntity<>("Products created successfully"+product.getId(),HttpStatus.CREATED);
		}
		return null;
	}
	
	@PostMapping(path="/products/modelattribute/{id}/{name}")
	public ResponseEntity<Object> createProductModelAttribute(@ModelAttribute Product product){
		Product createProduct = productRepository.get(product.getId());
		System.out.println("Create Product"+createProduct);
		if(createProduct == null) {
			productRepository.put(product.getId(), product);
			return new ResponseEntity<>("Products created successfully"+product.getId(),HttpStatus.CREATED);
		}
		return null;
	}
}
