package ntut.csie.sslab.kanban.card.usecase.port.in.get;

import ntut.csie.sslab.kanban.card.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class CardMapper {
	public static CardDto toDto(Card card) {
		CardDto dto = new CardDto();
		dto.setCardId(card.getCardId());
		dto.setBoardId(card.getBoardId());
		dto.setWorkflowId(card.getWorkflowId());
		dto.setLaneId(card.getLaneId());
		dto.setDescription(card.getDescription());
		dto.setUserId(card.getUserId());
		dto.setEstimate(card.getEstimate());
		dto.setNotes(card.getNotes());
		dto.setDeadline(card.getDeadline());
		dto.setType(card.getType());
		return dto;
	}

	public static List<CardDto> toDto(List<Card> cards) {
		List<CardDto> cardDtos = new ArrayList<>();
		for(Card card : cards) {
			cardDtos.add(toDto(card));
		}
		return cardDtos;
	}
}
