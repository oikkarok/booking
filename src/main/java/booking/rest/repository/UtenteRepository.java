package booking.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.rest.entities.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    public Optional<Utente> findByNome(String nome);

}
