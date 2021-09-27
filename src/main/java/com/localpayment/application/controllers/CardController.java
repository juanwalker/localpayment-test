package com.localpayment.application.controllers;

import com.localpayment.application.actionsResponses.CardResponse;
import com.localpayment.application.actionsResponses.ResponseStatus;
import com.localpayment.application.postActions.AddCard;
import com.localpayment.application.postActions.ValidPayment;
import com.localpayment.application.services.CardService;
import com.localpayment.domain.entities.Card;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/card")
public class CardController {

	private final CardService cardService;

	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	
		@PostMapping("/")
		public CardResponse add(@RequestBody AddCard addCard) {
		Card card = this.cardService.add(addCard);
		List<Card> cards = new ArrayList<Card>();
		if (card != null) {
			cards.add(card);
			return new CardResponse(cards,"Card created", ResponseStatus.OK.getMessage());
		} else {
			return new CardResponse(null,"Card not created", ResponseStatus.KO.getMessage());
		}
	}

		@GetMapping("/user/{idNumber}")
		public CardResponse findByPerson(@PathVariable(name = "idNumber") String  idNumber) {
			List<Card> cards = this.cardService.findByPersonId(idNumber);
			if (cards != null && !cards.isEmpty()) {
				Set<Card> set = new LinkedHashSet<>(cards);
				cards =  new ArrayList<Card>(set);
				return new CardResponse(cards,"Cards found", ResponseStatus.OK.getMessage());
			} else {
				return new CardResponse(null,"No cards found", ResponseStatus.KO.getMessage());
			}
		}

	@PostMapping("/valid")
		public CardResponse valid(@RequestBody  ValidPayment validPayment) {
			
		Card card = this.cardService.findById(validPayment.getId());
		Double amountValue = Double.valueOf(validPayment.getValue());
		if (card != null) {
			List<Card> cards = new ArrayList<Card>();
			cards.add(card);
			if (card.getExpireDate().before(new Date()) && card.getLimitAmount()>= amountValue ) {
				return new CardResponse(cards,"Payment possible", ResponseStatus.OK.getMessage());
			} else {
				return new CardResponse(cards,"Payment not possible", ResponseStatus.KO.getMessage());
			}
		} else {
			return new CardResponse(null,"Card not found", ResponseStatus.KO.getMessage());
		}




	}

		
	@GetMapping("/{idNumber}")
		public CardResponse getCard(@PathVariable(name = "idNumber") String idNumber) {
		Card card =  this.cardService.findById(idNumber);

		if ( card != null) {
			List<Card> cards = new ArrayList<Card>();
			cards.add(card);
			return new CardResponse(cards,"Card created", ResponseStatus.OK.getMessage());
		} else {
			return new CardResponse(null,"Card not created", ResponseStatus.KO.getMessage());
		}


	}



	@GetMapping("/compare/{idNumber1}/{idNumber2}")
		public CardResponse getCard(@PathVariable(name = "idNumber1") String idNumber1 , @PathVariable(name = "idNumber2") String idNumber2) {
		Card card1 = this.cardService.findById(idNumber1);
		Card card2 = this.cardService.findById(idNumber2);
		List<Card> cards = new ArrayList<Card>();
		cards.add(card1);
		cards.add(card2);
		if (card1 != null && card1.equals(card2)) {
			return new CardResponse(cards,"Cards are equals", ResponseStatus.OK.getMessage());

		} else {
			return new CardResponse(cards,"Cards are not equals", ResponseStatus.KO.getMessage());

		}
	}

	
}
