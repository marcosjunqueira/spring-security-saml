/*
 * Copyright 2013 Marcos Oliveira Junqueira <marcos.junqueira@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.neppo.spring.security.xacml;

import br.com.neppo.wsclient.authenticator.WSAuthenticator;
import java.io.Serializable;
import java.net.Authenticator;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.wso2.carbon.identity.entitlement.EntitlementService;
import org.wso2.carbon.identity.entitlement.EntitlementServicePortType;

/**
 *
 * @author Marcos Oliveira Junqueira <marcos.junqueira@gmail.com>
 */
public class WSO2PermissionEvaluator implements PermissionEvaluator {

    public WSO2PermissionEvaluator(WSAuthenticator authenticator) {
        Authenticator.setDefault(authenticator);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        String subject = "";
        String resource = "";
        String action = "";
        if (authentication == null) {
            return false;
        } else {
            subject = authentication.getName();
        }
        if (targetDomainObject != null) {
            resource = targetDomainObject.toString();
        }
        if (permission != null) {
            action = permission.toString();
        }
        return hasPermission(subject, resource, action);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        String subject = "";
        String resource = targetType;
        String action = "";
        if (authentication == null) {
            return false;
        } else {
            subject = authentication.getName();
        }
        if (permission != null) {
            action = permission.toString();
        }
        return hasPermission(subject, resource, action);
    }

    private Boolean hasPermission(String subject, String resource, String action) {
        Boolean result = true;
        EntitlementService service = new EntitlementService();
        try {
            EntitlementServicePortType port = service.getEntitlementServiceHttpsSoap11Endpoint();
            // TODO process result here
            result = port.getBooleanDecision(subject, resource, action);
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO log
        }
        return result;
    }

}
