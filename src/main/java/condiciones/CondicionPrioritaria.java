package condiciones;

import java.util.Comparator;
import java.util.List;

import comparadores.IComparador;
import empresas.Empresa;
import edu.emory.mathcs.backport.java.util.Collections;
import empresas.EmpresaRankeada;
import repositorios.RepositorioIndicadores;


public class CondicionPrioritaria extends Condicion {
	
	private int peso;
	
	public CondicionPrioritaria(RepositorioIndicadores indicadores, IComparador comparador, int peso) {
		super(indicadores, comparador);
		this.peso = peso;
	}
	
	@Override
	public List<EmpresaRankeada> aplicar(List<EmpresaRankeada> empresas) {
		
		ordenarSegunCriterio(empresas);
		
		return empresas;
	}

	private void aumentarRankings(List<EmpresaRankeada> empresas) {
		for(EmpresaRankeada empresa : empresas){
			
			/* De la siguiente manera calculamos el peso.
			 */
			
			empresa.aumentarRanking(getPeso() * (empresas.size() - empresas.indexOf(empresa)));
		}
	}
	
	private List<EmpresaRankeada> ordenarSegunCriterio(List<EmpresaRankeada> empresas) {
		
		empresas.sort((unaEmpresa, otraEmpresa) -> compararEmpresas(unaEmpresa, otraEmpresa));
		Collections.reverse(empresas);
		this.aumentarRankings(empresas);
		
		return empresas;
	}
	
	private int compararEmpresas(EmpresaRankeada unaEmpresa, EmpresaRankeada otraEmpresa){
		if(getCriterio().aplicar(unaEmpresa.getEmpresa(), getValor(otraEmpresa.getEmpresa()), getComparador()))
			return 1;
		else 
			return -1;
	}
	
	public int getPeso() {
		return peso;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public double getValor(Empresa unaEmpresa) {
		return this.getCriterio().calcular(unaEmpresa);
	}
}