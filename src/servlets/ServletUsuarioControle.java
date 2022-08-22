package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModeloLogin;
import util.ReportUtil;

@MultipartConfig
@WebServlet( urlPatterns = {"/ServletUsuarioControle"})
public class ServletUsuarioControle extends ServletGeneric {
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
   
    public ServletUsuarioControle() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao  = request.getParameter("acao");
		
			// DELETAR USUÁRIO POR AJAX
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String idUser = request.getParameter("id"); 
				daoUsuarioRepository.deletarUsuario(idUser);
				
				response.getWriter().write("Usuário excluído COM SUCESSO");
				
				
			// CONSULTAR USUÁRIO PELO MODAL 	
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUser")) {
				 String nomeBusca = request.getParameter("nomeBusca");
				 
				 List<ModeloLogin> dadosJson =  daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
				 
				 ObjectMapper mapper = new ObjectMapper();
				 
				 String json = mapper.writeValueAsString(dadosJson);
				 response.getWriter().write(json);
				
				 
			// CONSULTAR USUÁRIO PELO ID 
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				 String id = request.getParameter("id");
				 
				ModeloLogin modeloLogin = daoUsuarioRepository.consultaUsuarioID(id, super.getUserLogado(request));
				
				List<ModeloLogin> modeloLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modeloLogins", modeloLogins);
				 
				request.setAttribute("msg", "Usuário encontrado");
				request.setAttribute("modolLogin", modeloLogin);
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
				
			// CONSULTAR USUÁRIO PARA EXIBIR ABAIXO DO FORMULÁRIO
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				List<ModeloLogin> modeloLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				
				request.setAttribute("msg", "Usuário carregados");
				request.setAttribute("modeloLogins", modeloLogins);
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			// PAGINAÇÃO DE 5 EM 5 NA TELA DE FORMULÁRIO 
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModeloLogin> modeloLogins = daoUsuarioRepository.consultaUsuarioListPaginado(this.getUserLogado(request), offset);
				
				request.setAttribute("modeloLogins", modeloLogins);
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			// EMITIR O RELATÓRIO 
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if (dataInicial == null || dataInicial.isEmpty() 
						 && dataFinal == null || dataFinal.isEmpty()) {
					 
					 request.setAttribute("listaUser", daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request)));
					 
				 }else {
					
					 request.setAttribute("listaUser", daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request), dataInicial, dataFinal));
					 
				 }
				
				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("principal/reluser.jsp").forward(request, response);
			}
			
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				List<ModeloLogin> modeloLogins = null;
				
					if (dataInicial == null || dataInicial.isEmpty() 
						 && dataFinal == null || dataFinal.isEmpty()) {
						 
						modeloLogins = daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request));
						 
					 }else {
						 modeloLogins = daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request), dataInicial, dataFinal);
						 
					 }
					
				byte[] relatorio = new ReportUtil().geraRelatorioPDF(modeloLogins, "rel-user.jsp", request.getServletContext());
					
				response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
				response.getOutputStream().write(relatorio);
			}
			
			else {
				List<ModeloLogin> modeloLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modeloLogins", modeloLogins);
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("paginaerro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	// RECEBENDO OS DADOS DO FORMULÁRIO E CRIANDO OBJETO 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String msg = " Operação Realizada com sucesso!";
			
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String perfil = request.getParameter("perfil");
		String sexo = request.getParameter("sexo");
		String dataNascimento = request.getParameter("dataNascimento");
		
		ModeloLogin modeloLogin = new ModeloLogin();
		modeloLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		modeloLogin.setNome(nome);
		modeloLogin.setEmail(email);
		modeloLogin.setLogin(login);
		modeloLogin.setSenha(senha);
		modeloLogin.setPerfil(perfil);
		modeloLogin.setSexo(sexo);
		
		modeloLogin.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));
		
		if (ServletFileUpload.isMultipartContent(request)) {
			
			Part part = request.getPart("fileFoto");
			
			if (part.getSize() > 0) {
				byte[] foto = IOUtils.toByteArray(part.getInputStream());
				String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," +  new Base64().encodeBase64String(foto);
				
				modeloLogin.setFotoUser(imagemBase64);
				modeloLogin.setExtensaoFoto(part.getContentType().split("\\/")[1]);
			}
			
		} 
		
		if (daoUsuarioRepository.validarLogin(modeloLogin.getLogin()) && modeloLogin.getId() == null) {
			msg = "  Este usuário já existe !";
		} else {
			modeloLogin = daoUsuarioRepository.gravarUsuario(modeloLogin, super.getUserLogado(request));
		}
		
		List<ModeloLogin> modeloLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
		request.setAttribute("modeloLogins", modeloLogins);
		
		// MANTER OS DADOS NA TELA APÓS SALVAR
		request.setAttribute("msg", msg);
		request.setAttribute("modolLogin", modeloLogin);
		request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("paginaerro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}
	
	

}
