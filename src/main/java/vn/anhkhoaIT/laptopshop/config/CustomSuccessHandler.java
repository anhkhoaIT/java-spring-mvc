package vn.anhkhoaIT.laptopshop.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.anhkhoaIT.laptopshop.domain.User;
import vn.anhkhoaIT.laptopshop.service.UserService;

public class CustomSuccessHandler implements AuthenticationSuccessHandler{
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private UserService userService;
    protected void handle(
        HttpServletRequest request,
        HttpServletResponse response, 
        Authentication authentication
) throws IOException {

    String targetUrl = determineTargetUrl(authentication);

    redirectStrategy.sendRedirect(request, response, targetUrl);
}
    protected String determineTargetUrl(final Authentication authentication) {

    Map<String, String> roleTargetUrlMap = new HashMap<>();
    roleTargetUrlMap.put("ROLE_USER", "/");
    roleTargetUrlMap.put("ROLE_ADMIN", "/admin");

    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (final GrantedAuthority grantedAuthority : authorities) {
        String authorityName = grantedAuthority.getAuthority();
        if(roleTargetUrlMap.containsKey(authorityName)) {
            return roleTargetUrlMap.get(authorityName);
        }
    }

    throw new IllegalStateException();
}
    protected void clearAuthenticationAttributes(HttpServletRequest request, Authentication authentication) {
    HttpSession session = request.getSession(false);
    if (session == null) {
        return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    //get user information
    String email = authentication.getName();
    User user = this.userService.getUserByEmail(email);
    session.setAttribute("fullName", user.getFullName());
    session.setAttribute("avatar", user.getAvatar());
}

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request , authentication);
    }
    
}
