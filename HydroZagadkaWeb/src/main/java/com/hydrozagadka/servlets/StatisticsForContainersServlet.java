package com.hydrozagadka.servlets;

import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.User;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.AdminStatsDao;
import com.hydrozagadka.dao.StatisticsDao;
import com.hydrozagadka.dao.WaterContainerDao;
import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/stats")
public class StatisticsForContainersServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(com.hydrozagadka.servlets.WelcomeServlet.class);
    @Inject
    private FreeMarkerConfig freeMarkerConfig;
    @Inject
    private StatisticsDao statisticsDao;
    @Inject
    private AdminStatsDao adminStatsDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        List<Statistics> WCList = adminStatsDao.getStatistics();
        Template template = freeMarkerConfig.getTemplate("adminPage/statistics.ftlh", getServletContext());

        Map<String, Object> model = new HashMap<>();
        model.put("WCList", WCList);
        try {
            template.process(model, response.getWriter());
        } catch (TemplateException e) {
            logger.warn("Template doesn't exist");
        }
    }
}