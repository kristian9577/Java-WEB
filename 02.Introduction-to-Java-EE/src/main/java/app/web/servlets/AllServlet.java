package app.web.servlets;

import app.domain.models.view.CarViewModel;
import app.service.CarService;
import app.util.FileUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/all")
public class AllServlet extends HttpServlet {
    private static String FILE_PATH = "D:\\SOFT UNI - JAVA\\JAVA WEB\\05.Introduction-to-Java-EE\\src\\main\\webapp\\view\\all.html";

    private final FileUtil fileUtil;
    private final CarService carService;
    private final ModelMapper modelMapper;

    @Inject
    public AllServlet(FileUtil fileUtil, CarService carService, ModelMapper modelMapper) {
        this.fileUtil = fileUtil;
        this.carService = carService;
        this.modelMapper = modelMapper;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String html = fileUtil.readFile(FILE_PATH);

        StringBuilder sb = new StringBuilder();
        List<CarViewModel> cars = this.carService.allCars()
                .stream()
                .map(car -> this.modelMapper.map(car, CarViewModel.class))
                .collect(Collectors.toList());

        for (CarViewModel car : cars) {
            sb.append(String.format("<li class=\"d-flex justify-content-around\">\n" +
                            " <div class=\"col-md-4 d-flex flex-column text-center mb-3\">\n" +
                            " <h2 class=\"text-white text-center\">Brand: %s</h2>\n" +
                            "<h4 class=\"text-white text-center\">Model: %s</h4>\n" +
                            "<h4 class=\"text-white text-center\">Year: %d</h4>\n" +
                            "<h4 class=\"text-white text-center\">Engine: %s</h4>\n" +
                            "</div >\n " +
                            "</li>",
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getEngine()));
        }

        html = html.replace("{{replace}}", sb.toString());
        out.println(html);
    }
}
