package servlet;

import bean.MonitorBean;
import bean.QuotaBean;
import bean.SpeciesBean;
import dao.MonitorDao;
import dao.QuotaDao;
import dao.Quota_monDao;
import dao.SpeciesDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/monitorAddBatchPlantDo")
public class MonitorAddBatchPlantDo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MonitorAddBatchPlantDo() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 检查请求是否为多部分（即文件上传）
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }

        // 配置上传设置
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段（即文件字段）
                    if (!item.isFormField()) {
                        // 使用 Commons IO 库中的工具类直接读取文件内容到内存
                        String csvContent = IOUtils.toString(item.getInputStream(), StandardCharsets.UTF_8);
                        String[] lines = csvContent.split("\\r?\\n");
                        boolean isFirstLine = true; // 初始化标志以跳过第一行
                        for (String line : lines) {
                            String[] values = line.split(",");
                            if (isFirstLine) {
                                isFirstLine = false; // 将标志设为 false，以便不再跳过
                                continue; // 跳过当前迭代，即标题行
                            }
                            MonitorBean monitorBean = new MonitorBean();
                            monitorBean.setCreatedby(values[0]);
                            monitorBean.setMonby(values[1]);
                            monitorBean.setMon_place(values[2]);
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date date = formatter.parse(values[3]);
                            monitorBean.setMon_time(new java.sql.Date(date.getTime()));
                            monitorBean.setMon_target(Integer.parseInt(values[5]));
                            MonitorDao monitorDao = new MonitorDao();
                            monitorBean = monitorDao.insertMonitor(monitorBean);

                            int quo_id = Integer.parseInt(values[4]);
                            Quota_monDao quotaMonDao = new Quota_monDao();
                            boolean isup = quotaMonDao.insertQuota_mon(monitorBean.getMon_id(), quo_id);

                            if(isup){
                                response.sendRedirect("/gardens/monitorAllPlants?feedbackmessage="+ URLEncoder.encode("更新成功", "UTF-8"));
                            }
                            else{
                                response.sendRedirect("/gardens/monitorAddPlantDo?feedbackmessage="+ URLEncoder.encode("更新失败", "UTF-8"));
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        response.sendRedirect("/gardens/monitorAllPlants");
    }


}
