/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;
import services.ReportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 845593
 */
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        ServletContext cntx = request.getServletContext();
        // Get the absolute path of the image
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountService accountService = new AccountService();
        ReportService reportService = new ReportService();
        String path = getServletContext().getRealPath("/WEB-INF");
//                      
        String filename = path + "\\report\\" + ".xlsx";
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream out = new FileOutputStream(file);

        if (type != null && type.equals("all")) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet();
                List<String[]> data = reportService.getUserReportAll();
                for (int i = 0; i < data.size(); i++) {
                    XSSFRow excelRow = sheet.createRow(i);
                    String[] dbRow = data.get(i);
                    for (int j = 0; j < dbRow.length; j++) {
                        XSSFCell cell = excelRow.createCell(j);
                        cell.setCellValue(dbRow[j]);

                    }
                }
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
                workbook.write(outByteStream);
                byte[] outArray = outByteStream.toByteArray();

                response.setContentType("application/vnd.ms-excel");
                response.setContentLength(outArray.length);
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + filename + "\"");

                OutputStream outStream = response.getOutputStream();
                outStream.write(outArray);

                outStream.flush();
                outStream.close();

//                
//                workbook.write(out);
//                
//           FileInputStream input=new FileInputStream(file);
//              OutputStream output=response.getOutputStream();
//              byte[] buf = new byte[1024];
//              int count = 0;
//               while ((count = input.read(buf)) >= 0) {
//                 output.write(buf, 0, count);
//                    }
//                    output.close();
//                    input.close(); 
            } catch (IOException ignore) {
                ignore.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (type != null && type.equals("activeUser")) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet();
                List<String[]> data = reportService.getUserReport();
                for (int i = 0; i < data.size(); i++) {
                    XSSFRow excelRow = sheet.createRow(i);
                    String[] dbRow = data.get(i);
                    for (int j = 0; j < dbRow.length; j++) {
                        XSSFCell cell = excelRow.createCell(j);
                        cell.setCellValue(dbRow[j]);

                    }
                }
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
                workbook.write(outByteStream);
                byte[] outArray = outByteStream.toByteArray();

                response.setContentType("application/vnd.ms-excel");
                response.setContentLength(outArray.length);
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + filename + "\"");

                OutputStream outStream = response.getOutputStream();
                outStream.write(outArray);

                outStream.flush();
                outStream.close();
//                workbook.write(out);
//
//                out.flush();
//                out.close();
//              FileInputStream input=new FileInputStream(file);
//              OutputStream output=response.getOutputStream();
//              byte[] buf = new byte[1024];
//              int count = 0;
//               while ((count = input.read(buf)) >= 0) {
//                 output.write(buf, 0, count);
//                    }
//                    output.close();
//                    input.close(); 

            } catch (IOException ignore) {
                ignore.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
