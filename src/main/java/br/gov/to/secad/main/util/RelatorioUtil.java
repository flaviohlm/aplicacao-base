package br.gov.to.secad.main.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.omnifaces.util.Faces;

@SuppressWarnings({"unchecked", "Duplicates"})
public class RelatorioUtil implements Serializable {

    // Diretórios de relatórios
    public static final String DIR_UNICET = "/report/";

    private String jasperFilePath;
    private Map<String, Object> parametros;
    private String reportName;
    private Connection con;
    private boolean redirect;
    private String sessionId;

    /**
     * Gerar um relatório, com datasource ou não, escolhendo o tipo PDF ou XLSX e se é necessário redirecionar para
     * o arquivo gerado.
     *
     * @param jasperFilePath caminho do arquivo .jasper
     * @param parametros     lista de parâmetros a ser passados para o relatório
     * @param reportName     nome do relatório
     * @param con            conexão DataSource, pode ser null
     * @param fileType       tipo do arquivo (PDF ou XLSX)
     * @param redirect       true = redireciona para o caminho do arquivo gerado e o exibe
     *                       false = não redireciona
     * @return o caminho do arquivo gerado, caso precise busca-ló e ler o byte
     */
    public String createReport(String jasperFilePath, Map<String, Object> parametros, String reportName,
                                      Connection con, String fileType, boolean redirect)
            throws JRException, IOException, SQLException {

        this.jasperFilePath = jasperFilePath;
        this.parametros = parametros;
        this.reportName = StringUtil.removeAcentos(DownloadUploadUtil.removeExtension(reportName));
        this.con = con;
        this.redirect = redirect;

        if (fileType.equalsIgnoreCase("PDF")) {
            return startPDF();
        } else if (fileType.equalsIgnoreCase("XLSX")) {
            return startXLSX();
        } else {
            // Não suportado
            return null;
        }
    }

    /**
     * Processo de geração de relatórios do tipo PDF
     *
     * @return o caminho do arquivo gerado
     */
    private String startPDF() throws SQLException, IOException, JRException {
        String fileSource;
        // Se não for passado uma conexão, é um relatório sem DataSource
        if (con == null) {
            fileSource = generatePdfNoDataSource();
        } else {
            fileSource = generatePdf();
        }
        // Redirecionar
        if (redirect) {
            String redirectUrl = FileHelper.tempResourceRequestPath() + Faces.getSessionId() + "/" + reportName + ".pdf";
            Faces.redirect(redirectUrl);
        }
        return fileSource;
    }

    /**
     * Processo de geração de relatórios do tipo XLSX
     *
     * @return o caminho do arquivo gerado
     */
    private String startXLSX() throws SQLException, IOException, JRException {
        String fileSource;
        // Se não for passado uma conexão, é um relatório sem DataSource
        if (con == null) {
            fileSource = "";
            // TODO criar método XLSX sem conexão
        } else {
            fileSource = generateXlsx();
        }
        // Redirecionar
        if (redirect) {
            String redirectUrl = FileHelper.tempResourceRequestPath() + sessionId + "/" + reportName + ".xlsx";
            Faces.redirect(redirectUrl);
        }
        return fileSource;
    }

    /**
     * Gerar um relatório do tipo PDF sem DataSource
     *
     * @return o caminho do arquivo gerado
     */
    private String generatePdfNoDataSource() throws JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(Faces.getRealPath(jasperFilePath), parametros, new JREmptyDataSource(1));

        sessionId = Faces.getSessionId();
        String source = FileHelper.tempResourceRealPath();
        source += sessionId + File.separator;

        //Create directory
        File outputPath = new File(source);
        outputPath.mkdirs();

        //Create pdf report
        JasperExportManager.exportReportToPdfFile(jasperPrint, source + "" + reportName + ".pdf");
        return source + reportName + ".pdf";
    }

    /**
     * Gerar um relatório do tipo PDF com conexão DataSource
     *
     * @return o caminho do arquivo gerado
     * @throws SQLException .
     */
    private String generatePdf() throws JRException, SQLException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(Faces.getRealPath(jasperFilePath), parametros, con);
        con.close();

        sessionId = FileHelper.mySessionID();
        String source = FileHelper.tempResourceRealPath();
        source += sessionId + File.separator;

        //Create file
        File outputPath = new File(source);
        outputPath.mkdirs();

        //Create pdf report
        JasperExportManager.exportReportToPdfFile(jasperPrint, source + "" + reportName + ".pdf");
        return source + reportName + ".pdf";
    }

    /**
     * Gerar um relatório do tipo XLSX com conexão DataSource
     *
     * @return o caminho do arquivo gerado
     * @throws SQLException ;
     */
    private String generateXlsx() throws SQLException, JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(Faces.getRealPath(jasperFilePath), parametros, con);
        con.close();

        JRXlsxExporter xlsxExporter = new JRXlsxExporter();

        sessionId = FileHelper.mySessionID();
        String source = FileHelper.tempResourceRealPath();
        source += sessionId + File.separator;

        //Create file
        File outputPath = new File(source);
        outputPath.mkdirs();

        // Output File
        File outputFile = new File(source + reportName + ".xlsx");

        xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setIgnoreCellBorder(false);
        xlsxExporter.setConfiguration(configuration);
        xlsxExporter.exportReport();

        return source + reportName + ".xlsx";
    }
}
