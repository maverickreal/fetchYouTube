package com.spring_oauth.demo;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import com.spring_oauth.demo.Records.*;

interface Youtube {
    @GetExchange("/search?part=snippet&type=video")
    SearchListResponse channelVideos(@RequestParam String channelId, @RequestParam int maxResults, @RequestParam Sort order);

    static enum Sort {
        DATE("date"),
        VIEW_COUNT("viewCount"),
        TITLE("title"),
        RATING("rating");

        private final String type;

        Sort(String _Type) {
            this.type = _Type;
        }
    }
}