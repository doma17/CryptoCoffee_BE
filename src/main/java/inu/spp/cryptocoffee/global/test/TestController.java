package inu.spp.cryptocoffee.global.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Security Config Test API")
@RestController
public class TestController {

    /**
     * 테스트 API
     * @return test 문자열 반환
     */
    @Operation(summary = "Main Test")
    @GetMapping("/test")
    public ResponseEntity<String > test() {
        return ResponseEntity.ok("test");
    }

    /**
     * admin 권한이 필요한 API
     * @return admin 문자열 반환
     */
    @Operation(summary = "Admin Test")
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("admin");
    }
}