package com.mongo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Account entity class.
 *
 * @author Artem Boiko
 */
public class Account implements Serializable {
    private Long cardNumber;
    private String nameOnAccount;
    private Date expirationDate;

    public Account() {
    }

    public Account(Long cardNumber, String nameOnAccount, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.nameOnAccount = nameOnAccount;
        this.expirationDate = expirationDate;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnAccount() {
        return nameOnAccount;
    }

    public void setNameOnAccount(String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return cardNumber.equals(account.cardNumber) &&
               nameOnAccount.equals(account.nameOnAccount) &&
               expirationDate.equals(account.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, nameOnAccount, expirationDate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                                Account.class.getSimpleName() + "[",
                                "]")
                .add("cardNumber=" + cardNumber)
                .add("nameOnAccount='" + nameOnAccount + "'")
                .add("expirationDate='" + expirationDate + "'")
                .toString();
    }
}
