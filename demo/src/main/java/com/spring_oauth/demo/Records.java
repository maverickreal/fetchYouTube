package com.spring_oauth.demo;

import java.util.Map;

class Records {
        static final record searchList(String kind, String etag, String nextPageToken, String prevPageToken,
                        PageInfo pageInfo,
                        SearchResult[] items) {
        }

        static final record PageInfo(Integer totalResults, Integer resultsPerPage) {
        }

        static final record SearchResult(String kind, String etag, SearchId id, SearchSnippet snippet) {
        }

        static final record SearchId(String kind, String videoId, String channelId, String playlistId) {
        }

        static final record SearchSnippet(String publishedAt, String channelId, String title, String description,
                        Map<String, SearchThumbnail> thumbnails, String channelTitle) {
                String shortDescription() {
                        if (this.description.length() <= 100) {
                                return this.description;
                        }
                        return this.description.substring(0, 100) + "...";
                }

                SearchThumbnail thumbnail() {
                        //print the thumbnails
                        System.out.println(this.thumbnails);
                        return this.thumbnails.entrySet()
                                        .stream()
                                        .filter(entry -> entry.getKey().equals("default"))
                                        .findFirst()
                                        .map(Map.Entry::getValue)
                                        .orElse(null);
                }
        }

        static final record SearchThumbnail(String url, Integer width, Integer height) {
        }

        static final record SearchListResponse(String kind, String etag, String nextPageToken, String prevPageToken,
                        PageInfo pageInfo, SearchResult[] items) {
        }
}