package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter (urlPatterns = {"/principal/*"}) // INTERCEPTA TODAS AS REQUISIÇÕES QUE VEM DO PRINCIPAL 
public class FilterAutenticacao implements Filter {
	
	private static Connection connection;
   
    public FilterAutenticacao() {
    }

	
    // ENCERRA OS PROCESSOS QUANDO O SERVIDOR É PARADO
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// PEGA AS REQUISIÇÕES E RESPONDE NO SISTEMA
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	try {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		String urlAutenticar = req.getServletPath();
		
		if (usuarioLogado == null && !urlAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlAutenticar);
			request.setAttribute("msg", "Login NÃO REALIZADO");
			redirecionar.forward(request, response);
			return;
			
		} else {
			chain.doFilter(request, response);
			
		}	
		connection.commit();
		
	} catch (Exception e) {
		e.printStackTrace();
		RequestDispatcher redirecionar = request.getRequestDispatcher("paginaerro.jsp");
		request.setAttribute("msg", e.getMessage());
		redirecionar.forward(request, response);
		
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
		
	}

	
	// INICIA OS PROCESSOS QUANDO SUBIMOS O PROJETO NO SERVIDOR 
	public void init(FilterConfig fConfig) throws ServletException {
		connection = ConnectionBanco.getConnection();
		
	}

}
