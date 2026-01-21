package com.applv.sobes.wildflyapp.web.action;

import com.applv.sobes.wildflyapp.dto.CardDto;
import com.applv.sobes.wildflyapp.service.CardService;
import com.applv.sobes.wildflyapp.web.form.FindCardsBySubstrForm;
import java.util.List;
import javax.enterprise.inject.spi.CDI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FindCardsBySubstrAction extends Action {

  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {

    FindCardsBySubstrForm substrForm = (FindCardsBySubstrForm) form;
    String substr = substrForm.getCardSubstr();

    CardService service = CDI.current().select(CardService.class).get();
    List<CardDto> cards = updateValues(service.findByValue(substr), substr);
    request.setAttribute("cards", cards);

    return mapping.findForward("success");
  }

  private List<CardDto> updateValues(List<CardDto> cards, String value) {
    String value2 = "<span style=\"color: red;\">" + value + "</span>";
    cards.forEach(card -> {
      card.setQuestion(card.getQuestion().replaceAll(value, value2));
      card.setAnswer(card.getAnswer().replaceAll(value, value2));
    });
    return cards;
  }
}
