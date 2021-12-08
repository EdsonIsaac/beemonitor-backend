package io.github.edsonisaac.beemonitor;

import io.github.edsonisaac.beemonitor.infraestrutura.exception.ObjectNotFoundException;
import io.github.edsonisaac.beemonitor.infraestrutura.service.Facade;
import io.github.edsonisaac.beemonitor.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BeeMonitorApplication implements CommandLineRunner {

	private final Facade facade;
	private final BCryptPasswordEncoder encoder;

	@Autowired
	public BeeMonitorApplication(Facade facade, BCryptPasswordEncoder encoder) {
		this.facade = facade;
		this.encoder = encoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(BeeMonitorApplication.class, args);
	}

	@Override
	public void run(String... args) {
		checkDefaultUser();
	}

	private void checkDefaultUser () {

		try {
			saveDefaultUser(facade.usuarioFindByUsuario("cooperativa"));
		} catch (ObjectNotFoundException ex) {
			saveDefaultUser(new Usuario());
		}
	}

	private void saveDefaultUser (Usuario usuario) {

		usuario.setUsuario("cooperativa");
		usuario.setNome("Cooperativa");
		usuario.setSenha(encoder.encode("Rf0c4a>E"));

		facade.usuarioSave(usuario);
	}
}