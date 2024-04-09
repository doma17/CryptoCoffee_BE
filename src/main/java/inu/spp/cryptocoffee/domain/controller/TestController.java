package inu.spp.cryptocoffee.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * 테스트 API
     * @return test 문자열 반환
     */
    @GetMapping("/test")
    public ResponseEntity<String > test() {
        return ResponseEntity.ok("test");
    }

    /**
     * admin 권한이 필요한 API
     * @return admin 문자열 반환
     */
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("admin");
    }
}