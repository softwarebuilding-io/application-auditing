package io.softwarebuilding.fusionplex.util;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class IpUtil {
    private static final Logger LOG = LoggerFactory.getLogger( IpUtil.class );

    private static final String X_FORWARDED_FOR = "X-Forwarded-For";

    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    private static final String UNKNOWN = "unknown";

    private static final String LOCALHOST_IPV4 = "127.0.0.1";

    private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    private static HttpServletRequest REQUEST;

    /**
     * This class should not be instantiated, as all methods are static.
     * Private constructor to prevent instantiation.
     */
    private IpUtil() {
    }

    public static void setHttpServletRequest( final HttpServletRequest request ) {
        IpUtil.REQUEST = request;
    }

    /**
     * Retrieves Client IP Address.
     *
     * @return client ip address
     */
    public static String getClientIpAddress() {
        if ( REQUEST == null ) {
            return LOCALHOST_IPV4;
        }

        String clientIpAddress = REQUEST.getHeader( X_FORWARDED_FOR );

        if ( !StringUtils.hasLength( clientIpAddress ) || UNKNOWN.equals( clientIpAddress ) ) {
            clientIpAddress = REQUEST.getHeader( PROXY_CLIENT_IP );
        }

        if ( !StringUtils.hasLength( clientIpAddress ) || UNKNOWN.equals( clientIpAddress ) ) {
            clientIpAddress = REQUEST.getHeader( WL_PROXY_CLIENT_IP );
        }

        if ( !StringUtils.hasLength( clientIpAddress ) || UNKNOWN.equals( clientIpAddress ) ) {
            clientIpAddress = REQUEST.getRemoteAddr();

            if ( LOCALHOST_IPV4.equals( clientIpAddress ) || LOCALHOST_IPV6.equals( clientIpAddress ) ) {

                try {
                    final InetAddress inetAddress = InetAddress.getLocalHost();
                    clientIpAddress = inetAddress.getHostAddress();

                } catch ( final UnknownHostException exception ) {
                    LOG.error( exception.getMessage(), exception );
                }
            }
        }

        if ( StringUtils.hasLength( clientIpAddress )
                && clientIpAddress.length() > 15
                && clientIpAddress.indexOf( "," ) > 0 ) {

            clientIpAddress = clientIpAddress.substring( 0, clientIpAddress.indexOf( "," ) );
        }

        return clientIpAddress;
    }

    public static String getUserAgent() {
        return REQUEST.getHeader( "user-agent" );
    }
}