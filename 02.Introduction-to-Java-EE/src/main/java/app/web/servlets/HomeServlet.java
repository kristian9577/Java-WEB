package app.web.servlets;

import app.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private static String FILE_PATH = "D:\\SOFT UNI - JAVA\\JAVA WEB\\05.Introduction-to-Java-EE\\src\\main\\webapp\\view\\home.html";
    private final FileUtil fileUtil;

    @Inject
    public HomeServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html = fileUtil.readFile(FILE_PATH);
        resp.getWriter().println(html);
    }

}
