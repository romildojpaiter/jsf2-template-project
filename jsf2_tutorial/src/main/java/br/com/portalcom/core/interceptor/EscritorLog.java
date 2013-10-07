package br.com.portalcom.core.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@InterceptorLog
public class EscritorLog {
	
	@AroundInvoke
	public Object escreverLog(InvocationContext contexto) throws Exception{
		
		String nomeMetodoIntercepado = contexto.getMethod().getName();
		System.out.println("Executando o Metodo " + nomeMetodoIntercepado + " da classe " +
				contexto.getTarget());
		return contexto.proceed();
	}

}
