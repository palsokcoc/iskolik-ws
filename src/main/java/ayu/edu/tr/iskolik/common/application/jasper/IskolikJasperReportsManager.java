package ayu.edu.tr.iskolik.common.application.jasper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * A utility class that provides static methods for printing, displaying and
 * saving {@link IskolikReport} objects. Currently supported formats are HTML,PDF
 * and XLS.
 * 
 * <p>
 * A bug in sitemesh 2.2.x makes it incompatible with Jasper Reports. If you use
 * sitemesh in your project, make sure it is version 2.3 or later.
 * </p>
 * 
 * @author SAGSISO
 * 
 * @version 2.7
 * 
 * @see IskolikReport
 * 
 * @since March 2010
 * 
 */
public class IskolikJasperReportsManager {

	private static final Log logger = LogFactory
			.getLog(IskolikJasperReportsManager.class);

	private static Method reportLoadMethod;

	static {
		initialize();
	}

	private IskolikJasperReportsManager() {
	}

	/**
	 * {@link JRLoader#loadObjectFromFile(String)} metodunu var mı diye kontrol
	 * eder. Varsa {@link #reportLoadMethod} işine kaydeder. Amaç bu metodun
	 * olmadığı eski JasperReports sürümlerinde de çalışabilmeyi sağlamaktır.
	 */
	private static void initialize() {
		try {
			reportLoadMethod = JRLoader.class.getMethod("loadObjectFromFile",
					String.class);
		} catch (SecurityException e) {
			logger.warn("JRLoader.loadObjectFromFile(String) metoduna erişilemedi");
		} catch (NoSuchMethodException e) {
			logger.warn("JRLoader.loadObjectFromFile(String) metodu bulunamadı");
		}
	}

	/**
	 * Varsa {@link JRLoader#loadObjectFromFile(String)} metodu ile, yoksa
	 * {@link JRLoader#loadObject(String))} metodu ile {@link JasperReport}
	 * nesnesini yaratır. Amaç eski JasperReports sürümlerinde de çalışabilmeyi
	 * sağlamaktır.
	 * 
	 * @param absolutePath
	 *            Raport dosya yolu
	 * @return {@link JasperReport} nesnesi
	 * @throws JRException
	 */
	private static JasperReport loadReport(String absolutePath)
			throws JRException {
		if (reportLoadMethod == null) {
			JasperDesign jasperDesign = JRXmlLoader.load(new File(absolutePath));
			return JasperCompileManager.compileReport(jasperDesign);
		} else
			try {
				return (JasperReport) reportLoadMethod.invoke(null,
						absolutePath);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				Throwable target = e.getTargetException();
				if (target instanceof JRException)
					throw (JRException) target;
				else
					throw (RuntimeException) target;
			}
	}

	public static void getReport(IskolikReport report) throws IOException, JRException {
		JRPdfExporter exporter = new JRPdfExporter();

		JasperPrint jasperPrint = generateJasperPrint(report);
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("employeeReport.pdf"));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
//		exportConfig.setMetadataAuthor("baeldung");
//		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");

		exporter.setConfiguration(reportConfig);

