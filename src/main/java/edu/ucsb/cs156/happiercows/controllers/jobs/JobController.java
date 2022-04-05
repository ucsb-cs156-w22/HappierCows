package edu.ucsb.cs156.happiercows.controllers.jobs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(JobController.PREFIX)
public abstract class JobController {
  public static final String PREFIX = "/api/jobs";

  @Component
  public static class JobInterceptor implements HandlerInterceptor {
    @Value("${app.internal-api-key}")
    private String internalApiKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      if (!request.getServletPath().startsWith(JobController.PREFIX)) {
        return true;
      }

      if (!internalApiKey.equals(request.getHeader("X-API-KEY"))) {
        response.setStatus(403);
        return false;
      }

      return true;
    }
  }
}
