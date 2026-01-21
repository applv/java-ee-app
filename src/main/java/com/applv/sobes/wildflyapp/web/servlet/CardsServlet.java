package com.applv.sobes.wildflyapp.web.servlet;

import static com.applv.sobes.wildflyapp.web.servlet.Constants.WEB_INF_JSP_DIR;

import com.applv.sobes.wildflyapp.dto.CardDto;
import com.applv.sobes.wildflyapp.service.CardService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

@WebServlet(value = "/v1/cards/*", name = "cardsServlet")
public class CardsServlet extends HttpServlet {

  @Serial
  private static final long serialVersionUID = 6067942412798804136L;

  private final static Logger LOG = Logger.getLogger(CardsServlet.class);

  private final CardService cardsService;

  @Inject
  public CardsServlet(CardService cardsService) {
    super();
    this.cardsService = cardsService;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      request.setAttribute("cards", getData(request));
      request.getRequestDispatcher(WEB_INF_JSP_DIR + "/cards.jsp").forward(request, response);
    } catch (Exception e) {
      LOG.info(e.getMessage(), e);
      request.getRequestDispatcher(WEB_INF_JSP_DIR + "/error.jsp").forward(request, response);
    }
  }

  private List<CardDto> getData(HttpServletRequest request) {
    String pathInfo = request.getPathInfo();
   return pathInfo != null && pathInfo.length() > 1
    ? cardsService.findById(Integer.parseInt(pathInfo.substring(1)))
                  .map(List::of)
                  .orElse(List.of())
    : getDataByTopicAndValue(request);
  }

  private List<CardDto> getDataByTopicAndValue(HttpServletRequest request) {
    String topic = request.getParameter("topic");
    String value = request.getParameter("value");

    if (StringUtils.isBlank(topic)) {
      return getDataByValue(value);
    }
    if (StringUtils.isBlank(value)) {
      return getDataByTopic(topic);
    }
    return updateValues(cardsService.findAllByTopicNameAndValue(topic, value), value);
  }

  private List<CardDto> getDataByTopic(String topic) {
    return StringUtils.isBlank(topic)
    ? cardsService.findAll()
    : cardsService.findAllByTopicName(topic);
  }

  private List<CardDto> getDataByValue(String value) {
    return StringUtils.isBlank(value)
        ? cardsService.findAll()
        : updateValues(cardsService.findAllByValue(value), value);
  }

  private List<CardDto> updateValues(List<CardDto> cards, String value) {
    String value2 = "<span style=\"color: red;\">" + value + "</span>";
    cards.forEach(card -> {
      card.setQuestion(card.getQuestion().replaceAll(value, value2));
      card.setAnswer(card.getAnswer().replaceAll(value, value2));
    });
    return cards;
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
