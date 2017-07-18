package condiciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import comparadores.IComparador;
import empresas.EmpresaRankeada;
import repositorios.RepositorioIndicadores;

public class CondicionTaxativa extends Condicion {
	
	private int valor;
	
	public CondicionTaxativa(RepositorioIndicadores indicadores, IComparador comparador, int valor) {
		/* Para el caso en el que la condicion requiera de un valor 
		 */
		super(indicadores, comparador);
		this.valor = valor;
	}
		
	public CondicionTaxativa(RepositorioIndicadores indicadores, IComparador comparador) {
		/* En ciertos casos la condicion Taxativa puede no requerir de valor alguno. 
		 * Por ejemplo cuando se quiere aplica el criterio Crecimiento.
		 */
		super(indicadores, comparador);
	}


	public List<EmpresaRankeada> aplicar(List<EmpresaRankeada> empresas) {
		List<EmpresaRankeada> empresas_a_invertir = empresas.stream()
			.filter(empresaRankeada -> getCriterio().aplicar(empresaRankeada.getEmpresa(), getValor(), getComparador())).collect(Collectors.toList());
		return new ArrayList<EmpresaRankeada>(empresas_a_invertir); 
	}

	public int getValor() {
		return valor;
	}

	public void setValue(int value) {
		this.valor = value;
	}
	
}
