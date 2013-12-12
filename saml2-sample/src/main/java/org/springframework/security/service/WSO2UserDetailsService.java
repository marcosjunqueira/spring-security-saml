/*
 * Copyright 2013 SpringSource.
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
package org.springframework.security.service;

import org.opensaml.saml2.core.Attribute;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.impl.XSStringImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.model.Role;
import org.springframework.security.model.User;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;

/**
 *
 * @author Marcos Oliveira Junqueira <marcos.junqueira@gmail.com>
 */
public class WSO2UserDetailsService implements SAMLUserDetailsService {

    @Override
    public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
        User retValue = new User();
        retValue.setUsername(credential.getNameID().getValue());
        Attribute roleAttribute = credential.getAttributeByName("http://wso2.org/claims/role");
        XMLObject next = roleAttribute.getAttributeValues().iterator().next();
        if (next instanceof XSStringImpl) {
            XSStringImpl xsRoles = (XSStringImpl) next;
            String[] split = xsRoles.getValue().split(",");
            for (String s : split) {
                retValue.getAuthorities().add(new Role(s));
            }
        }
        return retValue;
    }

}
