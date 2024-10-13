package org.learn.SpringBootWorkAroundBranch.service;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ExpiryDateServiceImpl implements ExpiryDateService {

    @Override
    public Date calculateExpiryDate(Date createdAt, int minutesToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdAt);
        calendar.add(Calendar.MINUTE, minutesToAdd);
        return calendar.getTime();
    }

    @Override
    public Boolean checkDateExpired(Date date) {
        return !date.after(new Date());
    }
}
