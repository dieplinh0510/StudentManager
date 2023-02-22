package com.example.studentmanager.base;

import com.example.studentmanager.constant.AuthorityConstant;
import com.example.studentmanager.constant.UserMessageConstant;
import com.example.studentmanager.config.exception.VsException;
import com.nimbusds.jwt.SignedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

@Component("WebTransferRequestHeader")
public class RequestHeader {
  private final HttpServletRequest httpServletRequest;

  public RequestHeader(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
  }


  /**
   * @return String
   */
  public String getUUID() {
    String bearerToken = httpServletRequest.getHeader("Authorization");
    if (StringUtils.isEmpty(bearerToken)) {
      return AuthorityConstant.ANONYMOUS_USER;
    }
    String token = bearerToken.substring(7);
    try {
      SignedJWT decodedJWT = SignedJWT.parse(token);
      Map<String, Object> payload = decodedJWT.getPayload().toJSONObject();
      return (String) payload.get(AuthorityConstant.CLAIM_UUID);
    } catch (ParseException e) {
      throw new VsException(UserMessageConstant.ERR_EXCEPTION_ACCESS_SYSTEM, e.getMessage());
    }
  }
}
