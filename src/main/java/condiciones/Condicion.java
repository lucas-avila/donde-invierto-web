package condiciones;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import comparadores.Comparador;
import criterios.Criterio;
import empresas.EmpresaRankeada;
import repositorios.RepositorioIndicadores;

@Entity
@Table(name="Condiciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipo")
public abstract class Condicion {
	
	@Id@GeneratedValue
	long id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "criterio")
	private Criterio criterio;
	@Transient
	private RepositorioIndicadores repoIndicadores;
	@Enumerated
	@Column(name = "comparador")
	private Comparador comparador;
	
	public Condicion(){}
	
	public Condicion(RepositorioIndicadores indicadores, Comparador comparador){
		this.setRepoIndicadores(indicadores);
		this.comparador = comparador;
	}
	
	public abstract List<EmpresaRankeada> aplicar(List<EmpresaRankeada> empresas);

	public RepositorioIndicadores getRepoIndicadores() {
		return repoIndicadores;
	}

	void setRepoIndicadores(RepositorioIndicadores repoIndicadores) {
		this.repoIndicadores = repoIndicadores;
	}

	public Criterio getCriterio() {
		return criterio;
	}

	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}

	public Comparador getComparador() {
		return comparador;
	}

	void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}
	
}
