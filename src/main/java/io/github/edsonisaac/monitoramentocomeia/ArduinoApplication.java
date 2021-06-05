package io.github.edsonisaac.monitoramentocomeia;

import io.github.edsonisaac.monitoramentocomeia.infraestrutura.exception.ObjectNotFoundException;
import io.github.edsonisaac.monitoramentocomeia.infraestrutura.service.Facade;
import io.github.edsonisaac.monitoramentocomeia.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ArduinoApplication implements CommandLineRunner {

	private final Facade facade;
	private final BCryptPasswordEncoder encoder;

	@Autowired
	public ArduinoApplication(Facade facade, BCryptPasswordEncoder encoder) {
		this.facade = facade;
		this.encoder = encoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(ArduinoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		checkDefaultUser();
	}

	private void checkDefaultUser () {

		try {
			Usuario usuario = facade.usuarioFindByUsuario("cooperativa");

			if (!encoder.matches("Rf0c4a>E", usuario.getSenha())) {
				saveDefaultUser(usuario);
			}
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