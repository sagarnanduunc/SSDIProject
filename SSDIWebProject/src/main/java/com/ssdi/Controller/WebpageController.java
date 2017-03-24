package com.ssdi.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jacob on 2/24/2017.
 */
@Controller
public class WebpageController {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
