package com.applv.sobes.wildflyapp.web.servlet;

import com.applv.sobes.wildflyapp.dto.TopicDto;
import com.applv.sobes.wildflyapp.service.TopicService;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(value = "/v1/topics", name = "topicsServlet")
public class TopicsServlet extends HttpServlet {

  @Serial
  private static final long serialVersionUID = 6067942412798804136L;

  @Inject
  private TopicService topicService;


  public TopicsServlet() {
    super();
  }

@Override
  public void init(ServletConfig config) {
  }

	@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<TopicDto> data = topicService.findAll();
    request.setAttribute("topics", data);

    request.getRequestDispatcher("/WEB-INF/jsp/topics.jsp").forward(request, response);
  }

	@Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
  }

	@Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) {
  }

	@Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
  }

}
