package com.spring_oauth.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {
    private final Youtube youTube;

    HomeController(Youtube _YouTube) {
        this.youTube = _YouTube;
    }

    @GetMapping
    String index(Model model) {
        model.addAttribute("channelVideos",
                youTube.channelVideos("UCjukbYOd6pjrMpNMFAOKYyw", 10,
                        Youtube.Sort.VIEW_COUNT));
        return "index";
    }
}