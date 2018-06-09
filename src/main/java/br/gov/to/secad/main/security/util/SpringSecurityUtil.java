package br.gov.to.secad.main.security.util;

import br.gov.to.secad.main.security.UsuarioSistema;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author wandyer.silva
 */
public final class SpringSecurityUtil {

    private SpringSecurityUtil() {
        throw new AssertionError();
    }

    //

    /**
     * Gets the current user details.
     *
     * @return the current user details or null if can't be retrieved.
     */
    public static UserDetails getCurrentUserDetails() {
        final Authentication authentication = getCurrentAuthentication();
        if (authentication == null) {
            return null;
        }

        final Object principal = authentication.getPrincipal();
        if (principal == null) {
            return null;
        }
        if (principal instanceof UserDetails) {
            return (UserDetails) principal;
        }

        return null;
    }

    private static Authentication getCurrentAuthentication() {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            return null;
        }
        return securityContext.getAuthentication();
    }

    public static UsuarioSistema getCurrentPrincipal() {
        final Authentication currentAuthentication = getCurrentAuthentication();
        if (currentAuthentication == null) {
            return null;
        }
        if (currentAuthentication.getPrincipal().toString().equalsIgnoreCase("anonymousUser")) {
            return null;
        }

        final Object principal = currentAuthentication.getPrincipal();
        if (principal == null) {
            return null;
        }

        return (UsuarioSistema) principal;
    }

    public static String getNameOfCurrentPrincipal() {
        final Authentication authentication = getCurrentAuthentication();
        if (authentication == null) {
            return null;
        }

        return authentication.getName();
    }

    public static String getCPF() {
        final Authentication authentication = getCurrentAuthentication();
        if (authentication == null) {
            return null;
        }

        return authentication.getName();
    }


    /**
     * Check if current user is authenticated.
     *
     * @return true if user is authenticated.
     */
    public static boolean isAuthenticated() {
        return SpringSecurityUtil.getCurrentUserDetails() != null;
    }


    /**
     * Check if current user has specified role.
     *
     * @param privilege
     *            the role to check if user has.
     * @return true if user has specified role, otherwise false.
     */
    public static boolean hasPrivilege(final String privilege) {
        final UserDetails userDetails = SpringSecurityUtil.getCurrentUserDetails();
        if (userDetails != null) {
            for (final GrantedAuthority each : userDetails.getAuthorities()) {
                if (each.getAuthority().equals(privilege)) {
                    return true;
                }
            }
        }

        return false;
    }

}
