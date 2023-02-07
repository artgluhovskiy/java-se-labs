package org.art.samples.core.design.patterns.builder.func;

import java.time.LocalDate;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class Letter {

    private final String returningAddress;

    private final String insideAddress;

    private final LocalDate dateOfLetter;

    private final String salutation;

    private final String body;

    private final String closing;

    Letter(String returningAddress, String insideAddress, LocalDate dateOfLetter, String salutation, String body, String closing) {
        this.returningAddress = returningAddress;
        this.insideAddress = insideAddress;
        this.dateOfLetter = dateOfLetter;
        this.salutation = salutation;
        this.body = body;
        this.closing = closing;
    }

    static AddReturnAddress builder() {
        return returnAddress
            -> closing
            -> dateOfLetter
            -> insideAddress
            -> salutation
            -> body
            -> new Letter(returnAddress, insideAddress, dateOfLetter, salutation, body, closing);
    }

    interface AddReturnAddress {
        Letter.AddClosing withReturnAddress(String returnAddress);
    }

    interface AddClosing {
        Letter.AddDateOfLetter withClosing(String closing);
    }

    interface AddDateOfLetter {
        Letter.AddInsideAddress withDateOfLetter(LocalDate dateOfLetter);
    }

    interface AddInsideAddress {
        Letter.AddSalutation withInsideAddress(String insideAddress);
    }

    interface AddSalutation {
        Letter.AddBody withSalutation(String salutation);
    }

    interface AddBody {
        Letter withBody(String body);
    }

    public static void main(String[] args) {
        Letter letter = Letter.builder()
            .withReturnAddress("Return Address")
            .withClosing("Closing")
            .withDateOfLetter(LocalDate.now())
            .withInsideAddress("Inside Address")
            .withSalutation("Salutation")
            .withBody("Letter Body");

        log.info("Letter: {}", letter);
    }
}
