////////////////////////////////////////////////////////////////////////
//
//     Copyright (c) 2009-2014 Denim Group, Ltd.
//
//     The contents of this file are subject to the Mozilla Public License
//     Version 2.0 (the "License"); you may not use this file except in
//     compliance with the License. You may obtain a copy of the License at
//     http://www.mozilla.org/MPL/
//
//     Software distributed under the License is distributed on an "AS IS"
//     basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
//     License for the specific language governing rights and limitations
//     under the License.
//
//     The Original Code is ThreadFix.
//
//     The Initial Developer of the Original Code is Denim Group, Ltd.
//     Portions created by Denim Group, Ltd. are Copyright (C)
//     Denim Group, Ltd. All Rights Reserved.
//
//     Contributor(s): Denim Group, Ltd.
//
////////////////////////////////////////////////////////////////////////
package com.denimgroup.threadfix.framework.impl.dotNetWebForm;

import com.denimgroup.threadfix.data.interfaces.Endpoint;
import com.denimgroup.threadfix.framework.TestConstants;
import com.denimgroup.threadfix.framework.engine.full.EndpointDatabase;
import com.denimgroup.threadfix.framework.engine.full.EndpointDatabaseFactory;
import com.denimgroup.threadfix.framework.engine.full.EndpointQuery;
import com.denimgroup.threadfix.framework.engine.full.EndpointQueryBuilder;
import org.junit.Test;

import java.util.Set;

/**
 * Created by mac on 10/20/14.
 */
public class AutoGeneratedParameterNameTests {

    @Test
    public void testContosoGeneratedProperties() {
        EndpointDatabase database = EndpointDatabaseFactory.getDatabase(TestConstants.WEB_FORMS_CONTOSO);

        assert database != null : "Database was null for " + TestConstants.WEB_FORMS_CONTOSO;

        EndpointQuery query =
                EndpointQueryBuilder
                    .start()
                    .setDynamicPath("/StudentsAdd.aspx")
                    .generateQuery();

        Set<Endpoint> allMatches = database.findAllMatches(query);

        assert allMatches.size() == 1 :
                "Got " + allMatches.size() + " endpoints for /StudentsAdd.aspx: " + allMatches;

        Endpoint endpoint = allMatches.iterator().next();

        String[] params = {
                "ctl00$MainContent$StudentsDetailsView$ctl01",
                "ctl00$MainContent$StudentsDetailsView$ctl02",
                "ctl00$MainContent$StudentsDetailsView$ctl03"
        };

        for (String param : params) {
            assert endpoint.getParameters().contains(param) :
                    "Parameters didn't contain " + param + " : " + endpoint.getParameters();
        }
    }

}
