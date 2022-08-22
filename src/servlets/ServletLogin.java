package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModeloLogin;


@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository(); 
	

   
    public ServletLogin() {
    }

	
    // RECEBE OS DADOS PELA URL EM PARÂMETRO
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		} else {
			doPost(request, response);

		}
		
	}

	
	// RECEBE OS DADOS ENVIADOS PELO FORMULÁRIO 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("Login");
		String senha = request.getParameter("Senha");
		String url = request.getParameter("url");
		
		try {
			// CONDIÇÃO DE LOGIN 
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModeloLogin modelo = new ModeloLogin();
				modelo.setLogin(login);
				modelo.setSenha(senha);
				
				if (daoLoginRepository.validarAutenticacao(modelo)) {
					
					request.getSession().setAttribute("usuario", modelo.getLogin());
					request.getSession().setAttribute("perfil", modelo.getPerfil());
					
					 if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
					
				}else {
					// REDIRECIONANDO PARA PRIMEIRA PÁGINA SE LOGIN OU SENHA FOR INCORRETO
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", " Login e/ou Senha incorreto ");
					redirecionar.forward(request, response);
				}
				
			} else {
				// REDIRECIONANNDO PARA PRIMEIRA PÁGINA SE LOGIN OU SENHA FOR INCORRETO
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", " Login e/ou Senha incorreto ");
				redirecionar.forward(request, response);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("paginaerro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}