		exporter.exportReport();
	}

	/**
	 * Displays the IskolikReport object in the browser. If the type of the report
	 * is not HTML, the report is tried to be displayed inline in the browser
	 * rather than externally running the relevant application and opening the
	 * report in that application. Once this method is called, no change should
	 * be made on the response object. This means forwarding to a page via
	 * {@link ActionForward} or redirecting to a URL must be avoided. Otherwise,
	 * 
	 * <pre>
	 *            	{@link IllegalStateException IllegalStateException}: Cannot forward after response has been committed
	 * </pre>
	 * 
	 * exception is thrown. If, in addition, sitemesh is active on the forwarded
	 * page,
	 * 
	 * <pre>
	 *            	{@link IllegalStateException IllegalStateException}: response.getWriter() called after response.getOutputStream()
	 * </pre>
	 * 
	 * exception is thrown.
	 * 
	 * <p>
	 * <i>Supported Formats</i>: <b>HTML,PDF,XLS</b>.
	 * </p>
	 * 
	 * <b>Sample code:</b>
	 * 
	 * <pre>
	 * <code>
	 *  public class ReportAction extends DispatchAction {
	 *        ...
	 *   
	 *      public void displayReport(ActionMapping mapping, ActionForm form,
	 *          HttpServletResponse response)
	 *          throws Exception {
	 * 
	 *          IskolikReport report = new IskolikReport();
	 *          report.setFilePath(&quot;/iskolik/doktor/liste/DoktorHastaSayisi.jasper&quot;);
	 *          report.setReportFormat(&quot;PDF&quot;);
	 *          ...
	 *          List dataList = doktorDAO.getDoktotrHastaSayisi();
	 *          report.setDataList(dataList);
	 *          ...
	 * 	
	 *          TcmbJasperReportsManager.displayReport(response,report);
	 *      }
	 *  }
	 * </code>
	 * </pre>
	 * 
	 * @param response
	 *            an HttpServletResponse object
	 * @param report
	 *            a TcmbReport object, which will be displayed
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JRException
	 */
	public static void displayReport(HttpServletResponse response,
			IskolikReport report) throws FileNotFoundException, IOException,
			JRException {

		String reportFormat = report.getReportFormat();

		JasperPrint jasperPrint = generateJasperPrint(report);

		/* get exporter */
		JRExporter exporter = getExporter(response, jasperPrint, reportFormat);
		if (reportFormat.equalsIgnoreCase("PDF")) {
			/* set response header */
			response.setContentType("application/pdf");
			String fileName = generateFileName(report.getFilePath(), "pdf");
			response.setHeader("Content-Disposition", "inline;filename="
					+ fileName);
		} else if (reportFormat.equalsIgnoreCase("XLS")) {
			/* set response header */
			response.setContentType("application/vnd.ms-excel");
			String fileName = generateFileName(report.getFilePath(), "xls");
			response.setHeader("Content-Disposition", "inline;filename="
					+ fileName);
		} else if (reportFormat.equalsIgnoreCase("HTML")) {
			/* set response header */
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
		} else {
			throw new IllegalArgumentException("Unsupported format: "
					+ reportFormat.toUpperCase());
		}

		/* export report */
		exporter.exportReport();
	}

	/**
	 * Generates a pdf with some javascript code that prints the pdf after it is
	 * loaded in the browser, and opens that pdf inline in the browser so that
	 * the print dialog opens automatically.
	 * 
	 * Another handy way to print reports is to use the
	 * <code>&lt;tcmb:printReport&gt;</code> tag, which opens the print dialog
	 * <u>without</u> displaying the report. <br>
	 * A typical usage of the tag is as follows: </br>
	 * 
	 * <pre>
	 * 	<code>
	 * 		&lt;tcmb:printReport
	 * 			baseUrl = '/SAGSISO/doktor/doktorRapor.do'
	 * 			queryString = 'method=print&amp;reportName=report1'
	 * 		/&gt;
	 * </code>
	 * </pre>
	 * 
	 * <p>
	 * <i>Supported Formats</i>: <b>All</b>.
	 * </p>
	 * 
	 * @param response
	 *            an HttpServletResponse object
	 * @param report
	 *            a TcmbReport object, which is to be printed
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	public static void printReport(HttpServletResponse response,
			IskolikReport report) throws IOException, JRException {

		/*
		 * Since all the formats except pdf are likely to raise problems,
		 * especially in image embedding, the type of the report is changed to
		 * pdf temporarily.
		 */
		String reportFormat = report.getReportFormat();
		report.setReportFormat("pdf");
		JasperPrint jasperPrint = generateJasperPrint(report);

		/* set response header */
		response.setContentType("application/pdf");
		String fileName = generateFileName(report.getFilePath(), "pdf");
		response.setHeader("Content-Disposition", "inline;filename=" + fileName);

		/* configure exporter */
		JRExporter exporter = getExporter(response, jasperPrint, "PDF");
		exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT,
				"this.print({bUI: true, bSilent: true, bShrinkToFit: true});");

		/* restore report format */
		report.setReportFormat(reportFormat);

		/* export report */
		exporter.exportReport();
	}

	/**
	 * Displays the Open/Save dialog for the TcmbReport object. Open command
	 * opens the report in an appropriate application externally. Save command
	 * saves the report to the client file system. Once this method is called,
	 * no change should be made on the response object. This means forwarding to
	 * a page via {@link ActionForward} or redirecting to a URL must be avoided.
	 * Otherwise,
	 * 
	 * <pre>
	 *            	{@link IllegalStateException IllegalStateException}: Cannot forward after response has been committed
	 * </pre>
	 * 
	 * exception is thrown. If, in addition, sitemesh is active on the forwarded
	 * page,
	 * 
	 * <pre>
	 *            	{@link IllegalStateException IllegalStateException}: response.getWriter() called after response.getOutputStream()
	 * </pre>
	 * 
	 * exception is thrown.
	 * 
	 * <p>
	 * <i>Supported Formats</i>: <b>HTML,PDF,XLS</b>.
	 * </p>
	 * 
	 * <br>
	 * Sample code: </br>
	 * 
	 * <pre>
	 * <code>
	 *  public class ReportAction extends DispatchAction {
	 *        ...
	 *   
	 *      public void saveReportOnClient(ActionMapping mapping, ActionForm form,
	 *          HttpServletRequest request, HttpServletResponse response)
	 *          throws Exception {
	 * 
	 *          TcmbReport report = new TcmbReport();
	 *          report.setFilePath(&quot;/SAGSISO/doktor/liste/DoktorHastaSayisi.jasper&quot;);
	 *          report.setReportFormat(&quot;PDF&quot;);
	 *          ...
	 *          report.setDataList(dataList);
	 *          ...
	 * 	
	 *          TcmbJasperReportsManager.saveReport(request,response,report);
	 *      }
	 *  }
	 * </code>
	 * </pre>
	 * 
	 * @param request
	 *            an HttpServletRequest object
	 * @param response
	 *            an HttpServletResponse object
	 * @param report
	 *            a {@link IskolikReport} object, which will be saved
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JRException
	 */
	/**
	 * @deprecated Replaced by {@link #saveReportOnClient()}
	 */

	public static void saveReport(HttpServletRequest request,
			HttpServletResponse response, IskolikReport report)
			throws FileNotFoundException, IOException, JRException {

		String reportFormat = report.getReportFormat();

		JasperPrint jasperPrint = generateJasperPrint(report);

		/* get exporter */
		JRExporter exporter = getExporter(response, jasperPrint, reportFormat);

		if (reportFormat.equalsIgnoreCase("pdf")) {
			/* set response header */
			response.setContentType("application/pdf");
			String fileName = generateFileName(report.getFilePath(), "pdf");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
		} else if (reportFormat.equalsIgnoreCase("XLS")) {
			/* set response header */
			response.setContentType("application/vnd.ms-excel");
			String fileName = generateFileName(report.getFilePath(), "xls");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
		} else if (reportFormat.equalsIgnoreCase("HTML")) {
			/* set response header */
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("UTF-8");
			String fileName = generateFileName(report.getFilePath(), "html");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
		} else {
			throw new IllegalArgumentException(reportFormat.toUpperCase()
					+ " Unsupported format: " + reportFormat);
		}

		/* export report */
		exporter.exportReport();
	}

	/**
	 * Displays the Open/Save dialog for the TcmbReport object. Open command
	 * opens the report in an appropriate application externally. Save command
	 * saves the report to the client file system. Once this method is called,
	 * no change should be made on the response object. This means forwarding to
	 * a page via {@link ActionForward} or redirecting to a URL must be avoided.
	 * Otherwise,
	 * 
	 * <pre>
	 *            	{@link IllegalStateException IllegalStateException}: Cannot forward after response has been committed
	 * </pre>
	 * 
	 * exception is thrown. If, in addition, sitemesh is active on the forwarded
	 * page,
	 * 
	 * <pre>
	 *            	{@link IllegalStateException IllegalStateException}: response.getWriter() called after response.getOutputStream()
	 * </pre>
	 * 
	 * exception is thrown.
	 * 
	 * <p>
	 * <i>Supported Formats</i>: <b>HTML,PDF,XLS</b>.
	 * </p>
	 * 
	 * <br>
	 * Sample code: </br>
	 * 
	 * <pre>
	 * <code>
	 *  public class ReportAction extends DispatchAction {
	 *        ...
	 *   
	 *      public void saveReportOnClient(ActionMapping mapping, ActionForm form,
	 *          HttpServletResponse response) throws Exception {
	 * 
	 *          TcmbReport report = new TcmbReport();
	 *          report.setFilePath(&quot;/SAGSISO/doktor/liste/DoktorHastaSayisi.jasper&quot;);
	 *          report.setReportFormat(&quot;PDF&quot;);
	 *          ...
	 *          report.setDataList(dataList);
	 *          ...
	 * 	
	 *          TcmbJasperReportsManager.saveReportOnClient(response,report);
	 *      }
	 *  }
	 * </code>
	 * </pre>
	 * 
	 * @param response
	 *            an HttpServletResponse object
	 * @param report
	 *            a {@link IskolikReport} object, which will be saved
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JRException
	 */
	public static void saveReportOnClient(HttpServletResponse response,
			IskolikReport report) throws FileNotFoundException, IOException,
			JRException {

		String reportFormat = report.getReportFormat();

		JasperPrint jasperPrint = generateJasperPrint(report);

		/* get exporter */
		JRExporter exporter = getExporter(response, jasperPrint, reportFormat);

		if (reportFormat.equalsIgnoreCase("pdf")) {
			/* set response header */
			response.setContentType("application/pdf");
			String fileName = generateFileName(report.getFilePath(), "pdf");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
		} else if (reportFormat.equalsIgnoreCase("XLS")) {
			/* set response header */
			response.setContentType("application/vnd.ms-excel");
			String fileName = generateFileName(report.getFilePath(), "xls");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
		} else if (reportFormat.equalsIgnoreCase("HTML")) {
			/* set response header */
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("UTF-8");
			String fileName = generateFileName(report.getFilePath(), "html");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
		} else {
			throw new IllegalArgumentException("Unsupported format: "
					+ reportFormat.toUpperCase());
		}

		/* export report */
		exporter.exportReport();
	}

	/**
	 * 
	 * Saves the TcmbReport to the destination file.
	 * <p>
	 * <i>Supported Formats</i>: <b>HTML,PDF,XLS</b>.
	 * </p>
	 * 
	 * <br>
	 * Sample code: </br>
	 * 
	 * <pre>
	 * <code>
	 *  public class ReportAction extends DispatchAction {
	 *        ...
	 *   
	 *      public void saveReportOnServer(ActionMapping mapping, ActionForm form,
	 *          HttpServletResponse response) throws Exception {
	 * 
	 *          TcmbReport report = new TcmbReport();
	 *          report.setFilePath(&quot;/SAGSISO/doktor/liste/DoktorHastaSayisi.jasper&quot;);
	 *          report.setReportFormat(&quot;PDF&quot;);
	 *          ...
	 *          report.setDataList(dataList);
	 *          ...
	 *          
	 *          File file = new File(&quot;D://Jasper-Reports-Deneme.pdf&quot; );
	 *          TcmbJasperReportsManager.saveReportOnServer(report, file);
	 *          
	 *          response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	 *          
	 *      }
	 *  }
	 * </code>
	 * </pre>
	 * 
	 * @param report
	 *            a {@link IskolikReport} object, which will be saved
	 * @param file
	 *            the destination {@link File} file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JRException
	 */
	public static void saveReportOnServer(IskolikReport report, File file) throws IOException, JRException {
		try (OutputStream os = new FileOutputStream(file)) {
			saveReportOnServer(report, os);
		}
	}

	/**
	 * 
	 * Writes the TcmbReport to the destination output stream.
	 * <p>
	 * <i>Supported Formats</i>: <b>HTML,PDF,XLS</b>.
	 * </p>
	 * 
	 * <br>
	 * Sample code: </br>
	 * 
	 * <pre>
	 * <code>
	 *  public class ReportAction extends DispatchAction {
	 *        ...
	 *   
	 *      public void saveReportOnServer(ActionMapping mapping, ActionForm form,
	 *          HttpServletResponse response) throws Exception {
	 * 
	 *          TcmbReport report = new TcmbReport();
	 *          report.setFilePath(&quot;/SAGSISO/doktor/liste/DoktorHastaSayisi.jasper&quot;);
	 *          report.setReportFormat(&quot;PDF&quot;);
	 *          ...
	 *          report.setDataList(dataList);
	 *          ...
	 *          
	 *          OutputStream os = ...;
	 *          TcmbJasperReportsManager.saveReportOnServer(report, file);
	 *          os.close();
	 *          
	 *      }
	 *  }
	 * </code>
	 * </pre>
	 * 
	 * @param report
	 *            a {@link IskolikReport} object, which will be saved
	 * @param OutputStream
	 *            the destination {@link OutputStream} output stream.
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	public static void saveReportOnServer(IskolikReport report, OutputStream os)
			throws IOException, JRException {

		String reportFormat = report.getReportFormat();

		JasperPrint jasperPrint = generateJasperPrint(report);

		/* get exporter */
		JRExporter exporter = null;
		/* configure exporter */
		if (reportFormat.equalsIgnoreCase("PDF")) {
			exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
		} else if (reportFormat.equalsIgnoreCase("XLS")) {
			exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);

			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
		} else {
			throw new IllegalArgumentException(" Unsupported format: "
					+ reportFormat.toUpperCase());
		}

		/* export report */
		exporter.exportReport();
	}

	// ********************************************************************************
	// List Operations
	// ********************************************************************************
	/**
	 * Merges all reports in the given list and displays them as a single
	 * report. See {@link #displayReport(HttpServletResponse, IskolikReport)
	 * displayReport(...)} for further details.
	 * 
	 * @param response
	 *            an HttpServletResponse object
	 * @param reportList
	 *            a non-empty list of TcmbReport objects all of which will be
	 *            displayed
	 * @param fileName
	 *            name of the output file that is obtained by merging the
	 *            TcmbReport objects in the given list. File extension should be
	 *            omitted.
	 * @param reportFormat
	 *            file format of the output file. In case of unrecognized
	 *            formats, HTML is used by default.
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	public static void displayListOfReports(HttpServletResponse response,
											List<IskolikReport> reportList, String fileName, String reportFormat)
			throws IOException, JRException {

		/* generate the list of JasperPrint objects */
		List<JasperPrint> jasperPrintList = convertToJasperPrintList(reportList);

		/* get exporter */
		JRExporter exporter = getExporter(response, jasperPrintList,
				reportFormat);

		if (reportFormat.equalsIgnoreCase("pdf")) {
			/* set response header */
			response.setContentType("application/pdf");
			fileName = generateFileName(fileName, "pdf");
			response.setHeader("Content-Disposition", "inline;filename="
					+ fileName);
		} else if (reportFormat.equalsIgnoreCase("XLS")) {
			/* set response header */
			response.setContentType("application/vnd.ms-excel");
			fileName = generateFileName(fileName, "xls");
			response.setHeader("Content-Disposition", "inline;filename="
					+ fileName);
		} else if (reportFormat.equalsIgnoreCase("HTML")) {
			/* set response header */
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
		} else {
			throw new IllegalArgumentException("Unsupported format: "
					+ reportFormat.toUpperCase());
		}

		/* export report */
		exporter.exportReport();
	}

	/**
	 * Prints all the reports in the given list. See
	 * {@link #printReport(HttpServletResponse, IskolikReport)} for further
	 * details.
	 * 
	 * @param response
	 *            an HttpServletResponse object
	 * @param reportList
	 *            a non-empty list of TcmbReport objects, all of which will be
	 *            printed
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	public static void printListOfReports(HttpServletResponse response,
			List<IskolikReport> reportList) throws IOException, JRException {

		/* generate the list of JasperPrint objects */
		List<JasperPrint> jasperPrintList = convertToJasperPrintList(reportList);

		/* set response header */
		response.setContentType("application/pdf");
		String fileName = "Tüm Raporlar.pdf";
		response.setHeader("Content-Disposition", "inline;filename=" + fileName);

		/* configure exporter */
		JRExporter exporter = getExporter(response, jasperPrintList, "PDF");
		exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT,
				"this.print({bUI: true, bSilent: true, bShrinkToFit: true});");

		/* export report */
		exporter.exportReport();
	}

	/**
	 * Saves all the reports in the given list. See
	 * {@link #saveReportOnServer(IskolikReport, File)} for further details.
	 * 
	 * @param reportList
	 *            a non-empty list of TcmbReport objects, all of which will be
	 *            printed
	 * @param file
	 *            the destination {@link File} file.
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	public static void saveListOfReportsOnServer(List<IskolikReport> reportList, File file) throws IOException, JRException {
		try (OutputStream os = new FileOutputStream(file)) {
			saveListOfReportsOnServer(reportList, os, file.getName().substring(file.getName().lastIndexOf('.') + 1));
		}
	}

	/**
	 * Saves all the reports in the given list. See
	 * {@link #saveReportOnServer(IskolikReport report, OutputStream os)} for
	 * further details.
	 * 
	 * @param reportList
	 *            a non-empty list of TcmbReport objects, all of which will be
	 *            printed
	 * @param os
	 *            the destination {@link OutputStream} output stream.
	 * @param reportFormat
	 *            file format of the output file. In case of unrecognized
	 *            formats, HTML is used by default.
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	public static void saveListOfReportsOnServer(List<IskolikReport> reportList,
			OutputStream os, String reportFormat) throws IOException,
			JRException {

		/* generate the list of JasperPrint objects */
		List<JasperPrint> jasperPrintList = convertToJasperPrintList(reportList);

		/* get exporter */
		JRExporter exporter = null;
		/* configure exporter */
		if (reportFormat.equalsIgnoreCase("PDF")) {
			exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
					jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
		} else if (reportFormat.equalsIgnoreCase("XLS")) {
			exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
					jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);

			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
		} else {
			throw new IllegalArgumentException(" Unsupported format: "
					+ reportFormat.toUpperCase());
		}

		/* export report */
		exporter.exportReport();
	}

	/**
	 * Saves all the reports in the given list to the client. See
	 * {@link #saveReportOnClient(HttpServletResponse, IskolikReport)} for further
	 * details.
	 * 
	 * @param response
	 *            an HttpServletResponse object
	 * @param reportList
	 *            a non-empty list of TcmbReport objects, all of which will be
	 *            printed
	 * @param fileName
	 *            name of the output file that is obtained by merging the
	 *            TcmbReport objects in the given list. File extension should be
	 *            omitted.
	 * @param reportFormat
	 *            file format of the output file. In case of unrecognized
	 *            formats, HTML is used by default.
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	public static void saveListOfReportsOnClient(HttpServletResponse response,
												 List<IskolikReport> reportList, String fileName, String reportFormat)
			throws IOException, JRException {

		/* generate the list of JasperPrint objects */
		List<JasperPrint> jasperPrintList = convertToJasperPrintList(reportList);

		/* get exporter */
		JRExporter exporter = getExporter(response, jasperPrintList,
				reportFormat);

		if (reportFormat.equalsIgnoreCase("pdf")) {
			/* set response header */
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName + "." + reportFormat);
		} else if (reportFormat.equalsIgnoreCase("XLS")) {
			/* set response header */
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName + "." + reportFormat);
		} else if (reportFormat.equalsIgnoreCase("HTML")) {
			/* set response header */
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName + "." + reportFormat);
		} else {
			throw new IllegalArgumentException(reportFormat.toUpperCase()
					+ " Unsupported format: " + reportFormat);
		}

		/* export report */
		exporter.exportReport();
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// PRIVATE //
	// /////////////////////////////////////////////////////////////////////////////////
	/**
	 * Returns a relevant JRExporter object according to the report format.
	 */
	private static JRExporter getExporter(HttpServletResponse response,
			JasperPrint jasperPrint, String format) throws IOException {

		JRExporter exporter = null;

		/* configure exporter */
		if (format.equalsIgnoreCase("PDF")) {
			exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					response.getOutputStream());
		} else if (format.equalsIgnoreCase("XLS")) {
			exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					response.getOutputStream());

			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
		}

		return exporter;
	}

	/**
	 * Returns a relevant JRExporter object according to the report format.
	 */
	private static JRExporter getExporter(HttpServletResponse response,
			List<JasperPrint> jasperPrintList, String format)
			throws IOException {

		JRExporter exporter = null;

		/* configure exporter */
		if (format.equalsIgnoreCase("PDF")) {
			exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
					jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					response.getOutputStream());
		} else if (format.equalsIgnoreCase("XLS")) {
			exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
					jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					response.getOutputStream());

			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
		}

		return exporter;
	}

	/**
	 * Compiles the report if necessary and generates a JasperPrint object.
	 * Necessary means there is no pre-compiled jasper file of the
	 * <code>report</code>. This ensures that jrxml files are compiled only
	 * once.
	 * 
	 * @param report
	 *            TcmbReport whose JasperPrint object is to be generated.
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	public static JasperPrint generateJasperPrint(IskolikReport report)
			throws IOException, JRException {

		JasperReport jasperReportMaster;

		URL fileUrl = getFileUrl(report.getFilePath());
		String absolutePath = fileUrl.getPath();

		/* first compile report if necessary */
		if (absolutePath.endsWith(".jrxml")) {
			absolutePath = compileTcmbReport(fileUrl, report);
		}

		/* Load master report */
		jasperReportMaster = loadReport(absolutePath);

		/* Load subreports */
		if (report.hasSubreport()) {
			report = loadSubreports(report);
		}

		/* connect to DataSource */
		JRDataSource ds = report.getDataSource();

		/* set report locale */
		Locale trLocale = new Locale("tr", "TR");
		report.getParameters().put(JRParameter.REPORT_LOCALE, trLocale);

		/***/
		if (ds instanceof JREmptyDataSource) {
			try {
				Connection conn = report.getConnection();
				return JasperFillManager.fillReport(jasperReportMaster,
						report.getParameters(), conn);
			} catch (NotImplementedException e) {
			}
		}
		/***/

		/* Fill the report */
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasperReportMaster, report.getParameters(), ds);

		/*
		 * POI, the library used for generating excel reports, does not accept
		 * sheet names longer than 31 characters. Thus, we need to truncate the
		 * name of the jasperprint if its length is larger than 31. The name of
		 * the report is not changed.
		 */
		if (report.getReportFormat().equalsIgnoreCase("XLS")) {
			String name = jasperPrint.getName();
			if (name.length() > 31) {
				jasperPrint.setName(name.substring(0, 31));
			}
		}

		return jasperPrint;
	}

	/**
	 * Loads all subreports and adds them to the master report as parameters.
	 * <p>
	 * Subreports are added to the master report with parameter names
	 * subreport1, subreport2, subreport3 and so on. The data sources of the
	 * corresponding subreports are added to the master report with parameter
	 * names subreportDataSource1, subreportDataSource2, subreportDataSource3
	 * and so on.
	 * </p>
	 * 
	 * @param report
	 *            TcmbReport whose JasperPrint object is to be loaded. The file
	 *            extension of the filepath must be omitted.
	 * 
	 * @return TcmbReport object with all subreports added as parameters
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	private static IskolikReport loadSubreports(IskolikReport report)
			throws IOException, JRException {

		/* get subreports */
		List<IskolikReport> subreportList = report.getSubreportList();
		JasperReport jasperReportSub = null;

		/* load each subreport */
		if (subreportList.size() > 0) {
			String absoluteSubreportPath;

			int counter = 1;

			for (IskolikReport subreport : subreportList) {
				URL subreportUrl = getFileUrl(subreport.getFilePath());
				absoluteSubreportPath = subreportUrl.getPath();

				/* first compile subreport if necessary */
				if (absoluteSubreportPath.endsWith(".jrxml")) {
					absoluteSubreportPath = compileTcmbReport(subreportUrl,
							report);
				}

				/* load compiled jasper file of the subreport */
				if (!(new File(absoluteSubreportPath)).canWrite()) {
					throw new IOException(
							"Cannot write to "
									+ absoluteSubreportPath
									+ ". If this path is a jar file, put the jasper file in the jar instead of jrxml.");
				}
				jasperReportSub = loadReport(absoluteSubreportPath);

				/* add subreport to the master report */
				report.addReportParameter("subreportDataSource" + counter,
						subreport.getDataSource());
				report.addReportParameter("subreport" + counter,
						jasperReportSub);
				counter++;
			}
		}

		return report;
	}

	// ********************************************************************************
	// Compile report
	// ********************************************************************************
	/**
	 * Compiles the report and its subreports.
	 * 
	 * @param fileUrl
	 *            URL of the .jrxml file, which should be in the classpath.
	 * @param report
	 *            TcmbReport whose JasperPrint object is to be generated. The
	 *            file extension of the filepath may exist.
	 * 
	 * @return absolute path of the '.jasper' file created after compilation.
	 * 
	 * @throws FileNotFoundException
	 * @throws JRException
	 */
	private static String compileTcmbReport(URL fileUrl, IskolikReport report) throws IOException, JRException {

		/* absolute file path of the compiled jasper file */
		String absolutePath = fileUrl.getPath();
		absolutePath = removeFileExtension(absolutePath);
		String masterJasperAbsolutePath = absolutePath + ".jasper";

		/* Load report */
		JasperDesign masterJasperDesign = null;
		try (InputStream is = fileUrl.openStream()) {
			masterJasperDesign = JRXmlLoader.load(is);
		}

		/* compile report */
		JasperCompileManager.compileReportToFile(masterJasperDesign, masterJasperAbsolutePath);

		/* compile subreports */
		if (report.hasSubreport()) {
			for (IskolikReport subreport : report.getSubreportList()) {
				fileUrl = getFileUrl(subreport.getFilePath());
				compileTcmbReport(fileUrl, subreport);
			}
		}

		return masterJasperAbsolutePath;
	}

	/**
	 * Extracts the file name from the file path, and appends the file extension
	 * to it. Example:
	 * 
	 * <pre>
	 * 	filePath = &quot;/reports/testReport.jasper&quot;
	 * 	extension = &quot;pdf&quot; 
	 * 	result= &quot;testReport.pdf&quot;
	 * </pre>
	 * 
	 * @param filePath
	 *            relative or absolute path of the file
	 * @param extension
	 *            extension of the file
	 * 
	 * @return generated name of the file
	 */
	private static String generateFileName(String filePath, String extension) {
		int indexStart = filePath.lastIndexOf('/') + 1;
		int indexEnd = filePath.lastIndexOf('.');
		if (indexEnd == -1 || indexEnd < indexStart) {
			return filePath.substring(indexStart) + "." + extension;
		}

		return filePath.substring(indexStart, indexEnd) + "." + extension;
	}

	/**
	 * Converts each TcmbReport object in the list to a JasperPrint object.
	 * 
	 * @param reportList
	 *            list of the TcmbReports
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	private static List<JasperPrint> convertToJasperPrintList(
			List<IskolikReport> reportList) throws IOException, JRException {

		List<JasperPrint> result = new ArrayList<JasperPrint>();

		for (IskolikReport report : reportList) {
			result.add(generateJasperPrint(report));
		}

		return result;
	}

	/**
	 * Checks if a pre-compiled jasper file exists. The file is searched in the
	 * classpath of the project. If the file exists, returns the absolute path
	 * of this file. Otherwise, checks if the jrxml file specified by the
	 * filepath of the report exists. If so, returns the absolute path of this
	 * file. Otherwise, throws FileNotFoundException.
	 * 
	 * @param report
	 *            a TcmbReport object
	 * 
	 * @throws FileNotFoundException
	 */
	private static URL getFileUrl(String relativeFilePath)
			throws FileNotFoundException {

		if ((relativeFilePath == null)) {
			throw new NullPointerException(
					"<code>TcmbReport.reportFileName</code> cannot be null.");
		} else if ((relativeFilePath.length() == 0)) {
			throw new FileNotFoundException(
					"<code>TcmbReport.reportFileName</code> cannot be empty string \"\".");
		} else {
			/* check if the jasper file exists in the classpath */
			URL resource = IskolikJasperReportsManager.class
					.getResource(relativeFilePath + ".jasper");
			if (resource != null) {
				return resource;
			}

			/* check if a jrxml file exists in the classpath */
			resource = IskolikJasperReportsManager.class
					.getResource(relativeFilePath + ".jrxml");
			if (resource != null) {
				return resource;
			} else {
				/* check if a file with the given name exists in the classpath */
				resource = IskolikJasperReportsManager.class
						.getResource(relativeFilePath);
				if (resource != null) {
					return resource;
				} else {
					throw new FileNotFoundException(relativeFilePath
							+ " is not found in the CLASSPATH.");
				}
			}
		}
	}

	/**
	 * removes the extensions '.jrxml' or '.jasper' from the filepath
	 */
	private static String removeFileExtension(String filePath) {
		if (filePath.endsWith(".jrxml") || filePath.endsWith(".jasper")) {
			return filePath.substring(0, filePath.length() - 6);
		}

		return filePath;
	}
}