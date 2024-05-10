package com.e_commerce.e_commerce.services.auth;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.helper.Validation;
import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.services.auth.forget_password.PasswordService;
import com.e_commerce.e_commerce.services.auth.login.LoginService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect //bean
@EnableAspectJAutoProxy
@Component
@AllArgsConstructor
public class AuthAop {
    @Autowired
    private final ResponseHelper responseHelper;
    private final Validation validation;
    private final LoginService loginService;
    private final SecurityHelper securityHelper;

    @Around(value = "execution(* com.e_commerce.e_commerce.services.auth.signup.RegisterController.signup(..))")
    public ResponseEntity<Object> aroundAdviceSignup(ProceedingJoinPoint joinPoint) {
        try {
            // Get the arguments passed to the loginUserServ method
            Object[] args = joinPoint.getArgs();
            Map<String, Object> credentials = new HashMap<>(); // Assuming it's the first argument
            credentials.putAll((Map<String, Object>) args[0]); // Convert to Map<String, Object>
            validation.validateSignup(credentials);

            ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
            return response;

        } catch (Throwable e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "AOP : Error while registration ", e.getMessage());
        }
    }

    @Around(value = "execution(* com.e_commerce.e_commerce.services.auth.login.LoginController.loginUser(..))")
    public ResponseEntity<Object> aroundAdviceLogin(ProceedingJoinPoint joinPoint) {
        try {
            // Get the arguments passed to the loginUserServ method
            Object[] args = joinPoint.getArgs();
            Map<String, String> credentials = (Map<String, String>) args[0]; // Assuming it's the first argument
            String email = credentials.get("email");
            String password = credentials.get("password");
            validation.validateLogin(email);
            loginService.login(email, password);

            ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
            return response;

        } catch (Throwable e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error", e.getMessage());
        }
    }

    @Around(value = "execution(* com.e_commerce.e_commerce.services.auth.forget_password.PasswordController.resettingPassword(..))")
    public ResponseEntity<Object> aroundAdviceResetPassword(ProceedingJoinPoint joinPoint) {
        try {
            // Get the arguments passed to the resetting password method
            Object[] args = joinPoint.getArgs();
            Map<String, String> data = (Map<String, String>) args[0]; // Assuming it's the first argument

            if (!validation.validatePassword(data.get("newPassword"))) {
                throw new Exception("One upper letter with two digits with minimum length 4  at least");
            }
            ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
            return response;

        } catch (Throwable e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "AOP : Error while resetting password", e.getMessage());
        }
    }

    @Around(value = "execution(* com.e_commerce.e_commerce.services.auth.forget_password.PasswordController.checkEmail(..))")
    public ResponseEntity<Object> aroundAdviceCheckEmail(ProceedingJoinPoint joinPoint) {
        try {
            // Get the arguments passed to the check email method
            Object[] args = joinPoint.getArgs();
            Map<String, String> data = (Map<String, String>) args[0]; // Assuming it's the first argument
            String email = data.get("email");
            validation.validateLogin(email);
            ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
            return response;

        } catch (Throwable e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "AOP : Error while resetting password", e.getMessage());
        }
    }


    // Calculate the execution time and if catch any exceptions for all methods
    @Around(value = "execution(* com.e_commerce.e_commerce.services..*(..))")
    public Object performance(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("performance of Method " + joinPoint.getSignature() +
                    " executed in " + executionTime + " milliseconds");
            return result;
        } catch (Exception e){
            // Getting only the method name not all the path
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    "AOP, Error while executing method " +((MethodSignature) joinPoint.getSignature()).getName(),e.getMessage());
        }

    }

    @Around(value = "execution(* com.e_commerce.e_commerce.services.cart.CartController.*(..))")
    public ResponseEntity<Object> aroundCartController(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object[] args = joinPoint.getArgs();
            Map<String, Object> data = (Map<String, Object>) args[0];
            if (Boolean.TRUE.equals(securityHelper.checkUserCredantials(data))){
                ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
                return response;
            } else {
            throw new Exception("Unauthorized Access ! ");
        }
        } catch (Exception e){
            // Getting only the method name not all the path
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    "AOP, Error while executing method " +((MethodSignature) joinPoint.getSignature()).getName(),e.getMessage());
        }

    }
}
