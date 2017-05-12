package es.unileon.ulebankoffice.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClienteTest {

	private static Validator validator;
	private Cliente cliente;
	private Set<ConstraintViolation<Cliente>> constraintViolations;

	@BeforeClass
	public static void setUpClass() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente();
	}

	@Test
	public void testName() {
		assertNull(cliente.getName());
		cliente.setName("NombreCliente");
		assertThat(cliente.getName(), is("NombreCliente"));

	}

	@Test
	public void testLastname() {
		assertNull(cliente.getLastName());
		cliente.setLastname("LastNameCliente");
		assertThat(cliente.getLastName(), is("LastNameCliente"));
	}

	@Test
	public void testEmail() {
		assertNull(cliente.getEmail());
		cliente.setEmail("email@example.eg");
		assertThat(cliente.getEmail(), is("email@example.eg"));
	}

	@Test
	public void testFechaNacimiento() {
		assertNull(cliente.getFechaNacimiento());
		cliente.setFechaNacimiento("1994-12-05");
		assertThat(cliente.getFechaNacimiento(), is("1994-12-05"));
	}

	@Test
	public void testDireccion() {
		assertNull(cliente.getDireccion());
		cliente.setDireccion(new ArrayList<Direccion>());
		assertThat(cliente.getDireccion().size(), is(0));
		cliente.addDireccion(new Direccion());
		assertThat(cliente.getDireccion().size(), is(1));
	}

	@Test
	public void testDni() {
		assertNull(cliente.getDni());
		cliente.setDni("x5526828c");
		assertThat(cliente.getDni(), is("x5526828c"));
	}

	@Test
	public void testToString() {
		assertThat(cliente.toString(),
				is("Cliente [name=null, lastname=null, email=null, fechaNacimiento=null, dni=null, direccion=null]"));
	}

	@Test
	public void testValidator() {
		cliente.setDireccion(new ArrayList<Direccion>());
		cliente.setDni("x5526828C");
		cliente.setEmail("email");
		cliente.setFechaNacimiento("fecha");
		cliente.setLastname("lastname");
		cliente.setName("name");
		
		validate();
		assertThat(constraintViolations.size(), is(0));
		
		cliente.setDni("");
		validate();
		assertThat(constraintViolations.size(), is(1));
		assertThat(constraintViolations.iterator().next().getPropertyPath().toString(), is("dni"));
		cliente.setDni("x5526828C");
		validate();
		assertThat(constraintViolations.size(), is(0));
		cliente.setDni("                           ");
		validate();
		assertThat(constraintViolations.size(), is(1));
		cliente.setDni("x");
		
		
		cliente.setEmail("");
		validate();
		assertThat(constraintViolations.size(), is(1));
		assertThat(constraintViolations.iterator().next().getPropertyPath().toString(), is("email"));
		
		cliente.setEmail("      ");
		validate();
		assertThat(constraintViolations.size(), is(1));
		assertThat(constraintViolations.iterator().next().getPropertyPath().toString(), is("email"));
		
		cliente.setEmail("email");
		validate();
		assertThat(constraintViolations.size(), is(0));
		
		cliente.setFechaNacimiento("");
		validate();
		assertThat(constraintViolations.size(), is(1));
		assertThat(constraintViolations.iterator().next().getPropertyPath().toString(), is("fechaNacimiento"));
		
		cliente.setFechaNacimiento("   ");
		validate();
		assertThat(constraintViolations.size(), is(1));
		assertThat(constraintViolations.iterator().next().getPropertyPath().toString(), is("fechaNacimiento"));

		cliente.setFechaNacimiento("fecha");
		validate();
		assertThat(constraintViolations.size(), is(0));

		cliente.setLastname("");
		validate();
		assertThat(constraintViolations.size(), is(1));
		assertThat(constraintViolations.iterator().next().getPropertyPath().toString(), is("lastName"));
	
		cliente.setLastname("    ");
		validate();
		assertThat(constraintViolations.size(), is(1));
		assertThat(constraintViolations.iterator().next().getPropertyPath().toString(), is("lastName"));
		
		cliente.setLastname("lastNAme");
		validate();
		assertThat(constraintViolations.size(), is(0));

		cliente.setName("");
		validate();
		assertThat(constraintViolations.size(), is(1));
		assertThat(constraintViolations.iterator().next().getPropertyPath().toString(), is("name"));
		
		cliente.setName("          ");
		validate();
		assertThat(constraintViolations.size(), is(1));
		assertThat(constraintViolations.iterator().next().getPropertyPath().toString(), is("name"));
		
		cliente.setName("name");
		validate();
		assertThat(constraintViolations.size(), is(0));
		
	}
	
	private void validate(){
		constraintViolations = validator.validate(cliente);
	}
}
