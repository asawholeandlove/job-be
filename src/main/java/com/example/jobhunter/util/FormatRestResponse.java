package com.example.jobhunter.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.jobhunter.model.RestResponse;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {

  @Override
  @Nullable
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
      Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    var servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
    var status = servletResponse.getStatus();

    var res = new RestResponse<Object>();
    res.setStatusCode(status);
    if (status >= 400) {
      // case error
      return body;

    } else {
      res.setData(body);
      res.setMessage("Success");
    }

    return res;
  }

  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

}