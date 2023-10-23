package com.integrador.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.domain.Usuario;
import com.integrador.service.UsuarioService;
import com.integrador.service.dto.usuario.UsuarioRequestDto;
import com.integrador.service.dto.usuario.UsuarioResponseDto;
import com.integrador.service.dto.usuarioCuenta.UsuarioCuentaRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	@Autowired
	private  UsuarioService usuarioService;
	@GetMapping("")
    public List<UsuarioResponseDto> findAll(){
        return this.usuarioService.findAll();
    }

	
	 @GetMapping("/{id}")
	   public ResponseEntity<?> getById(@PathVariable Long id){
	        try{
	            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Usuario inexistente");
	        }
	        
	  }

    //ver si funciona
    @PostMapping("")
    public ResponseEntity<?> save( @RequestBody @Validated UsuarioRequestDto request ){
        try {
        	return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(request));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }
    
  //ver si funciona
    @PostMapping("/asociarCuenta")
    public ResponseEntity<?> asociarCuenta( @RequestBody @Validated UsuarioCuentaRequestDto request ){
        try {
        	return ResponseEntity.status(HttpStatus.OK).body(usuarioService.asociarCuenta(request));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }
    
    @DeleteMapping("/quitarCuenta")
    public ResponseEntity<?> quitarCuenta(@RequestBody @Validated UsuarioCuentaRequestDto request){
        try{
            this.usuarioService.quitarCuenta(request);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.quitarCuenta(request));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. revise los campos ingresados");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            this.usuarioService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente usuario con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el usuario con id: " + id);
        }
    }
    
    //chequear
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated UsuarioRequestDto request) {
        try {
            Usuario usuario = usuarioService.update(id, request);
            UsuarioResponseDto response = new UsuarioResponseDto(usuario);

            return ResponseEntity.status(HttpStatus.OK).body(response);
          
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario con el ID proporcionado.");
        }
    }
    





    
//como el ejemplo del libro, tira error
//  @GetMapping("/{id}")
//  public ResponseEntity<?> findById( @PathVariable Long id ){
//  	Optional<UsuarioResponseDto> usuario = Optional.ofNullable(this.usuarioService.findById(id));
//  	if(usuario == null) {
//  		return new ResponseEntity<> (HttpStatus.NOT_FOUND);
//  	}
//      return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
//  }
  
//como lo teniamos antes
//  @PostMapping("")
//  public ResponseEntity<UsuarioResponseDto> save( @RequestBody @Validated UsuarioRequestDto request ){
//      final var result = this.usuarioService.save( request );
//      return ResponseEntity.accepted().body( result );
//  }

    
    


}
