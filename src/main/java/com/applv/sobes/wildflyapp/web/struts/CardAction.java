package com.applv.sobes.wildflyapp.web.struts;

import static com.applv.sobes.wildflyapp.web.servlet.Constants.WEB_INF_JSP_DIR;
import static org.apache.struts2.action.Action.SUCCESS;

import com.applv.sobes.wildflyapp.dto.CardDto;
import com.applv.sobes.wildflyapp.service.CardService;
import javax.inject.Inject;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

@Namespace("/v2/cards/")
@ResultPath(WEB_INF_JSP_DIR)
public class CardAction {

  @Inject
  private CardService cardService;

  @Setter
  private Integer cardId;

  @Getter
  private List<CardDto> cards;

  @Action(value = "*", params={"cardId", "{1}"}, results = {@Result(name = SUCCESS, location = "cards.jsp")})
  public String getCardById() {
    cards = cardId != null
        ? cardService.findById(cardId)
                     .map(List::of)
                     .orElse(List.of())
        :List.of();
    return SUCCESS;
  }
}
