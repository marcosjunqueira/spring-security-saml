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

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author Marcos Oliveira Junqueira <marcos.junqueira@gmail.com>
 */
public class SampleService {

    @PreAuthorize("hasRole('Internal/everyone')")
    @PostFilter("hasPermission(filterObject, 'read')")
    public List<String> teste() {
        List<String> retValue = new ArrayList<String>();
        retValue.add("string_avaliacao1");
        retValue.add("string_avaliacao2");
        return retValue;
    }

    @PreAuthorize("hasPermission('contact', 'admin')")
    public void delete(String contact) {
        //
    }

    @PreAuthorize("#login == authentication.name")
    public void doSomething(String login) {
        System.out.println("login = " + login);
    }
}
