package java_advance.spring_boot_spotify.controller;

import java_advance.spring_boot_spotify.model.ResponseStatus;
import java_advance.spring_boot_spotify.model.api.LoginResponse;
import java_advance.spring_boot_spotify.utils.GlobalUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/auth/login")
    public ResponseEntity<String> Login() {
        try {
            LoginResponse response = new LoginResponse();
            response.setStatus(ResponseStatus.Status.SUCCESS);
            response.setMessage("login success!");
            return ResponseEntity.ok(GlobalUtils.JsonStringify(response));
        }
        catch(Exception e) {
            LoginResponse response = new LoginResponse();
            response.setStatus(ResponseStatus.Status.FAILED);
            response.setMessage("login failed!");
            return ResponseEntity.ok(GlobalUtils.JsonStringify(response));
        }
    }
}