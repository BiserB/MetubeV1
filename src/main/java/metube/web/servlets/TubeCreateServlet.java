package metube.web.servlets;


import metube.domain.entities.Tube;
import metube.domain.models.binding.TubeCreateBindingModel;
import metube.domain.models.service.TubeServiceModel;
import metube.service.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class TubeCreateServlet extends HttpServlet {

    private final ModelMapper mapper;
    private final TubeService service;

    @Inject
    public TubeCreateServlet(ModelMapper mapper, TubeService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/jsps/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TubeCreateBindingModel tubeCreateBindingModel = (TubeCreateBindingModel) req
                .getAttribute("tubeCreateBindingModel");

        TubeServiceModel model = mapper.map(tubeCreateBindingModel, TubeServiceModel.class);

        service.saveTube(model);

       resp.sendRedirect("/tubes/details?name=" + tubeCreateBindingModel.getName());

    }
}
