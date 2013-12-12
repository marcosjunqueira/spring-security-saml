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
package org.springframework.security.saml.web;

import org.springframework.security.service.SampleService;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 *
 * @author Marcos Oliveira Junqueira <marcos.junqueira@gmail.com>
 */
@Controller
@RequestMapping("/xacml")
public class XacmlController {

    @Autowired
    SampleService testService;

    @RequestMapping()
    public ModelAndView refreshMetadata() throws MetadataProviderException {

        ModelAndView model = new ModelAndView(new InternalResourceView("/WEB-INF/security/xacml.jsp", true));

        model.addObject("xacml", "XACML OK");
        model.addObject("list", testService.teste());
        testService.delete("deletando");
//        testService.doSomething("admin");

        return model;

    }
}
