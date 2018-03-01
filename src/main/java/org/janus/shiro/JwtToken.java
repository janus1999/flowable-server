package org.janus.shiro;

import org.apache.shiro.authc.AuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken implements AuthenticationToken {

	private static final long serialVersionUID = 2332067650823750644L;

	private String principal;

    private String token;
    
    @Override
    public String getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
