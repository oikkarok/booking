package booking.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import booking.rest.dto.UtenteDto;
import booking.rest.security.JwtProvider;
import booking.rest.security.TokenHandler;
import booking.rest.service.UtenteService;

@RestController 
@RequestMapping("public/authentication")
public class AuthenticationController {

    @Autowired
    UtenteService utenteService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody UtenteDto dto){
	
	utenteService.mapAndSaveUtente(dto);
	
	return ResponseEntity.noContent().build();	
    }

    @PostMapping
    public ResponseEntity<TokenHandler> signIn(@RequestBody UtenteDto input) {
	
	UtenteDto utente = utenteService.getByNome(input.getValue());
	
	if (utente == null) {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	List<Entry> claimMap = new ArrayList<>();
	claimMap.add(utente);
	String jwt = JwtProvider.createJwt(utente.getNome(), claimMap);
	
	TokenHandler handler = new TokenHandler();
	handler.setToken(jwt);
		
	return ResponseEntity.ok(handler);
    }
}
