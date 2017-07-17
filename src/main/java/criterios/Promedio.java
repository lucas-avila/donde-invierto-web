package criterios;

import condiciones.Condicion;
import condiciones.CondicionTaxativa;
import condiciones.CondicionPrioritaria;
import domain.Indicador;
import empresas.Empresa;

public class Promedio extends Criterio{

	
	public Promedio(Indicador indicador) {
		super(indicador);
	}
	
	public double promediar(Empresa unaEmpresa, Condicion unaCondicion) {
		double sumatoria = 0;
		sumatoria = unaEmpresa.getCuentas().stream().mapToDouble( x -> this.getIndicador().aplicarIndicador
				(x.getPeriodo(), unaEmpresa, unaCondicion.getRepoIndicadores())).sum();
		return sumatoria / unaEmpresa.getCuentas().size();
	}

	@Override
	public Boolean aplicarTaxativa(Empresa unaEmpresa, CondicionTaxativa unaCondicion) {
		double promedio = promediar(unaEmpresa, unaCondicion);
		
		return  unaCondicion.getComparador().comparar(promedio, unaCondicion.getValue());
	}
	
	@Override
	public Boolean aplicarPrioritaria(Empresa unaEmpresa, Empresa otraEmpresa, CondicionPrioritaria condicion_prioritaria) {
		
		double promedio1, promedio2 = 0;
		
		promedio1 = promediar(unaEmpresa, condicion_prioritaria);
		promedio2 = promediar(otraEmpresa, condicion_prioritaria);
		
		return condicion_prioritaria.getComparador().comparar(promedio1, promedio2);
	}
}
