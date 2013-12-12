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
package org.springframework.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Marcos Oliveira Junqueira <marcos.junqueira@gmail.com>
 */
public class Role implements GrantedAuthority {

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    /**
     * Get the value of authority
     *
     * @return the value of authority
     */
    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * Set the value of authority
     *
     * @param authority new value of authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
