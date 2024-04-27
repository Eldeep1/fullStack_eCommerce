package com.e_commerce.e_commerce.services.auth;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.Validation;
import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.services.auth.forget_password.PasswordService;
import com.e_commerce.e_commerce.services.auth.login.LoginService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
    private final PasswordService passwordService;
    private final LoginService loginService;

    @Around(value = "execution(* com.e_commerce.e_commerce.services.auth.signup.RegisterController.signup(..))")
    public ResponseEntity<Object> aroundAdviceSignup(ProceedingJoinPoint joinPoint){
        try {
            // Get the arguments passed to the loginUserServ method
            Object[] args = joinPoint.getArgs();
            Map<String, Object> credentials = new HashMap<>(); // Assuming it's the first argument
            credentials.putAll((Map<String, Object>) args[0]); // Convert to Map<String, Object>
            Map<String,String> validationErrors = validation.validateSignup(credentials);

            if (!validationErrors.isEmpty()) {
                return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid input fields", validationErrors);
            }
            else{
                ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
                return response;
            }

        } catch (Throwable e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"AOP : Error while registration ",e.getMessage());
        }
    }

    @Around(value = "execution(* com.e_commerce.e_commerce.services.auth.login.LoginController.loginUser(..))")
    public ResponseEntity<Object> aroundAdviceLogin(ProceedingJoinPoint joinPoint){
        try {
            // Get the arguments passed to the loginUserServ method
            Object[] args = joinPoint.getArgs();
            Map<String, String> credentials = (Map<String, String>) args[0]; // Assuming it's the first argument
            String email = credentials.get("email");
            String password = credentials.get("password");
            Map<String,String> validateErrors =validation.validateLogin(email);

            if (!validateErrors.isEmpty()) {
                return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid input fields", validateErrors);
            }
            User authenticatedUser = loginService.login(email, password);

            if(authenticatedUser != null ){
                ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
                return response;
            } else {
                // If authentication fails, return an error response
                return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid Credentials", null);
            }
        } catch (Throwable e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"AOP : User doesn't exist !",e.getMessage());
        }
    }

    @Around(value = "execution(* com.e_commerce.e_commerce.services.auth.forget_password.PasswordController.resettingpassword(..))")
    public ResponseEntity<Object> aroundAdviceResetPassword(ProceedingJoinPoint joinPoint) {
        try {
            // Get the arguments passed to the resetting password method
            Object[] args = joinPoint.getArgs();
            Map<String, String> data = (Map<String, String>) args[0]; // Assuming it's the first argument
            try {
                if (!passwordService.verifyUser(data.get("question_id"), data.get("email"),data.get("answer"))) {
                    return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "credantials are wrong",null);
                }
                if (!validation.validatePassword(data.get("newPassword"))){
                    return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED,
                            "Password must contain exactly one capiltal letter and least two digits, with a length between 4 and 10 characters",
                            null);
                }
            } catch (Exception e) {
                System.out.println(e);
                return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Email doesn't exist !",null);
            }
            ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
            return response;

        } catch (Throwable e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"AOP : Error while resetting password",e.getMessage());
        }
    }

    @Around(value = "execution(* com.e_commerce.e_commerce.services.auth.forget_password.PasswordController.checkEmail(..))")
    public ResponseEntity<Object> aroundAdviceCheckEmail(ProceedingJoinPoint joinPoint) {
        try {
            // Get the arguments passed to the check email method
            Object[] args = joinPoint.getArgs();
            Map<String, String> data = (Map<String, String>) args[0]; // Assuming it's the first argument
            String email=data.get("email");
            Map<String, String> errors = validation.validateLogin(email);
            if(errors.isEmpty()){ // no errors
                ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
                return response;
            }
            else {
                return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid input fields", errors);
            }

        } catch (Throwable e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"AOP : Error while resetting password",e.getMessage());
        }
    }
}
