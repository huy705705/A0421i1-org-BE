package com.example.demo.edit_logger;

import com.example.demo.model.Cage;
import com.example.demo.model.EditLog;
import com.example.demo.model.dto.CageForEditDto;
import com.example.demo.service.impl.EditLogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

@Component
@Aspect
public class LoggerAspect {
    @Autowired
    private EditLogServiceImpl editLogService;

    private Cage previousCage;

    private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @AfterReturning(value = "execution(* com.example.demo.service.impl.CageServiceImpl.findCageById3(String))", returning = "oldCage")
    public void afterFindCageById(JoinPoint joinPoint, Optional<Cage> oldCage) throws Throwable {
        if (oldCage.isPresent()){
            logger.info("Log cage after find by ID: " + oldCage.get().toString());
            this.previousCage = oldCage.get();
        } else {
            this.previousCage = null;
        }

    }

    @AfterReturning(value = "@annotation(com.example.demo.edit_logger.TractChange)", returning = "cage")
    public void logChangeFields(JoinPoint joinPoint, Cage cage) throws Throwable {
//        logger.info("old cage: " + this.previousCage.toString());
        logger.info("Log cage after edit: " + cage.toString());
        StringBuilder result = new StringBuilder();

        if (previousCage==null) {
            result.append("Tạo mới chuồng nuôi");
        } else {
            List<String> listChange = getDifference(this.previousCage, cage);
            for (int i = 0; i < listChange.size(); i++) {
                if (i < listChange.size() - 1) {
                    result.append(listChange.get(i)).append(";");
                } else {
                    result.append(listChange.get(i));
                }
            }
        }
            logger.info("result of change: " + result);
            LocalDate now = LocalDate.now();
            String cageId = cage.getCageId();
            String employee = cage.getEmployee().getEmployeeName();
            EditLog editLog = new EditLog(cageId, now, employee, result.toString());
            editLogService.save(editLog);

    }

    private static List<String> getDifference(Object s1, Object s2) throws IllegalAccessException, NoSuchFieldException {
        List<String> values = new ArrayList<>();
        String[] compareFields = { "cageId", "createdDate","closedDate", "quantity"}; // list field need to compare
        for (String s : compareFields) {
            Field field = s1.getClass().getDeclaredField(s); // get a list of all fields for this class
            field.setAccessible(true);
            Object value1 = field.get(s1);
            Object value2 = field.get(s2);
            if (value1 != null && value2 != null) {
                if (!Objects.equals(value1, value2)) {
                    values.add(String.valueOf(field.getName()+": "+value1+" -> "+value2));
                }
            }
        }
        return values;
    }


}
