package com.example.rest.controller;

import com.example.rest.dto.MakerDTO;
import com.example.rest.entities.Maker;
import com.example.rest.service.IMakerService;
import com.example.rest.service.implement.MakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/maker")
public class MakerController {

  @Autowired
  private IMakerService iMakerService;
  @Autowired
  private MakerService makerService;

  @GetMapping("/find/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Optional<Maker> makerOptional = iMakerService.findById(id);

    if(makerOptional.isPresent()){
      Maker maker = makerOptional.get();

      MakerDTO makerDTO = MakerDTO.builder()
          .id(maker.getId())
          .name(maker.getName())
          .productList(maker.getProductList())
          .build();

      return ResponseEntity.ok(makerDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/findAll")
  public ResponseEntity<?> findAll(){
    List<MakerDTO> makerlist = makerService.findAll()
            .stream()
            .map(maker -> MakerDTO.builder()
                    .id(maker.getId())
                    .name(maker.getName())
                    .productList(maker.getProductList())
                    .build())
            .toList();
    return ResponseEntity.ok(makerlist);
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody MakerDTO makerDTO) throws URISyntaxException {
    if(makerDTO.getName().isBlank()) {
      return ResponseEntity.badRequest().build();
    }
    makerService.save(Maker.builder()
            .name(makerDTO.getName())
            .build());
    return ResponseEntity.created(new URI("/api/maker/save")).build();
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateMaker(@PathVariable Long id, @RequestBody MakerDTO makerDTO) {

    Optional<Maker> makerOptional = iMakerService.findById(id);

    if(makerOptional.isPresent()) {
      Maker maker = makerOptional.get();
      maker.setName(makerDTO.getName());
      makerService.save(maker);
      return ResponseEntity.ok("Update Request");
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    if (id != null) {
      makerService.deleteById(id);
      return ResponseEntity.ok("Delete Request");
    }
    return ResponseEntity.badRequest().build();
  }
 }
