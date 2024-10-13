package org.learn.SpringBootWorkAroundBranch.service;

import java.util.Date;

public interface ExpiryDateService {
    Date calculateExpiryDate(Date createdAt, int minutesToAdd);
    Boolean checkDateExpired(Date date);
}
