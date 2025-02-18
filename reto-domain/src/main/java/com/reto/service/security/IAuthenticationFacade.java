package com.reto.service.security;

/**
 * IAuthenticationFacade.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface IAuthenticationFacade {

    /**
     * getAuthenticatedUser.
     *
     * @return
     */
    String getAuthenticatedUser();

    /**
     * getCurrentApplication.
     *
     * @return
     */
    String getCurrentApplication();

    /**
     * getAuthenticatedNombreCompleto.
     *
     * @return
     */
    String getAuthenticatedNombreCompleto();

    /**
     * getCurrentSessionToken.
     *
     * @return
     */
    String getCurrentSessionToken();
}
