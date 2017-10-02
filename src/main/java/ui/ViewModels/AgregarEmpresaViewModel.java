package ui.ViewModels;

import java.util.ArrayList;

import org.uqbar.commons.utils.Observable;

import domain.Archivo;
import domain.Cuenta;
import empresas.Empresa;
import manejadoresArchivo.ManejadorDeArchivoEmpresas;
import repositorios.RepositorioEmpresas;
import validadores.ValidadorEmpresa;

@Observable
public class AgregarEmpresaViewModel {

	public String empresa;
	private Archivo file;
	private ManejadorDeArchivoEmpresas manejador;
	private ValidadorEmpresa validador;
	private RepositorioEmpresas repoEmpresas;
	
	public AgregarEmpresaViewModel(Archivo aFile){
		file = aFile;
		validador = new ValidadorEmpresa();
	    manejador = new ManejadorDeArchivoEmpresas(file.getRuta());
	    setRepoEmpresas(new RepositorioEmpresas(file.getRuta()));
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		validador.validarNombre(empresa, getRepoEmpresas());
		 this.empresa = empresa;
	}
	
	public void agregarEmpresa(){
		Empresa myEmpresa = new Empresa(empresa);
		myEmpresa.setCuentas(new ArrayList<Cuenta>());
		getRepoEmpresas().agregar(myEmpresa);
		//manejador.agregarEmpresaAlArchivo(myEmpresa);
	}

	public RepositorioEmpresas getRepoEmpresas() {
		return repoEmpresas;
	}

	public void setRepoEmpresas(RepositorioEmpresas repoEmpresas) {
		this.repoEmpresas = repoEmpresas;
	}
	
}
