package br.com.springboot.curso.jdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso.jdev.model.Usuario;
import br.com.springboot.curso.jdev.repository.UsuarioRepository;


@RestController
public class GreetingsController {
    
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso SpringBoot API: " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}" , method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario);
    	
    	
    	return "Olá Mundo " + nome;
    	
    }
    
    @GetMapping(value = "listatodos") // Primeiro método de API
    @ResponseBody // Retorna os dados para o corpo da resposta (Json)
    public ResponseEntity<List<Usuario>> listaUsuario() {
    	List<Usuario> usuarios = usuarioRepository.findAll(); // Executa a consulta no banco de dados
    	    
    	 return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // Retorna a lista em Json
    	    
    }
    
    // Salvar
    @PostMapping(value = "salvar") // mapeia a URL
    @ResponseBody // Descrição da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario ) { // recebe os dados para salvar
    	
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    //  Atualizar
    @PutMapping(value = "atualizar") // mapeia a URL
    @ResponseBody // Descrição da resposta
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario ) { // recebe os dados para salvar
    	
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não informado para atualização", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    // Excluir
    @DeleteMapping(value = "delete") // mapeia a URL
    @ResponseBody // Descrição da resposta
    public ResponseEntity<String> delete(@RequestParam Long iduser) { // recebe os dados para salvar
    	
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Usuário excluído com sucesso", HttpStatus.OK);
    }
    
    //Buscar Por Id
    @GetMapping(value = "buscaruserid") // mapeia a URL
    @ResponseBody // Descrição da resposta
    public ResponseEntity<Usuario> buscaruserid(@RequestParam (name = "iduser")  Long iduser) { // recebe os dados para consultar
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
  //Buscar Por Nome
    @GetMapping(value = "buscarPorNome") // mapeia a URL
    @ResponseBody // Descrição da resposta
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam (name = "name")  String name) { // recebe os dados para consultar
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
    
    
    
  
    
    
    
    
   
    
}
