package com.wisely.ch9_2.batch;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

/**
 * 数据校验：
 * 1 使用JSR-303的 Validator 来教研我们的数据，在此处进行 JSR-303 的 Validator 的初始化。
 * 2 使用 Validator 的 validate 方法校验数据。
 */
public class CsvBeanValidator<T> implements Validator<T>,InitializingBean {
    private javax.validation.Validator validator; 
    @Override
    public void afterPropertiesSet() throws Exception { //1
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
    public void validate(T value) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(value); //2
        if(constraintViolations.size()>0){
            
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                message.append(constraintViolation.getMessage() + "\n");
            }
            throw new ValidationException(message.toString());

        }

    }

}
