package com.farm.pedia.download;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DownloadController {

    @GetMapping("/download")
    public String index() {
        return "download/index";
    }
}
