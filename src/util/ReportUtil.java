package util;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings({"rawtypes", "unchecked"})
	public class ReportUtil implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

		public byte[] geraRelatorioPDF(List listaDados, String nomeRelatorio, ServletContext context) throws Exception {
			//CRIA A LISTA DE DADOS QUE VEM DO SQL'
			JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDados);
			
			String caminhoJasper = context.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
			
			
			JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashedMap(), jrbcds);
			
			return JasperExportManager.exportReportToPdf(impressoraJasper);
		}

}
