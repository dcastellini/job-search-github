package com.platzi.job.api;

import com.platzi.job.JobPosition;
import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

@Headers("Accept: application/json")
public interface APIjobs {
    @RequestLine("GET/positions.json")
    List<JobPosition> jobs(@QueryMap Map<String, Object> queryMap);
}
