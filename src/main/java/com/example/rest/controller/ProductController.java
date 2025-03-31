package com.example.rest.controller;

import com.example.rest.dto.ProductDTO;
import com.example.rest.entities.Product;
import com.example.rest.service.IProductService;
import com.example.rest.service.implement.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  private IProductService iproductService;

  @Autowired
  private ProductService productService;

  @GetMapping("/find/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Optional<Product> productOptional = productService.findById(id);

    if(productOptional.isPresent()) {
      Product product = productOptional.get();
      ProductDTO productDTO = ProductDTO.builder()
              .id(product.getId())
              .name(product.getName())
              .price(product.getPrice())
              .maker(product.getMaker())
              .build();
      return ResponseEntity.ok(productDTO);
    }
    return ResponseEntity.notFound().build();
  }


  @GetMapping("/findAll")
  public ResponseEntity<?> findAll() {
    List<ProductDTO> productList = productService.findAll()
            .stream()
            .map(product -> ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .maker(product.getMaker())
                    .build()
            ).toList();
    return ResponseEntity.ok(productList);
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) throws URISyntaxException {

    if(productDTO.getName().isBlank() || productDTO.getPrice() == null || productDTO.getMaker() == null ) {
      return ResponseEntity.badRequest().build();
    }

    Product product = Product.builder()
            .name(productDTO.getName())
            .price(productDTO.getPrice())
            .maker(productDTO.getMaker())
            .build();

    productService.save(product);
    return ResponseEntity.created(new URI("/api/product/save")).build();
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {

    Optional<Product> productOptional = productService.findById(id);

    if(productOptional.isPresent()) {
      Product product = productOptional.get();
      product.setName(productDTO.getName());
      product.setPrice(productDTO.getPrice());
      product.setMaker(productDTO.getMaker());
      productService.save(product);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {

    if(id != null) {
      productService.deleteById(id);
      return ResponseEntity.ok("Delete element");
    }
    return ResponseEntity.badRequest().build();
  }
}
