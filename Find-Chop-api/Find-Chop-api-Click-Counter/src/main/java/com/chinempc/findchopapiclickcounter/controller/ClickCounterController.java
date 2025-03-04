package com.chinempc.findchopapiclickcounter.controller;

import com.chinempc.findchopapiclickcounter.dto.RequestDTO;
import com.chinempc.findchopapiclickcounter.dto.ResponseDTO;
import com.chinempc.findchopapiclickcounter.service.ClickCounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/click-counter")
@RequiredArgsConstructor
@Slf4j
public class ClickCounterController {
    private final ClickCounterService service;

    @PostMapping()
    public ResponseEntity<String> updateCount(@RequestBody RequestDTO request) {
        log.info("Request : {}", request);
        if (service.updateClickCounterByName(request)) {
            return ResponseEntity.ok()
                    .body("Updated");
        }
        return ResponseEntity.badRequest()
                .body("Bad Request");
    }

    @GetMapping("/top-recipes")
    public ResponseEntity<List<ResponseDTO>> getTopThreeRecipes(){
        return ResponseEntity.ok()
                .body(service.getTopThreeRecipes());
    }
}
